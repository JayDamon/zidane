package com.protean.legislativetracker.zidane.service;

import com.protean.legislativetracker.yuna.model.Person;
import com.protean.legislativetracker.yuna.model.RollCall;
import com.protean.legislativetracker.yuna.service.PersonService;
import com.protean.legislativetracker.yuna.service.RollCallService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.legiscan.PersonModelMapper;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.SessionFolder;
import com.protean.legislativetracker.zidane.service.update.BillUpdateService;
import com.protean.legislativetracker.zidane.service.update.file.FileBillUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileUpdateService implements UpdateService {

    private Logger log = LoggerFactory.getLogger(FileUpdateService.class);

    private BillUpdateService billUpdateService;
    private PersonRetrievalService personRetrievalService;
    private RollCallRetrievalService rollCallRetrievalService;
    private PersonService personService;
    private RollCallService rollCallService;

    public FileUpdateService(FileBillUpdateService billUpdateService,
                             @Qualifier("filePersonRetrievalService") PersonRetrievalService personRetrievalService,
                             @Qualifier("fileRollCallRetrievalService") RollCallRetrievalService rollCallRetrievalService,
                             PersonService personService,
                             RollCallService rollCallService) {
        this.billUpdateService = billUpdateService;
        this.personRetrievalService = personRetrievalService;
        this.rollCallRetrievalService = rollCallRetrievalService;
        this.personService = personService;
        this.rollCallService = rollCallService;
    }

    @Override
    public void getUpdates() {
        List<Integer> sessions = new ArrayList<>(SessionFolder.SESSION_FOLDER.keySet());
        for (Integer sessionId : sessions) {
            performUpdate(sessionId);
        }
    }

    @Override
    public void getUpdatesForSession(Integer sessionId) {
        performUpdate(sessionId);
    }

    private void performUpdate(Integer sessionId) {

        updatePeople(sessionId);
        updateBills(sessionId);
        updateRollCalls(sessionId);
        log.info("Session with Id " + sessionId + " has been updated");
    }

    private void updatePeople(Integer sessionId) {
        List<Person> people = personRetrievalService.getAllById(Collections.singletonList(
                sessionId.longValue())).stream().map(
                        PersonModelMapper::legiscanPersonToModelPerson).collect(Collectors.toList());
        ;
        personService.savePeople(people);
    }

    private void updateBills(Integer sessionId) {

        billUpdateService.getUpdatedBills(sessionId);
//        int interval = 200;
//        int upperVal = 200;
//        int j = 0;
//
//        List<CompletableFuture<List<com.protean.legislativetracker.yuna.model.Bill>>> completableFutures = new ArrayList<>();
//
//        boolean breakNextLoop = false;
//        // Loop through the bill list 200 at a time
//        while (true) {
//
//            // Instantiate bills that need to be processed
//            List<Bill> billsToProcess = new ArrayList<>();
//
//            // Spin off thread to process bill chunk
//            completableFutures.add(createThreadToProcessItem(legiscanBills, upperVal, j, billsToProcess));
//
//            if (breakNextLoop) { break; }
//
//            upperVal += interval;
//            j += interval;
//            if (upperVal > legiscanBills.size()) {
//                upperVal = legiscanBills.size();
//                breakNextLoop = true;
//            }
//            log.info("Values: upperVal = " + upperVal + " | j = " + j + " | list size = " + legiscanBills.size());
//        }
//        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
//        try {
//            for (CompletableFuture<List<com.protean.legislativetracker.yuna.model.Bill>> completableFuture : completableFutures) {
//                completableFuture.get();
//            }
//        } catch (InterruptedException e) {
//            log.error("Thread was interrupted", e);
//        } catch (ExecutionException e) {
//            log.error("There was an error executing the thread", e);
//        }
    }
//
//    @Async("threadPoolTaskExecutor")
//    public CompletableFuture<List<com.protean.legislativetracker.yuna.model.Bill>> createThreadToProcessItem(
//            List<Bill> legiscanBills, int upperVal, int j, List<Bill> billsToProcess) {
//
//        log.info("Starting thread to update Bills starting at <" + j + "> and ending at <" + upperVal + "> on Thread: "
//                + Thread.currentThread().getName());
//        // Retrieve set of 200 bills
//        for (int i = j ; i < upperVal ; i++) {
//            billsToProcess.add(legiscanBills.get(i));
//        }
//
//        List<com.protean.legislativetracker.yuna.model.Bill> bills =
//                billsToProcess.stream().map(
//                        BillModelMapper::legiscanBillToModelBill).collect(Collectors.toList());
//        billService.saveBills(bills);
//    }

    private void updateRollCalls(Integer sessionId) {
        List<RollCall> rollCalls = LegiscanModelMapper.modelListToLegiscan(
                rollCallRetrievalService.getAllById(
                        Collections.singletonList(sessionId.longValue())),
                RollCall.class);
        rollCallService.saveAllRollCalls(rollCalls);
    }
}
