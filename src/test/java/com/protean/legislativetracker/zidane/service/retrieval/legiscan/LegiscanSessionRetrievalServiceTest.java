package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.model.LegislativeSession;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.SessionRetrievalService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class LegiscanSessionRetrievalServiceTest {

    private SessionRetrievalService sessionRetrievalService;
    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        this.httpRequestService = new HttpRequestServiceImpl();
        sessionRetrievalService = new LegiscanSessionRetrievalService(httpRequestService);
    }

    @Test
    public void getAllSessions_ValidStates_ReturnsSessionList() {
        List<LegislativeSession> sessions =
                sessionRetrievalService.getAllByItem(Collections.singletonList("ME"));
        assertNotNull(sessions);
        assertTrue(sessions.size() > 0);
        for (LegislativeSession l : sessions) {
            assertEquals(19, l.getStateId().intValue());
        }
    }

    @Test
    public void getAllSessions_NoStateSpecified_ReturnFullSessionList() {
        List<LegislativeSession> sessions = sessionRetrievalService.getAll();
        assertNotNull(sessions);
        assertTrue(sessions.size() > 0);
        Set<Integer> states = new HashSet<>();
        sessions.forEach(s -> states.add(s.getStateId()));
        assertEquals(53, states.size());
    }
}