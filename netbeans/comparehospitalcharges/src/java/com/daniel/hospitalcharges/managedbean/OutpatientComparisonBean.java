package com.daniel.hospitalcharges.managedbean;

import com.daniel.hospitalcharges.service.ServiceManager;
import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.OutpatientComparisonResult;
import com.daniel.hospitalcharges.model.Provider;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;

/**
 * This managed bean handles the properties required for comparing two
 * outpatient search results.
 *
 * @author Bryan Daniel
 */
@ManagedBean
@SessionScoped
public class OutpatientComparisonBean implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -1190845609012603435L;

    /**
     * The list of ambulatory payment classifications to choose from
     */
    private ArrayList<AmbulatoryPaymentClassification> apcs;

    /**
     * The selected APC ID
     */
    private Integer apcId;

    /**
     * The APC definition
     */
    private String apcDefinition;

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
    private OutpatientComparisonResult result;

    /**
     * The second search result for comparison
     */
    private OutpatientComparisonResult secondResult;

    /**
     * Sets initial values for instance variables.
     */
    public OutpatientComparisonBean() {
        apcs = ServiceManager.getOutpatientComparisonService().getAPCs();
        apcId = null;
        resetAll();
    }

    /**
     * Utilizes outpatientComparisonService to populate the options for states
     */
    public void showStates() {
        if (apcId != null) {
            states = ServiceManager.getOutpatientComparisonService().getStates(apcId);
            setApcDefinition();
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
        return "outpatientcomparetwo";
    }

    /**
     * Utilizes outpatientComparisonService to populate the options for cities
     */
    public void showCities() {
        setApcDefinition();
        if (apcId == null) {
            resetAll();
        } else if (StringUtils.isBlank(state)) {
            cities = null;
            city = null;
            providers = null;
            providerId = null;
            result = null;
        } else {
            cities = ServiceManager.getOutpatientComparisonService().getCitiesToCompare(apcId, state);
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
        return "outpatientcomparetwo";
    }

    /**
     * Utilizes outpatientComparisonService to populate the options for the
     * second list of cities
     */
    public void showCompareToCities() {
        setApcDefinition();
        if (apcId == null) {
            resetAll();
        } else if (StringUtils.isBlank(secondState)) {
            compareToCities = null;
            secondCity = null;
            compareToProviders = null;
            secondProviderId = null;
            secondResult = null;
        } else {
            compareToCities = ServiceManager.getOutpatientComparisonService().getCitiesToCompare(apcId, secondState);
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
        return "outpatientcomparetwo";
    }

    /**
     * Utilizes outpatientComparisonService to populate the options for
     * providers
     */
    public void showProviders() {
        setApcDefinition();
        if (apcId == null) {
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
            providers = ServiceManager.getOutpatientComparisonService().getProviders(state, city, apcId);
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
        return "outpatientcomparetwo";
    }

    /**
     * Utilizes outpatientComparisonService to populate the options for the
     * second list of providers
     */
    public void showCompareToProviders() {
        setApcDefinition();
        if (apcId == null) {
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
            compareToProviders = ServiceManager.getOutpatientComparisonService().getProviders(secondState, secondCity, apcId);
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
        return "outpatientcomparetwo";
    }

    /**
     * Utilizes outpatientComparisonService to populate the first result
     */
    public void showResult() {
        setApcDefinition();
        if (apcId == null) {
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
            result = ServiceManager.getOutpatientComparisonService().getResult(providers, providerId, apcs, apcId);
        }
    }

    /**
     * Calls the showResult method and returns the page name
     *
     * @return the page name
     */
    public String showResultAndReturn() {
        showResult();
        return "outpatientcomparetwo";
    }

    /**
     * Utilizes outpatientComparisonService to populate the second result
     */
    public void showSecondResult() {
        setApcDefinition();
        if (apcId == null) {
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
            secondResult = ServiceManager.getOutpatientComparisonService().getResult(compareToProviders, secondProviderId, apcs, apcId);
        }
    }

    /**
     * Calls the showSecondResult method and returns the page name
     *
     * @return the page name
     */
    public String showSecondResultAndReturn() {
        showSecondResult();
        return "outpatientcomparetwo";
    }

    /**
     * Get the ambulatory payment classification
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
    public void setApcs(ArrayList<AmbulatoryPaymentClassification> apcs) {
        this.apcs = apcs;
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
     * Get the value of apcDefinition
     *
     * @return the value of apcDefinition
     */
    public String getApcDefinition() {
        return apcDefinition;
    }

    /**
     * Set the value of apcDefinition
     *
     * @param apcDefinition new value of apcDefinition
     */
    public void setApcDefinition(String apcDefinition) {
        this.apcDefinition = apcDefinition;
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
    public OutpatientComparisonResult getResult() {
        return result;
    }

    /**
     * Set the first result
     *
     * @param result the first result
     */
    public void setResult(OutpatientComparisonResult result) {
        this.result = result;
    }

    /**
     * Get the second result
     *
     * @return the second result
     */
    public OutpatientComparisonResult getSecondResult() {
        return secondResult;
    }

    /**
     * Set the second result
     *
     * @param secondResult the second result
     */
    public void setSecondResult(OutpatientComparisonResult secondResult) {
        this.secondResult = secondResult;
    }

    /**
     * Resets variables to null.
     */
    private void resetAll() {
        apcDefinition = null;
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
     * Finds the APC definition for the selected APC ID and sets the APC
     * definition variable.
     */
    private void setApcDefinition() {
        if (apcId != null) {
            for (AmbulatoryPaymentClassification a : apcs) {
                if (a.getApcId() == apcId) {
                    apcDefinition = a.getApcDefinition();
                    break;
                }
            }
        }
    }
}
