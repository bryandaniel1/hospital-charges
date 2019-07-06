package com.daniel.hospitalcharges.managedbean;

import com.daniel.hospitalcharges.service.ServiceManager;
import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.model.Provider;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;

/**
 * This managed bean handles the properties required for comparing two inpatient
 * search results.
 *
 * @author Bryan Daniel
 */
@ManagedBean
@SessionScoped
public class InpatientComparisonBean implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 6800226444771012946L;

    /**
     * The list of diagnosis-related groups to choose from
     */
    private ArrayList<DiagnosisRelatedGroup> drgs;

    /**
     * The selected DRG ID
     */
    private Integer drgId;

    /**
     * The DRG definition
     */
    private String drgDefinition;

    /**
     * The list of states to choose from
     */
    private ArrayList<String> states;

    /**
     * The second list of states to choose from
     */
    private ArrayList<String> compareToStates;

    /**
     * The state selection for the search
     */
    private String state;

    /**
     * The state selection for the second search
     */
    private String secondState;

    /**
     * The list of cities to choose from
     */
    private ArrayList<String> cities;

    /**
     * The second list of cities to choose from
     */
    private ArrayList<String> compareToCities;

    /**
     * The selected city
     */
    private String city;

    /**
     * The selected city for the second search
     */
    private String secondCity;

    /**
     * The list of providers to choose from
     */
    private ArrayList<Provider> providers;

    /**
     * The second list of providers to choose from
     */
    private ArrayList<Provider> compareToProviders;

    /**
     * The selected provider ID
     */
    private Integer providerId;

    /**
     * The selected provider ID for the second search
     */
    private Integer secondProviderId;

    /**
     * The first search result for comparison
     */
    private InpatientComparisonResult result;

    /**
     * The second search result for comparison
     */
    private InpatientComparisonResult secondResult;

    /**
     * Sets initial values for instance variables.
     */
    public InpatientComparisonBean() {
        drgs = ServiceManager.getInpatientComparisonService().getDRGs();
        drgId = null;
        resetAll();
    }

    /**
     * Utilizes inpatientComparisonService to populate the options for states
     */
    public void showStates() {
        if (drgId != null) {
            states = ServiceManager.getInpatientComparisonService().getStates(drgId);
            setDrgDefinition();
            compareToStates = new ArrayList(states);
            state = null;
            secondState = null;
            cities = null;
            compareToCities = null;
            city = null;
            secondCity = null;
            providers = null;
            compareToProviders = null;
            providerId = null;
            secondProviderId = null;
            result = null;
            secondResult = null;
        } else {
            resetAll();
        }
    }

    /**
     * Calls the showStates method and returns the page name
     *
     * @return the page name
     */
    public String showStatesAndReturn() {
        showStates();
        return "inpatientcomparetwo";
    }

    /**
     * Utilizes inpatientComparisonService to populate the options for cities
     */
    public void showCities() {
        setDrgDefinition();
        if (drgId == null) {
            resetAll();
        } else if (StringUtils.isBlank(state)) {
            cities = null;
            city = null;
            providers = null;
            providerId = null;
            result = null;
        } else {
            cities = ServiceManager.getInpatientComparisonService().getCitiesToCompare(drgId, state);
            city = null;
            providers = null;
            providerId = null;
            result = null;
        }
    }

    /**
     * Calls the showCities method and returns the page name
     *
     * @return the page name
     */
    public String showCitiesAndReturn() {
        showCities();
        return "inpatientcomparetwo";
    }

    /**
     * Utilizes inpatientComparisonService to populate the options for the
     * second list of cities
     */
    public void showCompareToCities() {
        setDrgDefinition();
        if (drgId == null) {
            resetAll();
        } else if (StringUtils.isBlank(secondState)) {
            compareToCities = null;
            secondCity = null;
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        } else {
            compareToCities = ServiceManager.getInpatientComparisonService().getCitiesToCompare(drgId, secondState);
            secondCity = null;
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        }
    }

    /**
     * Calls the showCompareToCities method and returns the page name
     *
     * @return the page name
     */
    public String showCompareToCitiesAndReturn() {
        showCompareToCities();
        return "inpatientcomparetwo";
    }

    /**
     * Utilizes inpatientComparisonService to populate the options for providers
     */
    public void showProviders() {
        setDrgDefinition();
        if (drgId == null) {
            resetAll();
        } else if (StringUtils.isBlank(state)) {
            cities = null;
            city = null;
            providers = null;
            providerId = null;
            result = null;
        } else if (StringUtils.isBlank(city)) {
            providers = null;
            providerId = null;
            result = null;
        } else {
            providers = ServiceManager.getInpatientComparisonService().getProviders(state, city, drgId);
            providerId = null;
            result = null;
        }
    }

    /**
     * Calls the showProviders method and returns the page name
     *
     * @return the page name
     */
    public String showProvidersAndReturn() {
        showProviders();
        return "inpatientcomparetwo";
    }

    /**
     * Utilizes inpatientComparisonService to populate the options for the
     * second list of providers
     */
    public void showCompareToProviders() {
        setDrgDefinition();
        if (drgId == null) {
            resetAll();
        } else if (StringUtils.isBlank(secondState)) {
            compareToCities = null;
            secondCity = null;
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        } else if (StringUtils.isBlank(secondCity)) {
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        } else {
            compareToProviders = ServiceManager.getInpatientComparisonService().getProviders(secondState, secondCity, drgId);
            secondProviderId = null;
            secondResult = null;
        }
    }

    /**
     * Calls the showCompareToProviders method and returns the page name
     *
     * @return the page name
     */
    public String showCompareToProvidersAndReturn() {
        showCompareToProviders();
        return "inpatientcomparetwo";
    }

    /**
     * Utilizes inpatientComparisonService to populate the first result
     */
    public void showResult() {
        setDrgDefinition();
        if (drgId == null) {
            resetAll();
        } else if (StringUtils.isBlank(state)) {
            cities = null;
            city = null;
            providers = null;
            providerId = null;
            result = null;
        } else if (StringUtils.isBlank(city)) {
            providers = null;
            providerId = null;
            result = null;
        } else if (providerId == null) {
            result = null;
        } else {
            result = ServiceManager.getInpatientComparisonService().getResult(providers, providerId, drgs, drgId);
        }
    }

    /**
     * Calls the showResult method and returns the page name
     *
     * @return the page name
     */
    public String showResultAndReturn() {
        showResult();
        return "inpatientcomparetwo";
    }

    /**
     * Utilizes inpatientComparisonService to populate the second result
     */
    public void showSecondResult() {
        setDrgDefinition();
        if (drgId == null) {
            resetAll();
        } else if (StringUtils.isBlank(secondState)) {
            compareToCities = null;
            secondCity = null;
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        } else if (StringUtils.isBlank(secondCity)) {
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        } else if (secondProviderId == null) {
            secondResult = null;
        } else {
            secondResult = ServiceManager.getInpatientComparisonService().getResult(compareToProviders, secondProviderId, drgs, drgId);
        }
    }

    /**
     * Calls the showSecondResult method and returns the page name
     *
     * @return the page name
     */
    public String showSecondResultAndReturn() {

        showSecondResult();
        return "inpatientcomparetwo";
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
     * Get the value of drgDefinition
     *
     * @return the value of drgDefinition
     */
    public String getDrgDefinition() {
        return drgDefinition;
    }

    /**
     * Set the value of drgDefinition
     *
     * @param drgDefinition new value of drgDefinition
     */
    public void setDrgDefinition(String drgDefinition) {
        this.drgDefinition = drgDefinition;
    }

    /**
     * Get the list of states to choose from
     *
     * @return the states
     */
    public ArrayList<String> getStates() {
        return states;
    }

    /**
     * Set the list of states to choose from
     *
     * @param states the states
     */
    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    /**
     * Get the second list of states to choose from
     *
     * @return the second list of states
     */
    public ArrayList<String> getCompareToStates() {
        return compareToStates;
    }

    /**
     * Set the second list of states to choose from
     *
     * @param compareToStates the second list of states
     */
    public void setCompareToStates(ArrayList<String> compareToStates) {
        this.compareToStates = compareToStates;
    }

    /**
     * the state getter
     *
     * @return the state selection
     */
    public String getState() {
        return state;
    }

    /**
     * The state setter
     *
     * @param state the state selection
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get the second state
     *
     * @return the second state
     */
    public String getSecondState() {
        return secondState;
    }

    /**
     * Set the second state
     *
     * @param secondState the second state
     */
    public void setSecondState(String secondState) {
        this.secondState = secondState;
    }

    /**
     * The cities getter
     *
     * @return the list of cities to choose from
     */
    public ArrayList<String> getCities() {
        return cities;
    }

    /**
     * The cities setter
     *
     * @param cities the list of cities to set
     */
    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    /**
     * Get the second list of cities to choose from
     *
     * @return the second list of cites
     */
    public ArrayList<String> getCompareToCities() {
        return compareToCities;
    }

    /**
     * Set the second list of cities to choose from
     *
     * @param compareToCities the second list of cites
     */
    public void setCompareToCities(ArrayList<String> compareToCities) {
        this.compareToCities = compareToCities;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the second city
     *
     * @return the second city
     */
    public String getSecondCity() {
        return secondCity;
    }

    /**
     * Set the second city
     *
     * @param secondCity the second city
     */
    public void setSecondCity(String secondCity) {
        this.secondCity = secondCity;
    }

    /**
     * Get the list of providers
     *
     * @return the list of providers
     */
    public ArrayList<Provider> getProviders() {
        return providers;
    }

    /**
     * Set the list of providers
     *
     * @param providers the list of providers
     */
    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    /**
     * Get the second list of providers
     *
     * @return the second list of providers
     */
    public ArrayList<Provider> getCompareToProviders() {
        return compareToProviders;
    }

    /**
     * Set the second list of providers
     *
     * @param compareToProviders the second list of providers
     */
    public void setCompareToProviders(ArrayList<Provider> compareToProviders) {
        this.compareToProviders = compareToProviders;
    }

    /**
     * Get the provider ID
     *
     * @return the provider ID
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * Set the provider ID
     *
     * @param providerId the provider ID
     */
    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    /**
     * Get the second provider ID
     *
     * @return the second provider ID
     */
    public Integer getSecondProviderId() {
        return secondProviderId;
    }

    /**
     * Set the second provider ID
     *
     * @param secondProviderId the second provider ID
     */
    public void setSecondProviderId(Integer secondProviderId) {
        this.secondProviderId = secondProviderId;
    }

    /**
     * Get the first result
     *
     * @return the first result
     */
    public InpatientComparisonResult getResult() {
        return result;
    }

    /**
     * Set the first result
     *
     * @param result the first result
     */
    public void setResult(InpatientComparisonResult result) {
        this.result = result;
    }

    /**
     * Get the second result
     *
     * @return the second result
     */
    public InpatientComparisonResult getSecondResult() {
        return secondResult;
    }

    /**
     * Set the second result
     *
     * @param secondResult the second result
     */
    public void setSecondResult(InpatientComparisonResult secondResult) {
        this.secondResult = secondResult;
    }

    /**
     * Resets variables to null.
     */
    private void resetAll() {
        drgDefinition = null;
        states = null;
        compareToStates = null;
        state = null;
        secondState = null;
        cities = null;
        compareToCities = null;
        city = null;
        secondCity = null;
        providers = null;
        compareToProviders = null;
        providerId = null;
        secondProviderId = null;
        result = null;
        secondResult = null;
    }

    /**
     * Finds the DRG definition for the selected DRG ID and sets the DRG
     * definition variable.
     */
    private void setDrgDefinition() {
        if (drgId != null) {
            for (DiagnosisRelatedGroup d : drgs) {
                if (d.getDrgId() == drgId) {
                    drgDefinition = d.getDrgDefinition();
                    break;
                }
            }
        }
    }
}
