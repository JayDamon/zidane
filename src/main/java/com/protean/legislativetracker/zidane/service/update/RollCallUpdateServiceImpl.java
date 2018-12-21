package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.RollCall;
import com.protean.legislativetracker.yuna.service.RollCallService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollCallUpdateServiceImpl implements RollCallUpdateService {
    
    private static final Logger log = LoggerFactory.getLogger(RollCallUpdateServiceImpl.class);
    
    private RollCallService rollCallService;
    private RollCallRetrievalService retrievalService;

    // TODO mimic the Bill update service
    public RollCallUpdateServiceImpl(RollCallService rollCallService,
                                     @Qualifier("legiscanRollCallRetrievalService") RollCallRetrievalService retrievalService) {
        this.rollCallService = rollCallService;
        this.retrievalService = retrievalService;
    }

    @Override
    public List<RollCall> saveAllRollCallChanges(List<Long> rollCallIds) {
        
        log.info("Retrieving all roll calls from Legiscan for provided id list.");
        
        List<com.protean.legislativetracker.zidane.model.RollCall> rollCalls =
                retrievalService.getAllById(rollCallIds);
        List<RollCall> changedRollCalls = LegiscanModelMapper.modelListToLegiscan(rollCalls, RollCall.class);
        
        log.info("Saving votes to database");

        return rollCallService.saveAllRollCalls(changedRollCalls);
    }
}
