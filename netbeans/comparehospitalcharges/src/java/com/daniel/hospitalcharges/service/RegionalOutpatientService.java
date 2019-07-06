package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import java.util.ArrayList;

/**
 * This interface contains the methods for retrieving data associated with
 * regional outpatient charges.
 *
 * @author Bryan Daniel
 */
public interface RegionalOutpatientService {

    /**
     * Returns a list of cities based on the given state
     *
     * @param state the given state
     * @return the list of cities
     */
    public ArrayList<String> getCities(String state);

    /**
     * Returns a list of ambulatory payment classifications based on the given city and
     * state
     *
     * @param state the given state
     * @param city the given city
     * @return the list of ambulatory payment classifications
     */
    public ArrayList<AmbulatoryPaymentClassification> getAPCsByRegion(String state, String city);
    
    /**
     * Returns a list of regional results for comparison
     * @param state the given state
     * @param city the given city
     * @param apcId the given APC ID
     * @return the list of results
     */
    public ArrayList<OutpatientComparisonResult> getRegionalResults(String state, String city, Integer apcId);
}