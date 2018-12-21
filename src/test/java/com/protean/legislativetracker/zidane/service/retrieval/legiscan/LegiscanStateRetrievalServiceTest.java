package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.StateRetrievalService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class LegiscanStateRetrievalServiceTest {

    private StateRetrievalService stateRetrievalService;
    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        this.httpRequestService = new HttpRequestServiceImpl();
        stateRetrievalService = new LegiscanStateRetrievalService(httpRequestService);
    }

    @Test
    public void getAllAvailableStates_BasicRequest_ReturnsState() throws IOException {
        List<String> states = stateRetrievalService.getAll();
        assertNotNull(states);
        assertTrue(states.contains("ME"));
    }
}