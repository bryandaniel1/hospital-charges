package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.data.InpatientIO;
import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.model.Provider;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This inpatient comparison service implementation contains the methods for
 * retrieving data associated with comparisons of inpatient charges.
 *
 * @author Bryan Daniel
 */
public class SimpleInpatientComparisonService implements InpatientComparisonService, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1696413230223076881L;

    /**
     * Returns the list of all diagnosis-related groups
     *
     * @return the DRGs
     */
    @Override
    public ArrayList<DiagnosisRelatedGroup> getDRGs() {
        return InpatientIO.getDiagnosisRelatedGroups();
    }

    /**
     * Returns the list of states based on the given DRG ID
     *
     * @param drgId the DRG ID
     * @return the list of states
     */
    @Override
    public ArrayList<String> getStates(int drgId) {
        return InpatientIO.getStates(drgId);
    }

    /**
     * Returns the list of cities to compare based on the given DRG ID and state
     *
     * @param drgId the DRG ID
     * @param state the state
     * @return the list of cities
     */
    @Override
    public ArrayList<String> getCitiesToCompare(int drgId, String state) {
        return InpatientIO.getCitiesToCompare(drgId, state);
    }

    /**
     * Returns the list of providers based on the given state, city, and DRG ID
     *
     * @param state the state
     * @param city the city
     * @param drgId the DRG ID
     * @return the list of providers
     */
    @Override
    public ArrayList<Provider> getProviders(String state, String city, int drgId) {
        return InpatientIO.getProviders(state, city, drgId);
    }

    /**
     * Returns the information on a hospital charge based on the provider and
     * DRG
     *
     * @param providers the list of providers
     * @param providerId the provider ID
     * @param drgs the list of DRGs
     * @param drgId the DRG ID
     * @return the result
     */
    @Override
    public InpatientComparisonResult getResult(ArrayList<Provider> providers, int providerId,
            ArrayList<DiagnosisRelatedGroup> drgs, int drgId) {

        Provider providerResult = null;
        for (Provider p : providers) {
            if (p.getId() == providerId) {
                providerResult = p;
            }
        }

        DiagnosisRelatedGroup drgResult = InpatientIO
                .getCharges(drgId, providerId);
        for (DiagnosisRelatedGroup d : drgs) {
            if (d.getDrgId() == drgId) {
                drgResult.setDrgId(d.getDrgId());
                drgResult.setDrgDefinition(d.getDrgDefinition());
            }
        }

        InpatientComparisonResult result = new InpatientComparisonResult();
        result.setDrg(drgResult);
        result.setProvider(providerResult);
        
        return result;
    }
}
