package com.protean.legislativetracker.zidane.service;

import org.junit.Test;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;

import static org.junit.Assert.*;

public class LegiscanServiceTest {

    @Test
    public void test() {
        assertEquals("{\"status\":\"OK\",\"states\":[\"ME\",\"US\"]}",
                new JsonFileLoader().readJsonFileAsString("src/test/resources/available_states.json"));
    }

    // TODO validate JSON tree for all legiscan operations needed

}