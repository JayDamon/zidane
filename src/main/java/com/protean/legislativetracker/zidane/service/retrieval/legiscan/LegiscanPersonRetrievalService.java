package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.model.Person;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegiscanPersonRetrievalService extends LegiscanRetrievalService<Person> implements PersonRetrievalService {

    private static final Logger log = LoggerFactory.getLogger(LegiscanPersonRetrievalService.class);

    public LegiscanPersonRetrievalService(HttpRequestService httpRequestService) {
        super(Person.class, httpRequestService);
    }

    @Override
    public List<Person> getAllById(List<Long> ids) {
        List<Person> people = new ArrayList<>();
        for (Long id : ids) {
            people.add(
                    getItem(
                            LegiscanOperation.GET_PERSON,
                            "person",
                            "id",
                            id.toString()
                    )
            );
        }
        return people;
    }

}
