package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.Person;

import java.util.List;

public interface PersonUpdateService {

    List<Person> saveAllPeopleChanges(List<Long> changedBillIds);

}
