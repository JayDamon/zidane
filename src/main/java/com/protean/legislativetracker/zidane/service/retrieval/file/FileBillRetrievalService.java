package com.protean.legislativetracker.zidane.service.retrieval.file;

import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.service.ZidaneAsync;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.update.AsyncService;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileBillRetrievalService extends ZidaneAsync<Bill, File> implements BillRetrievalService {

    private static final Logger log = LoggerFactory.getLogger(FileBillRetrievalService.class);

    private HttpRequestService httpRequestService;
    private FileRetrievalService fileRetrievalService;

    private List<Bill> workingList = new ArrayList<>();

    public FileBillRetrievalService(AsyncService asyncService, String fileLocation) {
        super(log, asyncService);
        fileRetrievalService = new FileRetrievalService(fileLocation, "/bill");
        httpRequestService = new HttpRequestServiceImpl();
    }

    @Override
    public List<Bill> getAllByItems(List<String> item) {
        return null; // TODO refactor out unused?
    }

    @Override
    public List<Bill> getAllByExternalId(Integer id) {
        log.info("Starting bill retrieval for directory " + fileRetrievalService.getFullFileLocation("log"));
        String fileLocation = fileRetrievalService.getFullFileLocation(SessionFolder.SESSION_FOLDER.get(id));
        List<File> files = fileRetrievalService.getFilesInDirectory(fileLocation);

        return retrieveItemsAsynchronouslyByInterval(files, 100);
    }

//    private List<Bill> retrieveItemsAsynchronouslyByInterval(List<File> inputList, int interval) {
//
//        // Set list to process by threaded operation
//        this.inputList = inputList;
//
//        // Instantiate bills that need to be processed if not already set
//        if (this.listToProcess != null) { this.listToProcess = new ArrayList<>(); }
//
//        // Instantiate return value
//        List<Bill> processedBills = new ArrayList<>();
//
//        int upperVal = interval;
//        int lowerValue = 0;
//
//        List<CompletableFuture<List<Bill>>> completableFutures = new ArrayList<>();
//
//        boolean breakNextLoop = false;
//        // Loop through the files 100 at a time
//        while (true) {
//
//            completableFutures.add(createThreadToProcessItem(upperVal, lowerValue));
//
//            if (breakNextLoop) { break; }
//
//            upperVal += interval;
//            lowerValue += interval;
//            if (upperVal > inputList.size()) {
//                upperVal = inputList.size();
//                breakNextLoop = true;
//            }
//            log.info("Values: upperVal = " + upperVal + " | j = " + lowerValue + " | list size = " + inputList.size());
//        }
//        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
//
//        try {
//            for (CompletableFuture<List<Bill>> completableFuture : completableFutures) {
//                processedBills.addAll(completableFuture.get());
//            }
//        } catch (InterruptedException e) {
//            log.error("Thread was interrupted", e);
//        } catch (ExecutionException e) {
//            log.error("There was an error executing the thread", e);
//        }
//        return processedBills;
//    }

    @Override
    public List<Bill> getAllById(List<Long> ids) {
        return null; // TODO refactor out unused?
    }

    @Override
    public List<Bill> getAllByEntity(List<Bill> entities) {
        return null; // TODO refactor out unused?
    }

    @Override
    public Bill getById(long billId) {
        return null; // TODO refactor out unused?
    }

    @Override
    public Bill getByEntity(Bill bill) {
        return null; // TODO refactor out unused?
    }

//    @Async("threadPoolTaskExecutor")
//    public CompletableFuture<List<Bill>> createThreadToProcessItem(int upperVal, int lowerVal) {
//
//        log.info("Starting thread to update item starting at <" + lowerVal + "> and ending at <" + upperVal + "> on Thread: "
//                + Thread.currentThread().getName());
//
//        // Retrieve set of 200 bills
//        for (int i = lowerVal ; i < upperVal ; i++) {
//            doThreadedAction(i);
//        }
//
//        return CompletableFuture.completedFuture(processThreadedItems());
//    }

    public void doThreadedAction(int i) {
        String json = JsonFileLoader.readJsonFileAsString(getInputList().get(i).getPath());
        workingList.add(
                httpRequestService.getPojoFromJson(Bill.class, json, "bill")
        );
    }

    public List<Bill> processThreadedItems() {
        return workingList;
    }

    @Override
    public void runPostThreadCleanup() {
        workingList = new ArrayList<>();
    }
}
