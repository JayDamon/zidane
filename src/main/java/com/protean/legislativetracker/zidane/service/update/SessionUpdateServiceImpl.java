package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.LegislativeSession;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.service.retrieval.SessionRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionUpdateServiceImpl implements SessionUpdateService {

    private static final Logger log = LoggerFactory.getLogger(SessionUpdateServiceImpl.class);

    private SessionService sessionService;
    private SessionRetrievalService retrievalService;

    public SessionUpdateServiceImpl(SessionService sessionService, SessionRetrievalService retrievalService) {
        this.sessionService = sessionService;
        this.retrievalService = retrievalService;
    }

    @Override
    public List<LegislativeSession> saveAndReturnSessionChanges(List<String> states) {

        log.info("Retrieving sessions for states: " + states.toString());

        List<com.protean.legislativetracker.zidane.model.LegislativeSession> legiscanSessions =
                retrievalService.getAllByItem(states);

        List<LegislativeSession> changedSessions = new ArrayList<>();
        for (com.protean.legislativetracker.zidane.model.LegislativeSession session : legiscanSessions) {
            if (sessionService.sessionHasChanged(session.getSessionId(), session.getSessionHash())) {
                changedSessions.add(
                        LegiscanModelMapper.modelToLegiscan(session, LegislativeSession.class)
                );
            }
        }

        log.info("Saving sessions");

        return sessionService.saveSessions(changedSessions);
    }
}
