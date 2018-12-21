package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protean.legislativetracker.zidane.legiscan.LegiscanHttpUri;
import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.model.LegislativeSession;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LegiscanBillRetrievalService extends LegiscanRetrievalService<Bill> implements BillRetrievalService {

    private Logger log = LoggerFactory.getLogger(LegiscanBillRetrievalService.class);

    public LegiscanBillRetrievalService(HttpRequestService httpRequestService) {
        super(Bill.class, httpRequestService);
    }

    @Override
    public List<Bill> getAllByItems(List<String> states) {
        List<Bill> bills = new ArrayList<>();
        for (String s : states) {
            LegiscanRetrievalService<LegislativeSession> sessionLegiscanRetrievalService = new LegiscanRetrievalService<LegislativeSession>(LegislativeSession.class, httpRequestService) {
                @Override
                public List<LegislativeSession> getList(LegiscanOperation operation, String nodeName, String parameterName, String parameter) {
                    return super.getList(operation, nodeName, parameterName, parameter);
                }
            };
            List<LegislativeSession> sessions = sessionLegiscanRetrievalService.getList(LegiscanOperation.GET_SESSION_LIST, "sessions", "state", s);
            for (LegislativeSession session : sessions) {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("state", s);
                parameters.put("id", session.getSessionId().toString());
                bills.addAll(
                        getList(
                                LegiscanOperation.GET_MASTER_LIST,
                                "masterlist",
                                parameters
                        )
                );
            }
        }
        return bills;
    }

    @Override
    public List<Bill> getAllByExternalId(Integer id) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", String.valueOf(id));
        return new ArrayList<>(getList(
                LegiscanOperation.GET_MASTER_LIST, "masterlist", parameters));
    }

    @Override
    public List<Bill> getAllById(List<Long> ids) {
        return null;
    }

    @Override
    public List<Bill> getAllByEntity(List<Bill> entities) {
        List<Bill> completeBills = new ArrayList<>();
        for (Bill bill : entities) {
            completeBills.add(getByEntity(bill));
        }
        return completeBills;
    }

    @Override
    public Bill getById(long billId) {
        return getItem(LegiscanOperation.GET_BILL, "bill", "id", String.valueOf(billId));
    }

    @Override
    public Bill getByEntity(Bill bill) {
        return getById(bill.getBillId().intValue());
    }

    @Override
    public List<Bill> getList(LegiscanOperation operation, String nodeName,
                              Map<String, String> parameters) {
        AtomicReference<String> secondParam = new AtomicReference<>();
        parameters.forEach((k, v) -> {
            if (!k.equals("state")) {
                secondParam.set(k);}
        });
        LegiscanHttpUri.LegiscanHttpUriBuilder legiscanHttpUri =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(operation, "state", parameters.get("state")).addParameter(secondParam.get(), parameters.get(secondParam.get()));
        String json = httpRequestService.readResponse(legiscanHttpUri.build().getConnection());
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json).get(nodeName);
            int sessionId = node.get("session").get("session_id").intValue();
            String masterList = node.toString();
            List<Bill> bills = new ArrayList<>();
            node.fieldNames().forEachRemaining(n -> {
                if (n.equals("session")) { return; }
                Bill bill = httpRequestService.getPojoFromJson(Bill.class, masterList, n);
                bill.setSessionId(sessionId);
                bills.add(bill);
            });
            return bills;
        } catch (IOException e) {
            String msg = "Unable to find node from json: " + json;
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }
}
