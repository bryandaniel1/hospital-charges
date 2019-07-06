package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.model.Provider;
import java.util.ArrayList;

/**
 * This interface contains the methods for retrieving data associated with
 * comparisons of inpatient charges.
 *
 * @author Bryan Daniel
 */
public interface InpatientComparisonService {

    /**
     * Returns the list of all diagnosis-related groups
     *
     * @return the DRGs
     */
    public ArrayList<DiagnosisRelatedGroup> getDRGs();

    /**
     * Returns the list of states based on the given DRG ID
     *
     * @param drgId the DRG ID
     * @return the list of states
     */
    public ArrayList<String> getStates(int drgId);

    /**
     * Returns the list of cities to compare based on the given DRG ID and state
     *
     * @param drgId the DRG ID
     * @param state the state
     * @return the list of cities
     */
    public ArrayList<String> getCitiesToCompare(int drgId, String state);

    /**
     * Returns the list of providers based on the given state, city, and DRG ID
     *
     * @param state the state
     * @param city the city
     * @param drgId the DRG ID
     * @return the list of providers
     */
    public ArrayList<Provider> getProviders(String state, String city, int drgId);

    /**
     * Returns the information on a hospital charge based on the provider and DRG
     *
     * @param providers the list of providers
     * @param providerId the provider ID
     * @param drgs the list of DRGs
     * @param drgId the DRG ID
     * @return the result
     */
    public InpatientComparisonResult getResult(ArrayList<Provider> providers, int providerId, 
            ArrayList<DiagnosisRelatedGroup> drgs, int drgId);
}
