package com.protean.legislativetracker.zidane.service.retrieval.file;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class FileRollCallRetrievalServiceTest {

    private FileRollCallRetrievalService retrievalService;

    @Before
    public void setUp() {
        retrievalService = new FileRollCallRetrievalService(
                "D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
    }

    @Test
    public void getAllRollCalls_ValidSession_ReturnsAllRolLCalls() {
        assertEquals(1263,
                retrievalService.getAllById(Collections.singletonList(1258L)).size());
    }

}