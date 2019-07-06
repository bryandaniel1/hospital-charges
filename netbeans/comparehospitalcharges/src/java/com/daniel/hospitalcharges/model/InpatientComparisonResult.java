package com.daniel.hospitalcharges.model;

import java.io.Serializable;

/**
 * This class contains a provider and a diagnosis-related group.
 *
 * @author Bryan Daniel
 */
public class InpatientComparisonResult implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1316396366835734807L;

    /**
     * The provider
     */
    private Provider provider;
    /**
     * The diagnosis-related group
     */
    private DiagnosisRelatedGroup drg;

    /**
     * This constructor set default values for the instance variables.
     */
    public InpatientComparisonResult() {
        provider = null;
        drg = null;
    }

    /**
     * Returns the provider
     *
     * @return the provider
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Returns the diagnosis-related group
     *
     * @return the diagnosis-related group
     */
    public DiagnosisRelatedGroup getDrg() {
        return drg;
    }

    /**
     * Sets the provider
     *
     * @param newProvider the provider
     */
    public void setProvider(Provider newProvider) {
        provider = newProvider;
    }

    /**
     * Sets the diagnosis-related group
     *
     * @param newDrg the diagnosis-related group
     */
    public void setDrg(DiagnosisRelatedGroup newDrg) {
        drg = newDrg;
    }
}
