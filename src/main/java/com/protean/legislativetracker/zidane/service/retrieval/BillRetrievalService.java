package com.protean.legislativetracker.zidane.service.retrieval;

import com.protean.legislativetracker.zidane.model.Bill;

import java.util.List;

/**
 * Retrieves {@link Bill} from legiscan and returns it
 *
 */
public interface BillRetrievalService extends RetrievalService {

    List<Bill> getAllByItems(List<String> item);

    List<Bill> getAllByExternalId(Integer id);

    List<Bill> getAllById(List<Long> ids);

    List<Bill> getAllByEntity(List<Bill> entities);

    Bill getById(long billId);

    Bill getByEntity(Bill bill);

}
