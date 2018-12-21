package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.SessionService;
import com.protean.legislativetracker.zidane.service.ZidaneAsync;
import org.slf4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * The BillUpdateService checks legiscan for updates and returns the corresponding
 * model for addition to the LegislativeTracker database.
 */
public abstract class BillUpdateService extends ZidaneAsync<Bill, com.protean.legislativetracker.zidane.model.Bill> {

    private SessionService sessionService;
    private BillService billService;

    public BillUpdateService(BillService billService, SessionService sessionService,
                             AsyncService<Bill> asyncService, Logger log) {
        super(log, asyncService);
        this.sessionService = sessionService;
        this.billService = billService;
    }

    public abstract List<Bill> getUpdatedBills(Integer sessionId);

    public void saveBills(List<Bill> bills) {
        log.info("Starting: save bills");
        billService.saveBills(bills);
        log.info("Bills saved");
    }

    protected BillService getBillService() {
        return billService;
    }

    protected void ensureAllSessionsExistInDatabase(Integer sessionId) {
        Set<Integer> databaseSessionIds = sessionService.getSessionIds();
        StringJoiner missingSessions = new StringJoiner(", ");
        if (!databaseSessionIds.contains(sessionId)) {
            missingSessions.add(sessionId.toString());
        }
        if (missingSessions.length() > 0) {
            throw new IllegalArgumentException(
                    "Some sessions not found in database, please add them before proceeding. " +
                            "Missing Sessions: " + missingSessions.toString());

        }
    }
}
