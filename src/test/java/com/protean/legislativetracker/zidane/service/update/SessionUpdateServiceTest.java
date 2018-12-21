package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.LegislativeSession;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.retrieval.SessionRetrievalService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionUpdateServiceTest {

    @Mock SessionService sessionService;
    @Mock SessionRetrievalService retrievalService;

    private HttpRequestService requestService;
    private SessionUpdateService sessionUpdateService;
    private List<com.protean.legislativetracker.zidane.model.LegislativeSession> legiscanSessions;
    private List<LegislativeSession> modelSessions;

    @Before
    public void setUp() {
        this.requestService = new HttpRequestServiceImpl();
        this.sessionUpdateService = new SessionUpdateServiceImpl(sessionService, retrievalService);

        legiscanSessions = requestService.getPojoListFromJson(
                com.protean.legislativetracker.zidane.model.LegislativeSession.class,
                JsonFileLoader.readJsonFileAsString("src/test/resources/sessions.json"),
                "sessions");
        modelSessions = LegiscanModelMapper.modelListToLegiscan(
                legiscanSessions,
                LegislativeSession.class);
    }

    @Test
    @DirtiesContext
    public void getSessionList_ProvideActiveState_ReturnStatesSession() {
        List<String> states = Collections.singletonList("me");
        List<LegislativeSession> returnedSessions = Arrays.asList(
                modelSessions.get(0), modelSessions.get(1), modelSessions.get(2));
        when(retrievalService.getAllByItem(states)).thenReturn(legiscanSessions);
//        when(sessionService.sessionHasChanged(or(eq(1468), eq(1254)), or(eq("504ec11c02414fc9f4b8a22a36b18beb"), eq("69dc7cbe4c85a5072cf2361646f90bde")))).thenReturn(true);
        when(sessionService.sessionHasChanged(or(not(eq(1468)), not(eq(1254))), or(not(eq("504ec11c02414fc9f4b8a22a36b18beb")), not(eq("69dc7cbe4c85a5072cf2361646f90bde"))))).thenReturn(false);
        when(sessionService.saveSessions(anyList())).thenReturn(returnedSessions);

        List<LegislativeSession> sessions = sessionUpdateService.saveAndReturnSessionChanges(states);

        assertNotNull(sessions);
        assertTrue(sessions.size() > 0);
        assertEquals(3, sessions.size());

        verify(retrievalService, times(1)).getAllByItem(states);
        verify(sessionService, times(514)).sessionHasChanged(anyInt(), anyString());
        verify(sessionService, times(1)).saveSessions(anyList());
    }

}