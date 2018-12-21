//package com.protean.legislativetracker.zidane.service;
//
//import com.protean.legislativetracker.yuna.model.LegislativeSession;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//
//public class ModelMapServiceImplTest {
//
//    private ModelMapService modelMapService = new ModelMapServiceImpl();
//
//    @Test
//    public void getModelFromJson_ValidSession_ReturnsSession() {
//        String json = "{" +
//                "\"state\":{\"stateId\":null,\"abbreviation\":\"me\",\"stateName\":null,\"biennium\":false," +
//                "\"carryOver\":null,\"capitol\":null,\"latitude\":null,\"longitude\":null}," +
//                "\"session_id\":1258,\"year_start\":2017,\"year_end\":2018,\"special\":0," +
//                "\"session_name\":\"128th Legislature\",\"session_title\":\"128th Legislature\"}";
//        LegislativeSession returnSession = modelMapService.getModelFromJson(LegislativeSession.class, json);
//        validateSession(returnSession);
//    }
//
//    @Test
//    public void getListModelFromJson_ValidSession_ReturnSessionList() {
//        String json = "[{\"state\":{\"stateId\":null,\"abbreviation\":\"me\",\"stateName\":null,\"biennium\":false," +
//                "\"carryOver\":null,\"capitol\":null,\"latitude\":null,\"longitude\":null}," +
//                "\"session_id\":1258,\"year_start\":2017,\"year_end\":2018,\"special\":0," +
//                "\"session_name\":\"128th Legislature\",\"session_title\":\"128th Legislature\"}]";
//
//        List<LegislativeSession> sessions = modelMapService.getListModelFromJson(LegislativeSession.class, json);
//        LegislativeSession session = sessions.get(0);
//        validateSession(session);
//    }
//
//    private void validateSession(LegislativeSession returnSession) {
//        assertEquals("me", returnSession.getState().getAbbreviation());
//        assertEquals(1258, returnSession.getSessionId().intValue());
//        assertEquals(2017, returnSession.getYearStart().intValue());
//        assertEquals(2018, returnSession.getYearEnd().intValue());
//        assertFalse(returnSession.getSpecial());
//        assertEquals("128th Legislature", returnSession.getSessionName());
//        assertEquals("128th Legislature", returnSession.getSessionTitle());
//    }
//
//}