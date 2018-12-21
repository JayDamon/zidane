package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.model.BillHistory;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BillModelMapperTest {

    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        httpRequestService = new HttpRequestServiceImpl();

    }

    @Test
    public void legiscanBillToModelBill_BillHasCommitee_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_53998_has_committee.json");
        assertNotNull(bill);
        assertEquals(2155, bill.getCommittee().getId().intValue());
        assertEquals('J', bill.getCommittee().getBody().getAbbreviation().charValue());
        assertEquals(119, bill.getCommittee().getBody().getId().intValue());
        assertEquals("Energy, Utilities And Technology", bill.getCommittee().getName());
    }

    @Test
    public void legiscanBilLToModelBil_BillHasZeroStatusId_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_191682_no_progress.json");
        assertNotNull(bill);
        assertNull(bill.getStatus());
    }

    @Test
    public void legiscanBillToModelBill_BillHasHistoryWithEmptyBody_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_191283_body_id_zero.json");
        assertNotNull(bill);
        List<BillHistory> histories = new ArrayList<>(bill.getHistories());
        BillHistory historyOne = histories.get(0);
        BillHistory historyTwo = histories.get(1);
        BillHistory historyThree = histories.get(2);
        assertNull(historyOne.getBody());
        assertNull(historyTwo.getBody());
        assertNull(historyThree.getBody());
    }

    @Test
    public void legiscanBillToModelBill_BillHasFieldsWithZeroDate_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_1005536_zero_date.json");
        assertNotNull(bill);
    }

    @Test
    public void legiscanBillToModelBill_EventIdFour_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_752023.json");
        assertNotNull(bill);
    }

    @Test
    public void legiscanBillToModelBill_BillLD1807_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_635867.json");
        assertNotNull(bill);
    }

    @Test
    public void legiscanBillToModelBill_BillLD1248_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_539250.json");
        assertNotNull(bill);
        assertNotNull(bill.getLegislativeSession());
        System.out.println(bill);
    }


    private Bill getBill(String filePath) {
        return BillModelMapper.legiscanBillToModelBill(httpRequestService.getPojoFromJson(
                com.protean.legislativetracker.zidane.model.Bill.class,
                JsonFileLoader.readJsonFileAsString(filePath),
                "bill"));
    }

}