package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.model.RollCall;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LegiscanRollCallRetrievalServiceTest {

    private RollCallRetrievalService rollCallRetrievalService;
    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        this.httpRequestService = new HttpRequestServiceImpl();
        this.rollCallRetrievalService = new LegiscanRollCallRetrievalService(httpRequestService);
    }

    @Test
    public void getAllRollCalls_ValidRollCallId_ReturnedRollCall() {
        Long rollCallOneId = 305955L;
        Long rollCallTwoId = 305957L;
        List<RollCall> rollCalls = rollCallRetrievalService.getAllById(
                Arrays.asList(rollCallOneId, rollCallTwoId)
        );
        assertNotNull(rollCalls);
        assertTrue(rollCalls.size() > 0);
        rollCalls.forEach(System.out::println);
    }

}