package com.protean.legislativetracker.zidane.service;

import com.protean.legislativetracker.yuna.repository.BillRepository;
import com.protean.legislativetracker.yuna.repository.PersonRepository;
import com.protean.legislativetracker.yuna.repository.RollCallRepository;
import com.protean.legislativetracker.yuna.repository.SessionRepository;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.BillServiceImpl;
import com.protean.legislativetracker.yuna.service.CommitteeService;
import com.protean.legislativetracker.yuna.service.PersonService;
import com.protean.legislativetracker.yuna.service.PersonServiceImpl;
import com.protean.legislativetracker.yuna.service.RollCallService;
import com.protean.legislativetracker.yuna.service.RollCallServiceImpl;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.yuna.service.SessionServiceImpl;
import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileBillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FilePersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileRollCallRetrievalService;
import com.protean.legislativetracker.zidane.service.update.AsyncService;
import com.protean.legislativetracker.zidane.service.update.file.FileBillUpdateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class FileUpdateServiceTest {

    @Autowired BillRepository billRepository;
    @Autowired PersonRepository personRepository;
    @Autowired RollCallRepository rollCallRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired CommitteeService committeeService;
    @Qualifier("zidaneBillAsync")
    @Autowired
    AsyncService<Bill> zidaneBillAsync;
    @Qualifier("yunaBillAsync")
    @Autowired
    AsyncService<com.protean.legislativetracker.yuna.model.Bill> yunaBillAsync;


    private UpdateService updateService;

    @Before
    public void setUp() {
        BillRetrievalService billRetrievalService = new FileBillRetrievalService(zidaneBillAsync,"D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
        PersonRetrievalService personRetrievalService = new FilePersonRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
        RollCallRetrievalService rollCallRetrievalService = new FileRollCallRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
        BillService billService = new BillServiceImpl(billRepository, committeeService);
        PersonService personService = new PersonServiceImpl(personRepository);
        RollCallService rollCallService = new RollCallServiceImpl(rollCallRepository);
        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        FileBillUpdateService fileBillUpdateService = new FileBillUpdateService(billService, sessionService, billRetrievalService, yunaBillAsync);
        updateService = new FileUpdateService(fileBillUpdateService, personRetrievalService, rollCallRetrievalService, personService, rollCallService);
    }

    @Test
    @DirtiesContext
    public void getUpdates_CorrectFilePath_BillsAddedToDatabase() {
        updateService.getUpdates();
        assertEquals(9564, StreamSupport.stream(billRepository.findAll().spliterator(), false).count());
        assertEquals(952, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
    }

    @Test
    @DirtiesContext
    public void getUpdates_128thSession_DataAddedToDatabase() {
        updateService.getUpdatesForSession(1258);
        assertEquals(2003, StreamSupport.stream(billRepository.findAll().spliterator(), false).count());
        assertEquals(188, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
    }

    @Test
    @DirtiesContext
    public void getUpdates_127thSession_DataAddedToDatabase() {
        updateService.getUpdatesForSession(1132);
        assertEquals(1777, StreamSupport.stream(billRepository.findAll().spliterator(), false).count());
        assertEquals(193, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
    }

    @Test
    @DirtiesContext
    public void getUpdates_126thSession_DataAddedToDatabase() { //TODOO not added to production
        updateService.getUpdatesForSession(1004);
        assertEquals(1917, StreamSupport.stream(billRepository.findAll().spliterator(), false).count());
        assertEquals(190, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
    }

    @Test
    @DirtiesContext
    public void getUpdates_125thSessionDataAddedToDatabase() {
        updateService.getUpdatesForSession(81);
        assertEquals(1980, StreamSupport.stream(billRepository.findAll().spliterator(), false).count());
        assertEquals(193, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
    }

    @Test
    @DirtiesContext
    public void getUpdates_124thSession_DataAddedToDatabase() {
        updateService.getUpdatesForSession(48);
        assertEquals(1887, StreamSupport.stream(billRepository.findAll().spliterator(), false).count());
        assertEquals(188, StreamSupport.stream(personRepository.findAll().spliterator(), false).count());
    }
}