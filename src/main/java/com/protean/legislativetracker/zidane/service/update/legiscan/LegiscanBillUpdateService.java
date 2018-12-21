package com.protean.legislativetracker.zidane.service.update.legiscan;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.zidane.legiscan.BillModelMapper;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanBillRetrievalService;
import com.protean.legislativetracker.zidane.service.update.AsyncService;
import com.protean.legislativetracker.zidane.service.update.BillUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegiscanBillUpdateService extends BillUpdateService {

    private static final Logger log = LoggerFactory.getLogger(LegiscanBillUpdateService.class);

    private BillRetrievalService billRetrievalService;

    public LegiscanBillUpdateService(BillService billService,
                                     SessionService sessionService,
                                     LegiscanBillRetrievalService billRetrievalService,
                                     AsyncService<Bill> asyncService) {
        super(billService, sessionService, asyncService, log);
        this.billRetrievalService = billRetrievalService;
    }

    /**
     * Retrieves a list of {@link Bill} from Legiscan and checks the change
     * hash with the repository to see which have been updated, saves any changed <code>Bill</code>
     * and returns them.
     *
     * @param sessionId Session Id for which the <code>Bill</code> must be retrieved
     * @return List of <code>Bill</code> that have updates in Legiscan
     */
    @Override
    public List<Bill> getUpdatedBills(Integer sessionId) {

        log.info("Retrieving bills for session with ID <" + sessionId + ">");

        ensureAllSessionsExistInDatabase(sessionId);

        List<com.protean.legislativetracker.zidane.model.Bill> legiscanBills =
                billRetrievalService.getAllByExternalId(sessionId);

        List<Bill> fullyLoadeddBills = retrieveItemsAsynchronouslyByInterval(legiscanBills, 200);

        log.info("Returning updated bills");
        return fullyLoadeddBills;
    }

    @Override
    public List<Bill> processThreadedItems() {
        List<Bill> fullyLodaedBills = new ArrayList<>();

        for (com.protean.legislativetracker.zidane.model.Bill bill : getListToProcess()) {
            if (getBillService().billHasChanged(bill.getBillId(), bill.getChangeHash())) {
                Bill modelBill = BillModelMapper.legiscanBillToModelBill(
                        billRetrievalService.getById(bill.getBillId()));
                fullyLodaedBills.add(modelBill);
            }
        }
        getBillService().saveBills(fullyLodaedBills);
        return fullyLodaedBills;
    }

}
