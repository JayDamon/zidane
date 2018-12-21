package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.zidane.model.Person;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonModelMapperTest {

    private Person personWithNoPartyId;

    @Before
    public void setUp() {

        HttpRequestService requestService = new HttpRequestServiceImpl();

        personWithNoPartyId = requestService.getPojoFromJson(
                Person.class,
                JsonFileLoader.readJsonFileAsString("src/test/resources/people/person_zero_party_id.json"),
                "person"
        );
    }

    @Test
    public void legiscanPersonToModelPerson_PersonWithZeroAsPartyId_PersonMapped() {
        com.protean.legislativetracker.yuna.model.Person person =
                PersonModelMapper.legiscanPersonToModelPerson(personWithNoPartyId);
        assertEquals(8903, person.getId().longValue());
        assertEquals("puhhaeh6", person.getPersonHash());
        assertEquals(19, person.getState().getStateId().intValue());
        assertNull(person.getParty());
        assertEquals(1, person.getRole().getId().intValue());
        assertEquals("Rep", person.getRole().getRoleAbbreviation());
        assertEquals("Soctomah", person.getName());
        assertEquals("", person.getFirstName());
        assertEquals("", person.getMiddleName());
        assertEquals("Soctomah", person.getLastName());
        assertEquals("", person.getSuffix());
        assertEquals("", person.getNickname());
        assertEquals("", person.getDistrict());
        assertEquals(0, person.getFollowTheMoneyId().longValue());
        assertEquals(0, person.getVoteSmartId().longValue());
        assertNull(person.getOpenSecretsId());
        assertEquals("", person.getBallotpedia());
//        assertEquals(0, person.getC); // TODO This value needs to be mapped
        assertNull(person.getCommittee());
    }


}