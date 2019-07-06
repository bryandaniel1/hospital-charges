package com.daniel.hospitalcharges.managedbean;

import com.daniel.hospitalcharges.service.ServiceManager;
import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;

/**
 * This managed bean handles the properties required for an inpatient search
 * result.
 *
 * @author Bryan Daniel
 */
@ManagedBean
@SessionScoped
public class InpatientBean implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -830992314447819931L;

    /**
     * The inpatientState selection for the search
     */
    private String inpatientState;

    /**
     * The list of inpatientCities to choose from
     */
    private ArrayList<String> inpatientCities;

    /**
     * The selected inpatientCity
     */
    private String inpatientCity;

    /**
     * The list of diagnosis-related groups to choose from
     */
    private ArrayList<DiagnosisRelatedGroup> drgs;

    /**
     * The selected DRG ID
     */
    private Integer drgId;

    /**
     * The selected DRG
     */
    private String selectedDrgDefinition;

    /**
     * The list of results collected for comparison of charges
     */
    private ArrayList<InpatientComparisonResult> results;

    /**
     * Sets initial values for instance variables.
     */
    public InpatientBean() {
        inpatientState = null;
        resetAll();
    }

    /**
     * Utilizes regionalInpatientService to populate the options for
     * inpatientCities
     */
    public void showCities() {
        if (StringUtils.isBlank(inpatientState)) {
            resetAll();
        } else {
            inpatientCities = ServiceManager.getRegionalInpatientService().getCities(inpatientState);
            inpatientCity = null;
            drgs = null;
            drgId = null;
            selectedDrgDefinition = null;
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
        return "inpatientregion";
    }

    /**
     * Utilizes regionalInpatientService to populate the options for DRGs
     */
    public void showDrgs() {
        if (StringUtils.isBlank(inpatientState)) {
            resetAll();
        } else if (StringUtils.isBlank(inpatientCity)) {
            drgs = null;
            drgId = null;
            selectedDrgDefinition = null;
            results = null;
        } else {
            drgs = ServiceManager.getRegionalInpatientService().getDRGsByRegion(inpatientState, inpatientCity);
            drgId = null;
            selectedDrgDefinition = null;
            results = null;
        }
    }

    /**
     * Calls the showDrgs method and returns the page name
     *
     * @return the name of the page
     */
    public String showDrgsAndReturn() {
        showDrgs();
        return "inpatientregion";
    }

    /**
     * Utilizes regionalInpatientService to populate the list of results
     */
    public void showResults() {
        if (StringUtils.isBlank(inpatientState)) {
            resetAll();
        } else if (StringUtils.isBlank(inpatientCity)) {
            drgs = null;
            drgId = null;
            selectedDrgDefinition = null;
            results = null;
        } else if (drgId == null) {
            selectedDrgDefinition = null;
            results = null;
        } else {
            for (DiagnosisRelatedGroup d : drgs) {
                if (d.getDrgId() == drgId) {
                    setSelectedDrgDefinition(d.getDrgDefinition());
                    break;
                }
            }
            results = ServiceManager.getRegionalInpatientService().getRegionalResults(inpatientState, inpatientCity, drgId);
        }
    }

    /**
     * Calls the showResults method and returns the page name
     *
     * @return the name of the page
     */
    public String showResultsAndReturn() {
        showResults();
        return "inpatientregion";
    }

    /**
     * the inpatientState getter
     *
     * @return the inpatientState selection
     */
    public String getInpatientState() {
        return inpatientState;
    }

    /**
     * The inpatientState setter
     *
     * @param inpatientState the inpatientState selection
     */
    public void setInpatientState(String inpatientState) {
        this.inpatientState = inpatientState;
    }

    /**
     * The inpatientCities getter
     *
     * @return the list of inpatientCities to choose from
     */
    public ArrayList<String> getInpatientCities() {
        return inpatientCities;
    }

    /**
     * The inpatientCities setter
     *
     * @param inpatientCities the list of inpatientCities to set
     */
    public void setInpatientCities(ArrayList<String> inpatientCities) {
        this.inpatientCities = inpatientCities;
    }

    /**
     * Get the value of inpatientCity
     *
     * @return the value of inpatientCity
     */
    public String getInpatientCity() {
        return inpatientCity;
    }

    /**
     * Set the value of inpatientCity
     *
     * @param inpatientCity new value of inpatientCity
     */
    public void setInpatientCity(String inpatientCity) {
        this.inpatientCity = inpatientCity;
    }

    /**
     * Get the diagnosis-related groups
     *
     * @return the list of DRGs
     */
    public ArrayList<DiagnosisRelatedGroup> getDrgs() {
        return drgs;
    }

    /**
     * Set the diagnosis-related groups
     *
     * @param drgs the list of DRGs
     */
    public void setDrgs(ArrayList<DiagnosisRelatedGroup> drgs) {
        this.drgs = drgs;
    }

    /**
     * Get the results
     *
     * @return the list of results collected for comparison of charges
     */
    public ArrayList<InpatientComparisonResult> getResults() {
        return results;
    }

    /**
     * Set the results
     *
     * @param results the list of results collected for comparison of charges
     */
    public void setResults(ArrayList<InpatientComparisonResult> results) {
        this.results = results;
    }

    /**
     * Get the DRG ID
     *
     * @return the DRG ID
     */
    public Integer getDrgId() {
        return drgId;
    }

    /**
     * Set the DRG ID
     *
     * @param drgId the DRG ID
     */
    public void setDrgId(Integer drgId) {
        this.drgId = drgId;
    }

    /**
     * Get the selected DRG definition
     *
     * @return the DRG definition
     */
    public String getSelectedDrgDefinition() {
        return selectedDrgDefinition;
    }

    /**
     * Set the selected DRG definition
     *
     * @param selectedDrgDefinition the DRG definition
     */
    public void setSelectedDrgDefinition(String selectedDrgDefinition) {
        this.selectedDrgDefinition = selectedDrgDefinition;
    }

    /**
     * Resets variables to null.
     */
    private void resetAll() {
        inpatientCities = null;
        inpatientCity = null;
        drgs = null;
        drgId = null;
        selectedDrgDefinition = null;
        results = null;
    }
}
