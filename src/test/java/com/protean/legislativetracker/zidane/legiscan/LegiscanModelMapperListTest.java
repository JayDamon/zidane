package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.zidane.model.LegislativeSession;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LegiscanModelMapperListTest {

    private List<LegislativeSession> legislativeSessions;

    @Before
    public void setUp() {
        HttpRequestService httpRequestService = new HttpRequestServiceImpl();
        LegiscanHttpUri legiscanHttpUri =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(LegiscanOperation.GET_SESSION_LIST,
                        "state", "me").build();

        legislativeSessions = httpRequestService.getPojoListFromJson(
                LegislativeSession.class,
                JsonFileLoader.readJsonFileAsString("src/test/resources/maine_session_list.json"),
                "sessions");
    }

    @Test
    public void modelListToLegiscan_MapRequestToModel_MapSuccessful() {
        List<com.protean.legislativetracker.yuna.model.LegislativeSession> legislativeLegislativeSessions = LegiscanModelMapper.modelListToLegiscan(legislativeSessions,
                com.protean.legislativetracker.yuna.model.LegislativeSession.class);

        boolean firstValFound = false;
        boolean secondValFound = false;
        boolean thirdValFound = false;
        boolean fourthValFound = false;
        boolean fifthValFound = false;

        for (com.protean.legislativetracker.yuna.model.LegislativeSession s : legislativeLegislativeSessions) {
            switch (s.getSessionId()) {
                case 1258:
                    assertEquals(19, s.getState().getStateId().intValue());
                    assertEquals(2017, s.getYearStart().intValue());
                    assertEquals(2018, s.getYearEnd().intValue());
                    assertFalse(s.getSpecial());
                    assertEquals("128th Legislature", s.getSessionName());
                    assertEquals("128th Legislature", s.getSessionTitle());
                    assertEquals("50ac4375db05c019d2d743a08926e93a", s.getSessionHash());
                    firstValFound = true;
                    break;
                case 1132:
                    assertEquals(19, s.getState().getStateId().intValue());
                    assertEquals(2015, s.getYearStart().intValue());
                    assertEquals(2016, s.getYearEnd().intValue());
                    assertFalse(s.getSpecial());
                    assertEquals("127th Legislature", s.getSessionName());
                    assertEquals("127th Legislature", s.getSessionTitle());
                    assertEquals("fa9e6cd4d2068b40e90743e3b3c7a911", s.getSessionHash());
                    secondValFound = true;
                    break;
                case 1004:
                    assertEquals(19, s.getState().getStateId().intValue());
                    assertEquals(2013, s.getYearStart().intValue());
                    assertEquals(2014, s.getYearEnd().intValue());
                    assertFalse(s.getSpecial());
                    assertEquals("126th Legislature", s.getSessionName());
                    assertEquals("126th Legislature", s.getSessionTitle());
                    assertEquals("e8d133eab6b75e36b0284f90d4499629", s.getSessionHash());
                    thirdValFound = true;
                    break;
                case 81:
                    assertEquals(19, s.getState().getStateId().intValue());
                    assertEquals(2011, s.getYearStart().intValue());
                    assertEquals(2012, s.getYearEnd().intValue());
                    assertFalse(s.getSpecial());
                    assertEquals("125th Legislature", s.getSessionName());
                    assertEquals("125th Legislature", s.getSessionTitle());
                    assertEquals("f50822f28db52334ec34a4dd6dbbc67c", s.getSessionHash());
                    fourthValFound = true;
                    break;
                case 48:
                    assertEquals(19, s.getState().getStateId().intValue());
                    assertEquals(2009, s.getYearStart().intValue());
                    assertEquals(2010, s.getYearEnd().intValue());
                    assertFalse(s.getSpecial());
                    assertEquals("124th Legislature", s.getSessionName());
                    assertEquals("124th Legislature", s.getSessionTitle());
                    assertEquals("404c09471070096037575236d55807b3", s.getSessionHash());
                    fifthValFound = true;
                    break;
            }
        }
        assertTrue(firstValFound);
        assertTrue(secondValFound);
        assertTrue(thirdValFound);
        assertTrue(fourthValFound);
        assertTrue(fifthValFound);
    }

    @Test
    public void modelListToLegiscan_NullElementInList_ValuesMapped() {

        HttpRequestService requestService = new HttpRequestServiceImpl();
        com.protean.legislativetracker.zidane.model.Bill expectedBillOne =
                requestService.getPojoFromJson(com.protean.legislativetracker.zidane.model.Bill.class,
                        JsonFileLoader.readJsonFileAsString("src/test/resources/bill_897595.json"),
                        "bill");
        com.protean.legislativetracker.zidane.model.Bill expectedBillTwo =
                requestService.getPojoFromJson(com.protean.legislativetracker.zidane.model.Bill.class,
                        JsonFileLoader.readJsonFileAsString("src/test/resources/bill_897625.json"),
                        "bill");
        com.protean.legislativetracker.zidane.model.Bill expectedBillThree =
                requestService.getPojoFromJson(com.protean.legislativetracker.zidane.model.Bill.class,
                        JsonFileLoader.readJsonFileAsString("src/test/resources/bill_868785.json"),
                        "bill");
        List<com.protean.legislativetracker.zidane.model.Bill> legiscanBills = Arrays.asList(expectedBillOne, expectedBillTwo, null);

        List<Bill> modelBills = LegiscanModelMapper.modelListToLegiscan(
                legiscanBills,
                Bill.class);
        assertNotNull(modelBills);
        assertEquals(2, modelBills.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void modelListToLegiscan_NullValuesToMap_ThrowsIllegalArgumentException() {
        LegiscanModelMapper.modelListToLegiscan(null,
                com.protean.legislativetracker.yuna.model.LegislativeSession.class);
    }

    @Test
    public void modelListToLegiscan_EmptyValuesToMap_ReturnsEmptyList() {
        List<com.protean.legislativetracker.yuna.model.LegislativeSession> sessions =
                LegiscanModelMapper.modelListToLegiscan(Collections.emptyList(),
                com.protean.legislativetracker.yuna.model.LegislativeSession.class);
        assertTrue(sessions.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void modelListToLegiscan_NullDestinationType_ThrowsIllegalArgumentException() {
        LegiscanModelMapper.modelListToLegiscan(legislativeSessions,
                null);
    }

}
