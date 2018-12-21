package com.protean.legislativetracker.zidane.service.update.file;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.zidane.legiscan.BillModelMapper;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.update.AsyncService;
import com.protean.legislativetracker.zidane.service.update.BillUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileBillUpdateService extends BillUpdateService {

    private static final Logger log = LoggerFactory.getLogger(FileBillUpdateService.class);

    private BillRetrievalService billRetrievalService;

    public FileBillUpdateService(BillService billService,
                                 SessionService sessionService,
                                 @Qualifier("fileBillRetrievalService") BillRetrievalService billRetrievalService,
                                 @Qualifier("yunaBillAsync") AsyncService<Bill> asyncService) {
        super(billService, sessionService, asyncService, log);
        this.billRetrievalService = billRetrievalService;
    }

    @Override
    public List<Bill> getUpdatedBills(Integer sessionId) {
        log.info("Retrieving bills for session with ID <" + sessionId + ">");

        ensureAllSessionsExistInDatabase(sessionId);

        List<Bill> mappedBills = retrieveItemsAsynchronouslyByInterval(billRetrievalService.getAllByExternalId(sessionId), 200);

        log.info("Returning bills");
        return mappedBills;
    }

    @Override
    public List<Bill> processThreadedItems() {

        List<Bill> bills =
                getListToProcess().stream().map(
                        BillModelMapper::legiscanBillToModelBill).collect(Collectors.toList());

        return getBillService().saveBills(bills);
    }

}
