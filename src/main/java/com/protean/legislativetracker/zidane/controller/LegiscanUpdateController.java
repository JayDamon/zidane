package com.protean.legislativetracker.zidane.controller;

import com.protean.legislativetracker.yuna.repository.BillRepository;
import com.protean.legislativetracker.yuna.repository.PersonRepository;
import com.protean.legislativetracker.yuna.repository.RollCallRepository;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.BillServiceImpl;
import com.protean.legislativetracker.yuna.service.CommitteeService;
import com.protean.legislativetracker.yuna.service.PersonService;
import com.protean.legislativetracker.yuna.service.PersonServiceImpl;
import com.protean.legislativetracker.yuna.service.RollCallService;
import com.protean.legislativetracker.yuna.service.RollCallServiceImpl;
import com.protean.legislativetracker.zidane.service.FileUpdateService;
import com.protean.legislativetracker.zidane.service.UpdateService;
import com.protean.legislativetracker.zidane.service.UpdateServiceFactory;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.RetrievalType;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileBillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FilePersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileRollCallRetrievalService;
import com.protean.security.auron.response.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("v1/bills")
public class LegiscanUpdateController {

    private UpdateServiceFactory updateServiceFactory;

//    @Autowired
//    BillRetrievalService billRetrievalService;
//    @Autowired
//    BillRepository billRepository;
//    @Autowired
//    PersonRepository personRepository;
//    @Autowired
//    CommitteeService committeeService;
//    @Autowired
//    RollCallRepository rollCallRepository;

    public LegiscanUpdateController(UpdateServiceFactory updateServiceFactory) {
        this.updateServiceFactory = updateServiceFactory;
    }

//    @PatchMapping(value = "/legiscan/activate")

    @PutMapping(value = "/legiscan/sessions/{sessionId}/activate")
    public ResponseEntity<?> updateSessionFromLegiscan(@PathVariable Integer sessionId) {

        updateServiceFactory.get(RetrievalType.LEGISCAN).getUpdatesForSession(sessionId);

        StandardResponse<Boolean> response = new StandardResponse<>(HttpStatus.OK, true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/file/activate")
    public ResponseEntity<?> initialDataLoad() {
//        BillRetrievalService billRetrievalService = new FileBillRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
//        PersonRetrievalService personRetrievalService = new FilePersonRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
//        RollCallRetrievalService rollCallRetrievalService = new FileRollCallRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
//        BillService billService = new BillServiceImpl(billRepository, committeeService);
//        PersonService personService = new PersonServiceImpl(personRepository);
//        RollCallService rollCallService = new RollCallServiceImpl(rollCallRepository);
//        UpdateService updateService = new FileUpdateService(billRetrievalService, personRetrievalService, rollCallRetrievalService, billService, personService, rollCallService);

        updateServiceFactory.get(RetrievalType.FILE).getUpdates();

        StandardResponse<Boolean> response = new StandardResponse<>(HttpStatus.OK, true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/file/sessions/{sessionId}/activate")
    public ResponseEntity<?> initialDataLoad(@PathVariable Integer sessionId) {
//        BillRetrievalService billRetrievalService = new FileBillRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
//        PersonRetrievalService personRetrievalService = new FilePersonRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
//        RollCallRetrievalService rollCallRetrievalService = new FileRollCallRetrievalService("D:/Libraries/Documents/Programming/LegislativeTracker/legiscan/Data/ME");
//        BillService billService = new BillServiceImpl(billRepository, committeeService);
//        PersonService personService = new PersonServiceImpl(personRepository);
//        RollCallService rollCallService = new RollCallServiceImpl(rollCallRepository);
//        UpdateService updateService = new FileUpdateService(billRetrievalService, personRetrievalService, rollCallRetrievalService, billService, personService, rollCallService);

        updateServiceFactory.get(RetrievalType.FILE).getUpdatesForSession(sessionId);

        StandardResponse<Boolean> response = new StandardResponse<>(HttpStatus.OK, true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
