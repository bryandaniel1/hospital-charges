package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.data.InpatientIO;
import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This regional inpatient service implementation contains the methods for retrieving data associated with
 * regional inpatient charges.
 * 
 * @author Bryan Daniel
 */
public class SimpleRegionalInpatientService implements RegionalInpatientService, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -3856629341635020314L;

    /**
     * Returns a list of cities based on the given state
     *
     * @param state the given state
     * @return the list of cities
     */
    @Override
    public ArrayList<String> getCities(String state) {
        return InpatientIO.getCities(state);
    }

    /**
     * Returns a list of diagnosis-related groups based on the given city and
     * state
     *
     * @param state the given state
     * @param city the given city
     * @return the list of diagnosis-related groups
     */
    @Override
    public ArrayList<DiagnosisRelatedGroup> getDRGsByRegion(String state, String city) {
        return InpatientIO.getDiagnosisRelatedGroupsByRegion(state, city);
    }

    /**
     * Returns a list of regional results for comparison
     * @param state the given state
     * @param city the given city
     * @param drgId the given DRG ID
     * @return the list of results
     */
    @Override
    public ArrayList<InpatientComparisonResult> getRegionalResults(String state, String city, Integer drgId) {
        return InpatientIO.getRegionalResults(state, city, drgId);
    }    
}
