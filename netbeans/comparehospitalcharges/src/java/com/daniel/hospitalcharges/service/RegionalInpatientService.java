package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import java.util.ArrayList;

/**
 * This interface contains the methods for retrieving data associated with
 * regional inpatient charges.
 *
 * @author Bryan Daniel
 */
public interface RegionalInpatientService {

    /**
     * Returns a list of cities based on the given state
     *
     * @param state the given state
     * @return the list of cities
     */
    public ArrayList<String> getCities(String state);

    /**
     * Returns a list of diagnosis-related groups based on the given city and
     * state
     *
     * @param state the given state
     * @param city the given city
     * @return the list of diagnosis-related groups
     */
    public ArrayList<DiagnosisRelatedGroup> getDRGsByRegion(String state, String city);
    
    /**
     * Returns a list of regional results for comparison
     * @param state the given state
     * @param city the given city
     * @param drgId the given DRG ID
     * @return the list of results
     */
    public ArrayList<InpatientComparisonResult> getRegionalResults(String state, String city, Integer drgId);
}
