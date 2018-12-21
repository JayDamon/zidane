package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.model.LegislativeSession;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.retrieval.SessionRetrievalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegiscanSessionRetrievalService extends LegiscanRetrievalService<LegislativeSession>
        implements SessionRetrievalService {

    public LegiscanSessionRetrievalService(HttpRequestService httpRequestService) {
        super(LegislativeSession.class, httpRequestService);
    }

    @Override
    public List<LegislativeSession> getAll() {
        return getList(LegiscanOperation.GET_SESSION_LIST,"sessions");
    }

    @Override
    public List<LegislativeSession> getAllByItem(List<String> states) {
        List<LegislativeSession> sessions = new ArrayList<>();
        for (String state : states) {
            sessions.addAll(getList(LegiscanOperation.GET_SESSION_LIST, "sessions", "state", state));
        }
        return sessions;
    }
}
