package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.model.Person;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LegiscanPersonRetrievalServiceTest {

    private PersonRetrievalService personRetrievalService;
    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        this.httpRequestService = new HttpRequestServiceImpl();
        this.personRetrievalService = new LegiscanPersonRetrievalService(httpRequestService);
    }

    @Test
    public void getAllPeopleUpdatesForBills_ValidPersonId_ReturnedPeopleForSession() {
        Long personOneId = 15889L;
        Long personTwoId = 18178L;
        List<Person> people = personRetrievalService.getAllById(Arrays.asList(personOneId, personTwoId));
        assertNotNull(people);
        assertTrue(people.size() > 0);
        for (Person p : people) {
            assertEquals(19, p.getStateId().intValue());
        }
    }
}