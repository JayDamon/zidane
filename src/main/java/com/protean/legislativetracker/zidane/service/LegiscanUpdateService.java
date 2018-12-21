package com.protean.legislativetracker.zidane.service;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.model.BillSponsor;
import com.protean.legislativetracker.yuna.model.LegislativeSession;
import com.protean.legislativetracker.yuna.model.RollCall;
import com.protean.legislativetracker.zidane.service.retrieval.StateRetrievalService;
import com.protean.legislativetracker.zidane.service.update.BillUpdateService;
import com.protean.legislativetracker.zidane.service.update.PersonUpdateService;
import com.protean.legislativetracker.zidane.service.update.RollCallUpdateService;
import com.protean.legislativetracker.zidane.service.update.SessionUpdateService;
import com.protean.legislativetracker.zidane.service.update.legiscan.LegiscanBillUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LegiscanUpdateService implements UpdateService {

    private Logger log = LoggerFactory.getLogger(LegiscanUpdateService.class);

    private StateRetrievalService stateRetrievalService;
    private SessionUpdateService sessionUpdateService;
    private BillUpdateService billUpdateService;
    private PersonUpdateService personUpdateService;
    private RollCallUpdateService rollCallUpdateService;

    public LegiscanUpdateService(StateRetrievalService stateRetrievalService,
                                 SessionUpdateService sessionUpdateService,
                                 LegiscanBillUpdateService billUpdateService,
                                 PersonUpdateService personUpdateService,
                                 RollCallUpdateService rollCallUpdateService) {
        this.stateRetrievalService = stateRetrievalService;
        this.sessionUpdateService = sessionUpdateService;
        this.billUpdateService = billUpdateService;
        this.personUpdateService = personUpdateService;
        this.rollCallUpdateService = rollCallUpdateService;
    }

    @Override
    public void getUpdates() {
        List<String> availableStates = stateRetrievalService.getAll();
        List<LegislativeSession> changedSessions =
                sessionUpdateService.saveAndReturnSessionChanges(availableStates);
        List<Integer> sessionIds = changedSessions.stream().map(
                LegislativeSession::getSessionId).collect(Collectors.toList());

        List<Bill> changedBills = new ArrayList<>();
        for (Integer i : sessionIds) {
            changedBills.addAll(
                    billUpdateService.getUpdatedBills(i)
            );
        }
        updatePeople(changedBills);
        billUpdateService.saveBills(changedBills);
        log.info("Legiscan update complete.");
    }

    @Override
    public void getUpdatesForSession(Integer sessionId) {
        List<Bill> updatedBills = billUpdateService.getUpdatedBills(sessionId);
        updatePeople(updatedBills);
        billUpdateService.saveBills(updatedBills);
        log.info("Legiscan update complete");
    }

    private void updatePeople(List<Bill> changedBills) {
        log.info("Start update people");
        Set<Long> people = new HashSet<>();
        for (Bill b : changedBills) {
            for (BillSponsor p : b.getSponsors()) {
                people.add(p.getPerson().getId());
            }
        }
        personUpdateService.saveAllPeopleChanges(new ArrayList<>(people));
    }

    private void updateRollCalls(List<Bill> changedBills) {
        log.info("Start update roll calls");
        Set<Long> rollcalls = new HashSet<>();
        for (Bill b : changedBills) {
            for (RollCall r : b.getRollCalls()) {
                rollcalls.add(r.getVoteId());
            }
        }
        rollCallUpdateService.saveAllRollCallChanges(new ArrayList<>(rollcalls));
    }
}
