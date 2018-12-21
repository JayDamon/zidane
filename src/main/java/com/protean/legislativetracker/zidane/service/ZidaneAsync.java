package com.protean.legislativetracker.zidane.service;

import com.protean.legislativetracker.zidane.service.update.AsyncService;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public abstract class ZidaneAsync<R, I> {

    protected Logger log;

    private AsyncService<R> asyncService;

    private List<I> inputList;
    private List<I> listToProcess;

    public ZidaneAsync(Logger log, AsyncService asyncService) {
        this.log = log;
        this.asyncService = asyncService;
    }

    protected List<R> retrieveItemsAsynchronouslyByInterval(List<I> inputList, int interval) {

        // Set list to process by threaded operation
        this.inputList = inputList;

        // Instantiate bills that need to be processed if not already set
        if (this.listToProcess == null) { this.listToProcess = new ArrayList<>(); }

        // Instantiate return value
        List<R> processedBills = new ArrayList<>();

        int upperVal = interval;
        int lowerValue = 0;

        List<CompletableFuture<List<R>>> completableFutures = new ArrayList<>();

        boolean breakNextLoop = false;
        // Loop through the bill list 200 at a time
        while (true) {
            // Spin off thread to process bill chunk
            completableFutures.add(asyncService.createThreadToProcessItem(lowerValue, upperVal, this));

            if (breakNextLoop) { break; }

            upperVal += interval;
            lowerValue += interval;
            if (upperVal > inputList.size()) {
                upperVal = inputList.size();
                breakNextLoop = true;
            }
            log.info("Values: lowerValue = <" + lowerValue + "> | upperVal = <" + upperVal + "> | list size = <" + inputList.size() + ">");
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
        try {
            for (CompletableFuture<List<R>> completableFuture : completableFutures) {
                processedBills.addAll(completableFuture.get());
            }
        } catch (InterruptedException e) {
            log.error("Thread was interrupted", e);
        } catch (ExecutionException e) {
            log.error("There was an error executing the thread", e);
        }
        return processedBills;
    }

    public void doThreadedAction(int i) {
        listToProcess.add(inputList.get(i));
    }

    public abstract List<R> processThreadedItems();

    public void runPostThreadCleanup() {
        listToProcess = new ArrayList<>();
    }

    public List<I> getInputList() {
        return inputList;
    }

    public void setInputList(List<I> inputList) {
        this.inputList = inputList;
    }

    public List<I> getListToProcess() {
        return listToProcess;
    }

    public void setListToProcess(List<I> listToProcess) {
        this.listToProcess = listToProcess;
    }
}
