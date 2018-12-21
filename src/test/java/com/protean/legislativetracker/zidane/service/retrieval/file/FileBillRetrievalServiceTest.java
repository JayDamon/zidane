package com.protean.legislativetracker.zidane.service.retrieval.file;

import com.protean.legislativetracker.zidane.service.update.AsyncService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileBillRetrievalServiceTest {

    private FileBillRetrievalService fileRetrievalService;

    
    @Before
    public void setUp() {
        fileRetrievalService = new FileBillRetrievalService(new AsyncService(),
                "D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
    }
    
    @Test
    public void getAllBillsBySession() {
        assertEquals(1777, fileRetrievalService.getAllByExternalId(1132).size());
    }
}