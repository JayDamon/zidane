package com.protean.legislativetracker.zidane.service.retrieval.file;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class FilePersonRetrievalServiceTest {

    private FilePersonRetrievalService retrievalService;

    @Before
    public void setUp() {
        retrievalService = new FilePersonRetrievalService(
                "D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
    }

    @Test
    public void getAllPeopleUpdatesForBills() {
        assertEquals(193,
                retrievalService.getAllById(Collections.singletonList(1132L)).size());
    }
}