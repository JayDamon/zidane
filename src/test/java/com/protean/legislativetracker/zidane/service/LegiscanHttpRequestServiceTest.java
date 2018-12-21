package com.protean.legislativetracker.zidane.service;

import com.protean.legislativetracker.zidane.legiscan.LegiscanHttpUri;
import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.model.LegislativeSession;
import com.protean.legislativetracker.zidane.model.Text;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LegiscanHttpRequestServiceTest {

    private String expectedJson;
    private LegiscanHttpUri request;
    private String json;
    private String bill987860;
    private String validJson;
    private String billWithMissingElementsJson;
    private String legiscanJsonSessions;

    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        this.httpRequestService = new HttpRequestServiceImpl();
        expectedJson = JsonFileLoader.readJsonFileAsString("src/test/resources/get_bill_expected_response.json");
        request = new LegiscanHttpUri.LegiscanHttpUriBuilder(
                LegiscanOperation.GET_BILL, "id", "897860").build();
        json = httpRequestService.readResponse(request.getConnection());
        validJson = JsonFileLoader.readJsonFileAsString("src/test/resources/bill_897595.json");
        billWithMissingElementsJson = JsonFileLoader.readJsonFileAsString("src/test/resources/bill_868785.json");
        bill987860 = JsonFileLoader.readJsonFileAsString("src/test/resources/bill_897860.json");
        legiscanJsonSessions = JsonFileLoader.readJsonFileAsString("src/test/resources/maine_session_list.json");
    }

    @Test
    public void statusOK_ValidJsonString_ReturnsTrue() {
        assertTrue(httpRequestService.statusOk(json));
    }

    @Test
    public void statusOK_IncompleteJsonString_ReturnsTrue() {
        assertTrue(httpRequestService.statusOk(json));
    }

    @Test(expected = IllegalArgumentException.class)
    public void statusOK_NullValue_ThrowsIllegalArgumentException() {
        httpRequestService.statusOk(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void statusOK_EmptyString_ThrowsIllegalArgumentException() {
        httpRequestService.statusOk("");
    }

    @Test
    public void statusOk_SessionList_StatusOk() {
        LegiscanHttpUri legiscanHttpUri = new LegiscanHttpUri.LegiscanHttpUriBuilder(
                LegiscanOperation.GET_SESSION_LIST, "state", "me").build();
        assertTrue(httpRequestService.statusOk(httpRequestService.readResponse(legiscanHttpUri.getConnection())));
    }

    @Test
    public void getPojoFromJson_BillJson_ReturnsMappedBill() {
        Bill bill = httpRequestService.getPojoFromJson(Bill.class, bill987860, "bill");
        assertEquals(897860, bill.getBillId().longValue());
    }

    @Test
    public void getPojoFromJson_IncompleteJsonTree_MapsAvailableValues() {
        Bill bill = httpRequestService.getPojoFromJson(Bill.class, billWithMissingElementsJson, "bill");
        assertNotNull(bill);
        assertEquals(868785, bill.getBillId().longValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoFromJson_NullJsonValue_ThrowsIllegalArgumentException() {
        httpRequestService.getPojoFromJson(Bill.class, null, "bill");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoFromJson_EmptyJsonValue_ThrowsIllegalArgumentException() {
        httpRequestService.getPojoFromJson(Bill.class, "", "bill");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoFromJson_NullNodeName_ThrowsIllegalArgumentException() {
        httpRequestService.getPojoFromJson(Bill.class, bill987860, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoFromJson_EmptyStringNodeName_ThrowsIllegalArgumentException() {
        httpRequestService.getPojoFromJson(Bill.class, bill987860, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoFromJson_NullMappedClass_ThrowsIllegalArgumentException() {
        httpRequestService.getPojoFromJson(null, bill987860, "bill");
    }

    @Test
    public void getPojoFromJson_BillJson_ReturnsMappedBillWithCorrectTime() {
        Bill bill = httpRequestService.getPojoFromJson(Bill.class, bill987860, "bill");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Time expectedTime = null;
        try {
            long ms = sdf.parse("13:30").getTime();
            expectedTime = new Time(ms);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time finalExpectedTime = expectedTime;
        AtomicBoolean valueFound = new AtomicBoolean(false);
        bill.getCalendars().forEach(time -> {
            if (finalExpectedTime != null && time.getTime().getTime() == finalExpectedTime.getTime()) {
                valueFound.set(true);
            }
        });
        assertTrue(valueFound.get());
    }

    @Test
    public void getPojoListFromJson_SessionJson_ReturnsMappedSessionList() {
        LegiscanHttpUri legiscanHttpUri = new LegiscanHttpUri.LegiscanHttpUriBuilder(
                LegiscanOperation.GET_SESSION_LIST, "state", "me").build();
        List<LegislativeSession> legislativeSessions = httpRequestService.getPojoListFromJson(LegislativeSession.class,
                legiscanJsonSessions, "sessions");
        assertEquals(1258, legislativeSessions.get(0).getSessionId().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoListFromJson_NullMappedClass_ReturnsMappedSessionList() {
        httpRequestService.getPojoListFromJson(null,
                legiscanJsonSessions, "sessions");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoListFromJson_NullJson_ReturnsMappedSessionList() {
        httpRequestService.getPojoListFromJson(LegislativeSession.class,
                null, "sessions");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoListFromJson_EmptyJsonString_ReturnsMappedSessionList() {
        httpRequestService.getPojoListFromJson(LegislativeSession.class,
                "", "sessions");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoListFromJson_NullNodeName_ReturnsMappedSessionList() {
        httpRequestService.getPojoListFromJson(LegislativeSession.class,
                legiscanJsonSessions, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPojoListFromJson_EmptyNodeName_ReturnsMappedSessionList() {
        httpRequestService.getPojoListFromJson(LegislativeSession.class,
                legiscanJsonSessions, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void readResponse_NullConnection_ThrowsIllegalArgumentException() {
        httpRequestService.readResponse(null);
    }
    
    @Test
    public void getPojoFromJson_BillWithZeroDate_MapsBill() {

    }

}