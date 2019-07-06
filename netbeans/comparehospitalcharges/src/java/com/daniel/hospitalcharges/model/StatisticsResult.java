package com.daniel.hospitalcharges.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Holds statistical information for a provider charge
 * @author Bryan Daniel
 */
public class StatisticsResult implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 8203222088685915727L;
    
    /**
     * Instance variables
     */
    private String header;    
    private BigDecimal avgCharge;    
    private BigDecimal median;    
    private BigDecimal stdDeviation;    
    private BigDecimal range;    
    private BigDecimal minCharge;    
    private BigDecimal maxCharge;    
    private Provider maxProvider;    
    private Provider minProvider;    
    private ArrayList<BigDecimal> allCharges;
    

    /**
     * Default constructor
     */
    public StatisticsResult() {
        header = null;    
        avgCharge = null;    
        median = null;    
        stdDeviation = null;    
        range = null;    
        minCharge = null;    
        maxCharge = null;
        maxProvider = null;
        minProvider = null;
        allCharges = null;
    }
    
    /**
     * Get the value of allCharges
     *
     * @return the value of allCharges
     */
    public ArrayList<BigDecimal> getAllCharges() {
        return allCharges;
    }

    /**
     * Set the value of allCharges
     *
     * @param allCharges new value of allCharges
     */
    public void setAllCharges(ArrayList<BigDecimal> allCharges) {
        this.allCharges = allCharges;
    }
    
    /**
     * Get the value of minProvider
     *
     * @return the value of minProvider
     */
    public Provider getMinProvider() {
        return minProvider;
    }

    /**
     * Set the value of minProvider
     *
     * @param minProvider new value of minProvider
     */
    public void setMinProvider(Provider minProvider) {
        this.minProvider = minProvider;
    }


    /**
     * Get the value of maxProvider
     *
     * @return the value of maxProvider
     */
    public Provider getMaxProvider() {
        return maxProvider;
    }

    /**
     * Set the value of maxProvider
     *
     * @param maxProvider new value of maxProvider
     */
    public void setMaxProvider(Provider maxProvider) {
        this.maxProvider = maxProvider;
    }    
    

    /**
     * Get the value of maxCharge
     *
     * @return the value of maxCharge
     */
    public BigDecimal getMaxCharge() {
        return maxCharge;
    }

    /**
     * Set the value of maxCharge
     *
     * @param maxCharge new value of maxCharge
     */
    public void setMaxCharge(BigDecimal maxCharge) {
        this.maxCharge = maxCharge;
    }
    
    /**
     * Get the value of minCharge
     *
     * @return the value of minCharge
     */
    public BigDecimal getMinCharge() {
        return minCharge;
    }

    /**
     * Set the value of minCharge
     *
     * @param minCharge new value of minCharge
     */
    public void setMinCharge(BigDecimal minCharge) {
        this.minCharge = minCharge;
    }


    /**
     * Get the value of range
     *
     * @return the value of range
     */
    public BigDecimal getRange() {
        return range;
    }

    /**
     * Set the value of range
     *
     * @param range new value of range
     */
    public void setRange(BigDecimal range) {
        this.range = range;
    }


    /**
     * Get the value of stdDeviation
     *
     * @return the value of stdDeviation
     */
    public BigDecimal getStdDeviation() {
        return stdDeviation;
    }

    /**
     * Set the value of stdDeviation
     *
     * @param stdDeviation new value of stdDeviation
     */
    public void setStdDeviation(BigDecimal stdDeviation) {
        this.stdDeviation = stdDeviation;
    }


    /**
     * Get the value of median
     *
     * @return the value of median
     */
    public BigDecimal getMedian() {
        return median;
    }

    /**
     * Set the value of median
     *
     * @param median new value of median
     */
    public void setMedian(BigDecimal median) {
        this.median = median;
    }


    /**
     * Get the value of avgCharge
     *
     * @return the value of avgCharge
     */
    public BigDecimal getAvgCharge() {
        return avgCharge;
    }

    /**
     * Set the value of avgCharge
     *
     * @param avgCharge new value of avgCharge
     */
    public void setAvgCharge(BigDecimal avgCharge) {
        this.avgCharge = avgCharge;
    }


    /**
     * Get the value of header
     *
     * @return the value of header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Set the value of header
     *
     * @param header new value of header
     */
    public void setHeader(String header) {
        this.header = header;
    }

}