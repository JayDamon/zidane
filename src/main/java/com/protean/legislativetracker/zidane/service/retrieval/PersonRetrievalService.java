package com.protean.legislativetracker.zidane.service.retrieval;

import com.protean.legislativetracker.zidane.model.Person;

import java.util.List;

public interface PersonRetrievalService extends RetrievalService {

    List<Person> getAllById(List<Long> ids);

}
