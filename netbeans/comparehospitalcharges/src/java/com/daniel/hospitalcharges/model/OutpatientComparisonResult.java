package com.daniel.hospitalcharges.model;

import java.io.Serializable;

/**
 * This class contains a provider and an ambulatory payment classification.
 *
 * @author Bryan Daniel
 */
public class OutpatientComparisonResult implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 5434147331123593762L;

    /**
     * The provider
     */
    private Provider provider;
    /**
     * The ambulatory payment classification
     */
    private AmbulatoryPaymentClassification apc;

    /**
     * This constructor set default values for the instance variables.
     */
    public OutpatientComparisonResult() {
        provider = null;
        apc = null;
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
     * Returns the ambulatory payment classification
     *
     * @return the ambulatory payment classification
     */
    public AmbulatoryPaymentClassification getApc() {
        return apc;
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
     * Sets the ambulatory payment classification
     *
     * @param newApc ambulatory payment classification
     */
    public void setApc(AmbulatoryPaymentClassification newApc) {
        apc = newApc;
    }
}
