package com.daniel.hospitalcharges.service;

import com.daniel.hospitalcharges.data.OutpatientIO;
import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import com.daniel.hospitalcharges.model.Provider;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This outpatient comparison service implementation contains the methods for
 * retrieving data associated with comparisons of outpatient charges.
 *
 * @author Bryan Daniel
 */
public class SimpleOutpatientComparisonService implements OutpatientComparisonService, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 6312123451314670600L;

    /**
     * Returns the list of all ambulatory payment classifications
     *
     * @return the APCs
     */
    @Override
    public ArrayList<AmbulatoryPaymentClassification> getAPCs() {
        return OutpatientIO.getAmbulatoryPaymentClassifications();
    }

    /**
     * Returns the list of states based on the given APC ID
     *
     * @param apcId the APC ID
     * @return the list of states
     */
    @Override
    public ArrayList<String> getStates(int apcId) {
        return OutpatientIO.getStates(apcId);
    }

    /**
     * Returns the list of cities to compare based on the given APC ID and state
     *
     * @param apcId the APC ID
     * @param state the state
     * @return the list of cities
     */
    @Override
    public ArrayList<String> getCitiesToCompare(int apcId, String state) {
        return OutpatientIO.getCitiesToCompare(apcId, state);
    }

    /**
     * Returns the list of providers based on the given state, city, and APC ID
     *
     * @param state the state
     * @param city the city
     * @param apcId the APC ID
     * @return the list of providers
     */
    @Override
    public ArrayList<Provider> getProviders(String state, String city, int apcId) {
        return OutpatientIO.getProviders(state, city, apcId);
    }

    /**
     * Returns the information on a hospital charge based on the provider and
     * APC
     *
     * @param providers the list of providers
     * @param providerId the provider ID
     * @param apcs the list of APCs
     * @param apcId the APC ID
     * @return the result
     */
    @Override
    public OutpatientComparisonResult getResult(ArrayList<Provider> providers, int providerId,
            ArrayList<AmbulatoryPaymentClassification> apcs, int apcId) {

        Provider providerResult = null;
        for (Provider p : providers) {
            if (p.getId() == providerId) {
                providerResult = p;
            }
        }

        AmbulatoryPaymentClassification apcResult
                = OutpatientIO.getCharges(apcId, providerId);
        for (AmbulatoryPaymentClassification a : apcs) {
            if (a.getApcId() == apcId) {
                apcResult.setApcId(a.getApcId());
                apcResult.setApcDefinition(a.getApcDefinition());
            }
        }

        OutpatientComparisonResult result = new OutpatientComparisonResult();
        result.setApc(apcResult);
        result.setProvider(providerResult);

        return result;
    }
}
