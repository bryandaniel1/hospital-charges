package com.daniel.hospitalcharges.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class contains information about an ambulatory payment classification
 * @author Bryan Daniel
 */
public class AmbulatoryPaymentClassification implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 2629832157614881660L;
    
    /**
     * The APC ID
     */
    private int apcId;
    /**
     * The APC definition
     */
    private String apcDefinition;
    /**
     * The average charges
     */
    private String avgCharges;
    /**
     * The average payments
     */
    private String avgPayments;
    
    /**
     * The percentile rank for average charges
     */
    private BigDecimal avgChargesPercentileRank;

    /**
     * The percentile rank for average payments
     */
    private BigDecimal avgPaymentsPercentileRank;

 
        
    /**
     * This constructor sets default values for the instance variables.
     */
    public AmbulatoryPaymentClassification() {
        
        apcDefinition = "";
    
        avgCharges = "";
    
        avgPayments = "";
        
        avgChargesPercentileRank = null;
        
        avgPaymentsPercentileRank = null;
    }
    
    /**
     * Get the value of apcId
     *
     * @return the value of apcId
     */
    public int getApcId() {
        return apcId;
    }

    /**
     * Set the value of apcId
     *
     * @param apcId new value of apcId
     */
    public void setApcId(int apcId) {
        this.apcId = apcId;
    }    
    
    /**
     * Returns the apc definition
     * @return the apc definition
     */
    public String getApcDefinition() {
        return apcDefinition;
    }
    
    /**
     * Returns the average charges
     * @return the average charges
     */
    public String getAvgCharges() {
        return avgCharges;
    }
    
    /**
     * Returns the average payments
     * @return the average payments
     */
    public String getAvgPayments() {
        return avgPayments;
    }
        
    /**
     * Sets the apc definition
     * @param newApcDefinition the apc defintion
     */
    public void setApcDefinition(String newApcDefinition) {
        apcDefinition = newApcDefinition;
    }
    
    /**
     * Sets the average charges
     * @param newAvgCharges the average charges
     */
    public void setAvgCharges(String newAvgCharges) {
        avgCharges = newAvgCharges;
    }
    
    /**
     * Sets the average payments
     * @param newAvgPayments the average payments
     */
    public void setAvgPayments(String newAvgPayments) {
        avgPayments = newAvgPayments;
    }

    /**
     * Get the value of avgChargesPercentileRank
     *
     * @return the value of avgChargesPercentileRank
     */
    public BigDecimal getAvgChargesPercentileRank() {
        return avgChargesPercentileRank;
    }

    /**
     * Set the value of avgChargesPercentileRank
     *
     * @param avgChargesPercentileRank new value of avgChargesPercentileRank
     */
    public void setAvgChargesPercentileRank(BigDecimal avgChargesPercentileRank) {        
        this.avgChargesPercentileRank = avgChargesPercentileRank;
    }

    /**
     * Get the value of avgPaymentsPercentileRank
     *
     * @return the value of avgPaymentsPercentileRank
     */
    public BigDecimal getAvgPaymentsPercentileRank() {
        return avgPaymentsPercentileRank;
    }

    /**
     * Set the value of avgPaymentsPercentileRank
     *
     * @param avgPaymentsPercentileRank new value of avgPaymentsPercentileRank
     */
    public void setAvgPaymentsPercentileRank(BigDecimal avgPaymentsPercentileRank) {        
        this.avgPaymentsPercentileRank = avgPaymentsPercentileRank;
    }

}
