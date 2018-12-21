package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.service.PersonService;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapper;
import com.protean.legislativetracker.zidane.model.Person;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonUpdateServiceImplTest {

    @Mock private PersonService personService;
    @Mock private PersonRetrievalService personRetrievalService;

    private HttpRequestService requestService;
    private PersonUpdateService personUpdateService;
    private List<Person> updatedPeople;
    private List<com.protean.legislativetracker.yuna.model.Person> modelPeople;

    @Before
    public void setUp() {
        this.requestService = new HttpRequestServiceImpl();
        this.personUpdateService = new PersonUpdateServiceImpl(personService, personRetrievalService);

        updatedPeople = requestService.getPojoListFromJson(
                Person.class,
                JsonFileLoader.readJsonFileAsString("src/test/resources/people/changed_people_test.json"),
                "people"
        );
        modelPeople = LegiscanModelMapper.modelListToLegiscan(
                updatedPeople,
                com.protean.legislativetracker.yuna.model.Person.class
        );
    }

    @Test
    public void saveAllPeopleChanges_ValidPeopleIds_ReturnPeople() {
        List<Long> changedPeople = Arrays.asList(15889L, 18178L, 10332L);

        when(personRetrievalService.getAllById(changedPeople)).thenReturn(updatedPeople);
        when(personService.personHasChanged(15889L, "gcjl9rka")).thenReturn(true);
        when(personService.personHasChanged(18178L, "bfierm7m")).thenReturn(true);
        when(personService.personHasChanged(10332L, "vwvo6f7d")).thenReturn(true);
        when(personService.savePeople(any())).thenReturn(modelPeople);

        List<com.protean.legislativetracker.yuna.model.Person> people =
                personUpdateService.saveAllPeopleChanges(changedPeople);

        assertNotNull(people);
        assertTrue(people.size() > 0);
        assertEquals(3, people.size());

        verify(personRetrievalService, times(1)).getAllById(any());
        verify(personService, times(3)).personHasChanged(anyLong(), anyString());
        verify(personService, times(1)).savePeople(any());
    }
}