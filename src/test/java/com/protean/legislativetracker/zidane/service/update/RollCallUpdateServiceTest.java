package com.protean.legislativetracker.zidane.service.update;
import com.protean.legislativetracker.yuna.service.RollCallService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.model.RollCall;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RollCallUpdateServiceTest {

    @Mock private RollCallService rollCallService;
    @Mock private RollCallRetrievalService rollCallRetrievalService;

    private HttpRequestService requestService;
    private RollCallUpdateService rollCallUpdateService;
    private List<RollCall> updatedRollCalls;
    private List<com.protean.legislativetracker.yuna.model.RollCall> modelRollCalls;

    @Before
    public void setUp() {
        this.requestService = new HttpRequestServiceImpl();
        this.rollCallUpdateService = new RollCallUpdateServiceImpl(rollCallService, rollCallRetrievalService);

        updatedRollCalls = new ArrayList<>();
        updatedRollCalls.add(getRolLCallFromFile("305955"));
        updatedRollCalls.add(getRolLCallFromFile("305957"));
        updatedRollCalls.add(getRolLCallFromFile("305973"));
        updatedRollCalls.add(getRolLCallFromFile("305974"));
        updatedRollCalls.add(getRolLCallFromFile("305975"));
        updatedRollCalls.add(getRolLCallFromFile("408285"));
        updatedRollCalls.add(getRolLCallFromFile("409667"));
        updatedRollCalls.add(getRolLCallFromFile("413193"));
        updatedRollCalls.add(getRolLCallFromFile("416151"));
        updatedRollCalls.add(getRolLCallFromFile("416859"));
        updatedRollCalls.add(getRolLCallFromFile("418554"));

        modelRollCalls = LegiscanModelMapper.modelListToLegiscan(
                updatedRollCalls,
                com.protean.legislativetracker.yuna.model.RollCall.class
        );
    }

    @Test
    public void saveAllRolLCallChanges() {

        List<Long> rollCalls = Arrays.asList(305955L, 305957L, 305973L, 305974L, 305975L, 408285L, 409667L, 413193L , 416151L, 416859L, 418554L);

        when(rollCallRetrievalService.getAllById(rollCalls)).thenReturn(updatedRollCalls);
        when(rollCallService.saveAllRollCalls(any())).thenReturn(modelRollCalls);

        List<com.protean.legislativetracker.yuna.model.RollCall> savedRollCalls =
                rollCallUpdateService.saveAllRollCallChanges(rollCalls);

        assertNotNull(savedRollCalls);
        assertTrue(savedRollCalls.size() > 0);
        assertEquals(11, savedRollCalls.size());

        verify(rollCallRetrievalService, times(1)).getAllById(any());
        verify(rollCallService, times(1)).saveAllRollCalls(any());
    }

    private RollCall getRolLCallFromFile(String rollCallId) {
        return requestService.getPojoFromJson(RollCall.class, JsonFileLoader.readJsonFileAsString("src/test/resources/roll_call/rollcall_" + rollCallId + ".json"), "roll_call");
    }
}