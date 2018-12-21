package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.Person;
import com.protean.legislativetracker.yuna.service.PersonService;
import com.protean.legislativetracker.zidane.legiscan.PersonModelMapper;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonUpdateServiceImpl implements PersonUpdateService {

    private static final Logger log = LoggerFactory.getLogger(PersonUpdateServiceImpl.class);

    private PersonService personService;
    private PersonRetrievalService retrievalService;

    // TODO mimc the Bill update service
    public PersonUpdateServiceImpl(PersonService personService,
                                   @Qualifier("legiscanPersonRetrievalService") PersonRetrievalService retrievalService) {
        this.personService = personService;
        this.retrievalService = retrievalService;
    }

    @Override
    public List<Person> saveAllPeopleChanges(List<Long> peopleIds) {

        log.info("Retrieving all people from Legiscan for provided id list.");

        List<com.protean.legislativetracker.zidane.model.Person> legiscanPeople =
                retrievalService.getAllById(peopleIds);
        List<Person> changedPeople = new ArrayList<>();
        for (com.protean.legislativetracker.zidane.model.Person p : legiscanPeople) {
            if (personService.personHasChanged(p.getId(), p.getPersonHash())) {
                changedPeople.add(PersonModelMapper.legiscanPersonToModelPerson(p));
            }
        }

        log.info("Saving changed people to database");
        return personService.savePeople(changedPeople);
    }
}
