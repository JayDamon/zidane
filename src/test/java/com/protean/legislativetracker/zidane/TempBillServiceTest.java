package com.protean.legislativetracker.zidane;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.model.BillSponsor;
import com.protean.legislativetracker.yuna.repository.BillRepository;
import com.protean.legislativetracker.yuna.repository.PersonRepository;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.BillServiceImpl;
import com.protean.legislativetracker.yuna.service.CommitteeService;
import com.protean.legislativetracker.zidane.legiscan.BillModelMapper;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class TempBillServiceTest {

    @Autowired BillRepository billRepository;
    @Autowired PersonRepository personRepository;
    @Autowired CommitteeService committeeService;

    private BillService billService;

    @Before
    public void setUp() {
        billService = new BillServiceImpl(billRepository, committeeService);
    }

    @Test
    public void saveBills_WithCommittee_SaveBill() {
        Bill bill = getBill("src/test/resources/bill/bill_53998_has_committee.json");
        for (BillSponsor s : bill.getSponsors()) {
            personRepository.save(s.getPerson());
        }
        billService.saveBills(Collections.singletonList(bill));
    }

    @Test
    public void legiscanBilLToModelBil_BillHasEmptyProgress_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_191682_no_progress.json");
        for (BillSponsor s : bill.getSponsors()) {
            personRepository.save(s.getPerson());
        }
        billService.saveBills(Collections.singletonList(bill));
    }

    @Test
    public void legiscanBillToModelBill_BillHasHistoryWithEmptyBody_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_191283_body_id_zero.json");
        for (BillSponsor s : bill.getSponsors()) {
            personRepository.save(s.getPerson());
        }
        billService.saveBills(Collections.singletonList(bill));
    }

    @Test
    public void legiscanBillToModelBill_BillLD1807_BillMapped() {
        Bill bill = getBill("src/test/resources/bill/bill_635867.json");
        for (BillSponsor s : bill.getSponsors()) {
            personRepository.save(s.getPerson());
        }
        billService.saveBills(Collections.singletonList(bill));
    }

    private Bill getBill(String filePath) {
        return BillModelMapper.legiscanBillToModelBill(new HttpRequestServiceImpl().getPojoFromJson(
                com.protean.legislativetracker.zidane.model.Bill.class,
                JsonFileLoader.readJsonFileAsString(filePath),
                "bill"));
    }

}
