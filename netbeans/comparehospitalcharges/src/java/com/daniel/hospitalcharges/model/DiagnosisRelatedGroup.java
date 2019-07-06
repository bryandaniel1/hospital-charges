package com.daniel.hospitalcharges.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class contains information about a diagnosis related group
 *
 * @author Bryan Daniel
 */
public class DiagnosisRelatedGroup implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5191925718234847913L;

    /**
     * The DRG ID
     */
    private int drgId;
    /**
     * The DRG definition
     */
    private String drgDefinition;
    /**
     * The average charges
     */
    private String avgCharges;
    /**
     * The average payments
     */
    private String avgPayments;
    /**
     * The average Medicare payments
     */
    private String avgMedicarePayments;

    /**
     * The percentile rank for average charges
     */
    private BigDecimal avgChargesPercentileRank;

    /**
     * The percentile rank for average payments
     */
    private BigDecimal avgPaymentsPercentileRank;

    /**
     * The percentile rank for average medicare payments
     */
    private BigDecimal avgMedicarePaymentsPercentileRank;

    /**
     * This constructor sets default values for the instance variables.
     */
    public DiagnosisRelatedGroup() {

        drgDefinition = "";

        avgCharges = "";

        avgPayments = "";

        avgMedicarePayments = "";

        avgChargesPercentileRank = null;

        avgPaymentsPercentileRank = null;

        avgMedicarePaymentsPercentileRank = null;
    }

    /**
     * Returns the drg definition
     *
     * @return the drg definition
     */
    public String getDrgDefinition() {
        return drgDefinition;
    }

    /**
     * Get the value of drgId
     *
     * @return the value of drgId
     */
    public int getDrgId() {
        return drgId;
    }

    /**
     * Set the value of drgId
     *
     * @param drgId new value of drgId
     */
    public void setDrgId(int drgId) {
        this.drgId = drgId;
    }

    /**
     * Returns the average charges
     *
     * @return the average charges
     */
    public String getAvgCharges() {
        return avgCharges;
    }

    /**
     * Returns the average payments
     *
     * @return the average payments
     */
    public String getAvgPayments() {
        return avgPayments;
    }

    /**
     * Returns the average Medicare payments
     *
     * @return the average Medicare payments
     */
    public String getAvgMedicarePayments() {
        return avgMedicarePayments;
    }

    /**
     * Sets the drg definition
     *
     * @param newDrgDefinition the drg defintion
     */
    public void setDrgDefinition(String newDrgDefinition) {
        drgDefinition = newDrgDefinition;
    }

    /**
     * Sets the average charges
     *
     * @param newAvgCharges the average charges
     */
    public void setAvgCharges(String newAvgCharges) {
        avgCharges = newAvgCharges;
    }

    /**
     * Sets the average payments
     *
     * @param newAvgPayments the average payments
     */
    public void setAvgPayments(String newAvgPayments) {
        avgPayments = newAvgPayments;
    }

    /**
     * Sets the average Medicare payments
     *
     * @param newAvgMedicarePayments the average Medicare payments
     */
    public void setAvgMedicarePayments(String newAvgMedicarePayments) {
        avgMedicarePayments = newAvgMedicarePayments;
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

    /**
     * Get the value of avgMedicarePaymentsPercentileRank
     *
     * @return the value of avgMedicarePaymentsPercentileRank
     */
    public BigDecimal getAvgMedicarePaymentsPercentileRank() {
        return avgMedicarePaymentsPercentileRank;
    }

    /**
     * Set the value of avgMedicarePaymentsPercentileRank
     *
     * @param avgMedicarePaymentsPercentileRank new value of
     * avgMedicarePaymentsPercentileRank
     */
    public void setAvgMedicarePaymentsPercentileRank(BigDecimal avgMedicarePaymentsPercentileRank) {
        this.avgMedicarePaymentsPercentileRank = avgMedicarePaymentsPercentileRank;
    }

}
