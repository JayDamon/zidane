package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.yuna.model.Person;
import com.protean.legislativetracker.zidane.utilities.ArgumentValidator;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonModelMapper {

    private static final Logger log = LoggerFactory.getLogger(PersonModelMapper.class);

    public static Person legiscanPersonToModelPerson(com.protean.legislativetracker.zidane.model.Person person) {
        ArgumentValidator.validateArgument(person == null, "Value to map must not be null", log);

        ModelMapper modelMapper = new ModelMapper();

        Condition<Integer, Integer> notZero = ctx -> ctx.getSource() != 0;

        TypeMap<com.protean.legislativetracker.zidane.model.Person, Person> typeMap =
                modelMapper.createTypeMap(com.protean.legislativetracker.zidane.model.Person.class, Person.class);
        typeMap.addMappings(mapping -> mapping.when(notZero).<Integer>map(
                com.protean.legislativetracker.zidane.model.Person::getCommitteeId,
                (destPerson, id) -> destPerson.getCommittee().setId(id)
        ));

        Person mappedPerson = typeMap.map(person);
        if (mappedPerson.getParty().getId() == 0) { mappedPerson.setParty(null); };

        return mappedPerson;

    }

}
