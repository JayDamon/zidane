package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.model.RollCall;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegiscanRollCallRetrievalService extends LegiscanRetrievalService<RollCall> implements RollCallRetrievalService {

    public LegiscanRollCallRetrievalService(HttpRequestService httpRequestService) {
        super(RollCall.class, httpRequestService);
    }

    @Override
    public List<RollCall> getAllById(List<Long> ids) {
        List<RollCall> rollCalls = new ArrayList<>();
        for (Long id : ids) {
            rollCalls.add(
                    getItem(
                            LegiscanOperation.GET_ROLL_CALL,
                            "roll_call",
                            "id",
                            id.toString()
                    )
            );
        }
        return rollCalls;
    }
}
