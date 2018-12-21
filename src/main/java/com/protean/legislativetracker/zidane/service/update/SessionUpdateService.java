package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.LegislativeSession;

import java.util.List;

/**
 * The SessionUpdateService checks legiscan for updates and returns the corresponding
 * model for addition to the LegislativeTracker database.
 */
public interface SessionUpdateService {

    /**
     * Retrieves a list of {@link LegislativeSession} from Legiscan and checks the change
     * hash with the repository to see which have been updated, and returns the list of
     * sessions in need of update.
     *
     * @param states List of state abbreviations for which the sessions must be retrieved
     * @return List of sessions that have updates in Legiscan
     */
    List<LegislativeSession> saveAndReturnSessionChanges(List<String> states);

}
