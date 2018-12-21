package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanBillRetrievalService;
import com.protean.legislativetracker.zidane.service.update.legiscan.LegiscanBillUpdateService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillUpdateServiceTest {

    private static BillService billService;
    private static SessionService sessionService;
    private static LegiscanBillRetrievalService legiscanBillRetrievalService;

    private static BillUpdateService billUpdateService;
    private static List<com.protean.legislativetracker.zidane.model.Bill> legiscanBills;
    private static List<Bill> modelBills;
    private static Integer passedSessions;
    private static List<com.protean.legislativetracker.zidane.model.Bill> expectedBills;
    private static List<Bill> finalModelBills;

    @BeforeClass
    public static void setUp() {
        billService = mock(BillService.class);
        sessionService = mock(SessionService.class);
        legiscanBillRetrievalService = mock(LegiscanBillRetrievalService.class);

        billUpdateService = new LegiscanBillUpdateService(billService, sessionService, legiscanBillRetrievalService, new AsyncService<>());
        HttpRequestService httpRequestService = mock(HttpRequestServiceImpl.class);
        when(httpRequestService.readResponse(any())).thenReturn(JsonFileLoader.readJsonFileAsString(
                "src/test/resources/session_1258_master_list.json"));

        passedSessions = 1258;

        when(httpRequestService.getPojoFromJson(any(), anyString(), anyString())).thenCallRealMethod();


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

        BillRetrievalService service = new LegiscanBillRetrievalService(httpRequestService);
        legiscanBills = service.getAllByExternalId(passedSessions);
        legiscanBills.add(expectedBillThree);

        modelBills = LegiscanModelMapper.modelListToLegiscan(legiscanBills, Bill.class);
        expectedBills = Arrays.asList(expectedBillOne, expectedBillTwo, expectedBillThree);
        finalModelBills = LegiscanModelMapper.modelListToLegiscan(
                expectedBills,
                Bill.class);
        when(sessionService.getSessionIds()).thenReturn(new HashSet<>(Arrays.asList(1258, 1132, 1004)));
    }

    @Test
    public void saveAndReturnBillChanges_ListOfSessions_ChangedBillsReturned() {

        when(legiscanBillRetrievalService.getAllByExternalId(passedSessions)).thenReturn(legiscanBills);
        when(billService.billHasChanged(
                or(or(eq(897595L), eq(897625L)),eq(868785L)),
                or(or(eq("a1236fc65bd20b84d7697beb71bb2530"), eq("75cf511cdaf002865d72ed141d65be75")), eq("bef082ef88a59a9849f0c5ef03ea0c6d")))
        ).thenReturn(true);
        when(legiscanBillRetrievalService.getById(897595)).thenReturn(expectedBills.get(0));
        when(legiscanBillRetrievalService.getById(897625)).thenReturn(expectedBills.get(1));
        when(legiscanBillRetrievalService.getById(868785)).thenReturn(expectedBills.get(2));

        List<Bill> bills = billUpdateService.getUpdatedBills(passedSessions);
        assertNotNull(bills);
        assertTrue(bills.size() > 0);
        assertEquals(3, bills.size());

        verify(legiscanBillRetrievalService, times(1)).getAllByExternalId(passedSessions);
        verify(billService, times(2004)).billHasChanged(anyLong(), anyString());
        verify(legiscanBillRetrievalService, times(3)).getById(anyLong());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveAndReturnBillChanges_InvalidSession_ThrowsIllegalArgumentException() {
        billUpdateService.getUpdatedBills(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveAndReturnBillChanges_InvalidSessions_ThrowsIllegalArgumentException() {
        for(Integer i : Arrays.asList(2, 3, 4)) {
            billUpdateService.getUpdatedBills(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveAndReturnBillChanges_MixedValidAndInvalidSessions_ThrowsIllegalArgumentException() {
        for(Integer i : Arrays.asList(4, 1258, 1132)) {
            billUpdateService.getUpdatedBills(i);
        }
    }

}