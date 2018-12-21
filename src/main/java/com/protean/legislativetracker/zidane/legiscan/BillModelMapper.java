package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.yuna.model.Bill;
import com.protean.legislativetracker.yuna.model.BillHistory;
import com.protean.legislativetracker.yuna.model.BillProgress;
import com.protean.legislativetracker.yuna.model.BillSponsor;
import com.protean.legislativetracker.yuna.model.MappedBill;
import com.protean.legislativetracker.yuna.model.MappedBillId;
import com.protean.legislativetracker.zidane.model.Person;
import com.protean.legislativetracker.zidane.utilities.ArgumentValidator;
import org.modelmapper.Condition;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillModelMapper {

    private static final Logger log = LoggerFactory.getLogger(BillModelMapper.class);

    public static Bill legiscanBillToModelBill(com.protean.legislativetracker.zidane.model.Bill bill) {
        ArgumentValidator.validateArgument(bill == null, "Value to map must not be null", log);

        log.debug("Mapping Bill #: " + bill.getBillNumber() + " | Bill Id: " + bill.getBillId());

        Condition<Integer, Integer> notZero = ctx -> ctx.getSource() != 0;
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Person, BillSponsor> sponsorCommitteeMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                skip().getPerson().getCommittee().setId(null);
            }
        };
        modelMapper.addMappings(sponsorCommitteeMap);

        TypeMap<com.protean.legislativetracker.zidane.model.Bill, Bill> typeMap =
                modelMapper.createTypeMap(com.protean.legislativetracker.zidane.model.Bill.class, Bill.class);

        typeMap.addMappings(mapper ->
                mapper.when(notZero).<Integer>map(
                        com.protean.legislativetracker.zidane.model.Bill::getPendingCommitteeId,
                        (destBill, id) -> destBill.getCommittee().setId(id)));
        typeMap.addMappings(mapping ->
                mapping.when(notZero).<Integer>map(
                        com.protean.legislativetracker.zidane.model.Bill::getStatusId,
                        (destBill, id) -> destBill.getStatus().setProgressId(id)
                ));
        try {
            Bill modelBill = typeMap.map(bill);

            modelBill.getProgress().forEach(billProgress -> addBillToObject(modelBill, billProgress));
            int order = 1;
            for (BillHistory history : modelBill.getHistories()) {
                if (history.getBody().getId() == 0) {
                    history.setBody(null);
                }
                addBillToObject(modelBill, history);
                history.getId().setOrder(order);
                order++;
            }
            int step = 1;
            for (BillProgress progress : modelBill.getProgress()) {
                progress.getId().setProgressStep(step);
                step++;
            }

            modelBill.getSponsors().forEach(sponsor -> addBillToObject(modelBill, sponsor));
            modelBill.getSasts().forEach(sast -> addBillToObject(modelBill, sast));
            modelBill.getCalendars().forEach(calendar -> addBillToObject(modelBill, calendar));
            modelBill.getRollCalls().forEach(rollCall -> addBillToObject(modelBill, rollCall));
            modelBill.getAmendments().forEach(amendment -> addBillToObject(modelBill, amendment));
            modelBill.getSupplements().forEach(supplement -> addBillToObject(modelBill, supplement));
            modelBill.getTexts().forEach(text -> addBillToObject(modelBill, text));
            return modelBill;
        } catch (MappingException e) {
            String msg = "Bill could not be mapped | Bill Number: " + bill.getBillNumber() +
                    " Bill ID: " + bill.getBillId() + " Bill: " + bill.toString();
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }

    private static void addBillToObject(Bill bill, MappedBill mappedBill) {
        mappedBill.setBill(bill);
    }

    private static void addBillToObject(Bill bill, MappedBillId mappedBillId) {
        mappedBillId.setBill(bill);
        mappedBillId.getId().setBill(bill);
    }

}
