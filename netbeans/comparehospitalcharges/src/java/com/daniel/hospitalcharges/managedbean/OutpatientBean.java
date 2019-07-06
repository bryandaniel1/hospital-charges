package com.daniel.hospitalcharges.managedbean;

import com.daniel.hospitalcharges.service.ServiceManager;
import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;

/**
 * This managed bean handles the properties required for an outpatient search
 * result.
 *
 * @author Bryan Daniel
 */
@ManagedBean
@SessionScoped
public class OutpatientBean implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5795433778150549785L;

    /**
     * The outpatientState selection for the search
     */
    private String outpatientState;

    /**
     * The list of outpatientCities to choose from
     */
    private ArrayList<String> outpatientCities;

    /**
     * The selected outpatientCity
     */
    private String outpatientCity;

    /**
     * The list of ambulatory payment classifications to choose from
     */
    private ArrayList<AmbulatoryPaymentClassification> apcs;

    /**
     * The selected APC ID
     */
    private Integer apcId;

    /**
     * The selected APC
     */
    private String selectedApcDefinition;

    /**
     * The list of results collected for comparison of charges
     */
    private ArrayList<OutpatientComparisonResult> results;

    /**
     * Sets initial values for instance variables.
     */
    public OutpatientBean() {
        outpatientState = null;
        resetAll();
    }

    /**
     * Utilizes regionalOutpatientService to populate the options for
     * outpatientCities
     */
    public void showCities() {
        if (StringUtils.isBlank(outpatientState)) {
            resetAll();
        } else {
            outpatientCities = ServiceManager.getRegionalOutpatientService().getCities(outpatientState);
            outpatientCity = null;
            apcs = null;
            apcId = null;
            selectedApcDefinition = null;
            results = null;
        }
    }

    /**
     * Calls the showCities method and returns the page name
     *
     * @return the name of the page
     */
    public String showCitiesAndReturn() {
        showCities();
        return "outpatientregion";
    }

    /**
     * Utilizes regionalOutpatientService to populate the options for APCs
     */
    public void showApcs() {
        if (StringUtils.isBlank(outpatientState)) {
            resetAll();
        } else if (StringUtils.isBlank(outpatientCity)) {
            apcs = null;
            apcId = null;
            selectedApcDefinition = null;
            results = null;
        } else {
            apcs = ServiceManager.getRegionalOutpatientService().getAPCsByRegion(outpatientState, outpatientCity);
            apcId = null;
            selectedApcDefinition = null;
            results = null;
        }
    }

    /**
     * Calls the showApcs method and returns the page name
     *
     * @return the name of the page
     */
    public String showApcsAndReturn() {
        showApcs();
        return "outpatientregion";
    }

    /**
     * Utilizes regionalOutpatientService to populate the list of results
     */
    public void showResults() {
        if (StringUtils.isBlank(outpatientState)) {
            resetAll();
        } else if (StringUtils.isBlank(outpatientCity)) {
            apcs = null;
            apcId = null;
            selectedApcDefinition = null;
            results = null;
        } else if (apcId == null) {
            selectedApcDefinition = null;
            results = null;
        } else {
            for (AmbulatoryPaymentClassification a : apcs) {
                if (a.getApcId() == apcId) {
                    setSelectedApcDefinition(a.getApcDefinition());
                    break;
                }
            }
            results = ServiceManager.getRegionalOutpatientService().getRegionalResults(outpatientState, outpatientCity, apcId);
        }
    }

    /**
     * Calls the showResults method and returns the page name
     *
     * @return the name of the page
     */
    public String showResultsAndReturn() {
        showResults();
        return "outpatientregion";
    }

    /**
     * the outpatientState getter
     *
     * @return the outpatientState selection
     */
    public String getOutpatientState() {
        return outpatientState;
    }

    /**
     * The outpatientState setter
     *
     * @param outpatientState the outpatientState selection
     */
    public void setOutpatientState(String outpatientState) {
        this.outpatientState = outpatientState;
    }

    /**
     * The outpatientCities getter
     *
     * @return the list of outpatientCities to choose from
     */
    public ArrayList<String> getOutpatientCities() {
        return outpatientCities;
    }

    /**
     * The outpatientCities setter
     *
     * @param outpatientCities the list of outpatientCities to set
     */
    public void setOutpatientCities(ArrayList<String> outpatientCities) {
        this.outpatientCities = outpatientCities;
    }

    /**
     * Get the value of outpatientCity
     *
     * @return the value of outpatientCity
     */
    public String getOutpatientCity() {
        return outpatientCity;
    }

    /**
     * Set the value of outpatientCity
     *
     * @param outpatientCity new value of outpatientCity
     */
    public void setOutpatientCity(String outpatientCity) {
        this.outpatientCity = outpatientCity;
    }

    /**
     * Get the ambulatory payment classifications
     *
     * @return the list of APCs
     */
    public ArrayList<AmbulatoryPaymentClassification> getApcs() {
        return apcs;
    }

    /**
     * Set the ambulatory payment classifications
     *
     * @param apcs the list of APCs
     */
    public void setDrgs(ArrayList<AmbulatoryPaymentClassification> apcs) {
        this.apcs = apcs;
    }

    /**
     * Get the results
     *
     * @return the list of results collected for comparison of charges
     */
    public ArrayList<OutpatientComparisonResult> getResults() {
        return results;
    }

    /**
     * Set the results
     *
     * @param results the list of results collected for comparison of charges
     */
    public void setResults(ArrayList<OutpatientComparisonResult> results) {
        this.results = results;
    }

    /**
     * Get the APC ID
     *
     * @return the APC ID
     */
    public Integer getApcId() {
        return apcId;
    }

    /**
     * Set the APC ID
     *
     * @param apcId the APC ID
     */
    public void setApcId(Integer apcId) {
        this.apcId = apcId;
    }

    /**
     * Get the value of selectedApcDefinition
     *
     * @return the value of selectedApcDefinition
     */
    public String getSelectedApcDefinition() {
        return selectedApcDefinition;
    }

    /**
     * Set the value of selectedApcDefinition
     *
     * @param selectedApcDefinition new value of selectedApcDefinition
     */
    public void setSelectedApcDefinition(String selectedApcDefinition) {
        this.selectedApcDefinition = selectedApcDefinition;
    }

    /**
     * Resets variables to null.
     */
    private void resetAll() {
        outpatientCities = null;
        outpatientCity = null;
        apcs = null;
        apcId = null;
        selectedApcDefinition = null;
        results = null;
    }
}
