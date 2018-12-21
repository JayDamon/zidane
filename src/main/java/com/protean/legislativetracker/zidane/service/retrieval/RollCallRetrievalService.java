package com.protean.legislativetracker.zidane.service.retrieval;

import com.protean.legislativetracker.zidane.model.RollCall;

import java.util.List;

public interface RollCallRetrievalService extends RetrievalService {

    List<RollCall> getAllById(List<Long> ids);

}
