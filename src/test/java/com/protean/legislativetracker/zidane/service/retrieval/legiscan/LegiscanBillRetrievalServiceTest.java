package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.yuna.model.LegislativeSession;
import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LegiscanBillRetrievalServiceTest {

    private static BillRetrievalService billRetrievalService;

    @BeforeClass
    public static void setUp() {
        HttpRequestService httpRequestService = new HttpRequestServiceImpl();
        billRetrievalService = new LegiscanBillRetrievalService(httpRequestService);
    }

    @Test
    public void getAllBills_ValidSession_ReturnBillsForSession() {
        LegislativeSession session = new LegislativeSession();
        session.setSessionId(1258);
        List<Bill> bills = billRetrievalService.getAllByExternalId(1258);
        assertNotNull(bills);
        assertTrue(bills.size() > 0);
        for (Bill b : bills) {
            assertEquals(1258, b.getSessionId().intValue());
        }
    }

    @Test
    public void getAllBills_ValidState_ReturnsBillsForState() {
        List<Bill> bills = billRetrievalService.getAllByItems(Collections.singletonList("ME"));
        assertNotNull(bills);
        assertTrue(bills.size() > 0);
        assertNotNull(bills.get(0).getSessionId());
    }

    @Test
    public void getAllBillData_ValidBills_ReturnsCompleteBill() {
        Bill passedBill = new Bill();
        passedBill.setBillId(897860L);
        Bill passedBill2 = new Bill();
        passedBill2.setBillId(897595L);
        List<Bill> bills = billRetrievalService.getAllByEntity(Arrays.asList(passedBill, passedBill2));
        assertNotNull(bills);
        assertEquals(2, bills.size());
        Bill billToCheck = bills.get(0);
        assertEquals(897860, billToCheck.getBillId().longValue());
        assertEquals(19, billToCheck.getStateId().intValue());
        assertNotNull(billToCheck.getChangeHash());
        assertNotNull(billToCheck.getLegislativeSession());
        assertNotNull(billToCheck.getStatusDate());
        assertNotNull(billToCheck.getTexts());
        assertNotNull(billToCheck.getCalendars());
    }
}