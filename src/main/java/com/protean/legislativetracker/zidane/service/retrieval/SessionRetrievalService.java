package com.protean.legislativetracker.zidane.service.retrieval;

import com.protean.legislativetracker.zidane.model.LegislativeSession;

import java.util.List;

public interface SessionRetrievalService extends RetrievalService {

    List<LegislativeSession> getAll();

    List<LegislativeSession> getAllByItem(List<String> states);

}
