package com.daniel.hospitalcharges.managedbean;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.apache.commons.lang3.text.WordUtils;

/**
 * This managed bean handles the properties required for viewing a map of the
 * hospital locationStreetAndCity.
 *
 * @author Bryan Daniel
 */
@ManagedBean
@SessionScoped
public class MapBean implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 2194952841136022381L;

    /**
     * The message for the map page
     */
    private String mapMessage;

    /**
     * The address for the map page
     */
    private String mapAddress;

    /**
     * The street and city portion of the address
     */
    private String locationStreetAndCity;

    /**
     * The state portion of the address
     */
    private String locationState;

    /**
     * The name of the locationStreetAndCity
     */
    private String mapLocationName;

    /**
     * Creates a new instance of MapBean
     */
    public MapBean() {
        mapMessage = null;
        mapAddress = null;
        locationStreetAndCity = null;
        locationState = null;
        mapLocationName = null;
    }

    /**
     * This method sets the map address and message and then directs the user to
     * the map page.
     *
     * @return the map page
     */
    public String viewMap() {

        setMapAddress(WordUtils.capitalizeFully(locationStreetAndCity) + locationState);

        if (mapAddress == null) {
            setMapMessage("The hospital address was not specified.");
        } else {
            setMapMessage(null);
        }

        return "/map/index";
    }

    /**
     * Get the value of mapMessage
     *
     * @return the value of mapMessage
     */
    public String getMapMessage() {
        return mapMessage;
    }

    /**
     * Set the value of mapMessage
     *
     * @param mapMessage new value of mapMessage
     */
    public void setMapMessage(String mapMessage) {
        this.mapMessage = mapMessage;
    }

    /**
     * Get the value of mapAddress
     *
     * @return the value of mapAddress
     */
    public String getMapAddress() {
        return mapAddress;
    }

    /**
     * Set the value of mapAddress
     *
     * @param mapAddress new value of mapAddress
     */
    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    /**
     * Get the value of locationStreetAndCity
     *
     * @return the value of locationStreetAndCity
     */
    public String getLocationStreetAndCity() {
        return locationStreetAndCity;
    }

    /**
     * Set the value of locationStreetAndCity
     *
     * @param locationStreetAndCity new value of locationStreetAndCity
     */
    public void setLocationStreetAndCity(String locationStreetAndCity) {
        this.locationStreetAndCity = locationStreetAndCity;
    }

    /**
     * Get the value of locationState
     *
     * @return the value of locationState
     */
    public String getLocationState() {
        return locationState;
    }

    /**
     * Set the value of locationState
     *
     * @param locationState new value of locationState
     */
    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    /**
     * Get the value of mapLocationName
     *
     * @return the value of mapLocationName
     */
    public String getMapLocationName() {
        return mapLocationName;
    }

    /**
     * Set the value of mapLocationName
     *
     * @param mapLocationName new value of mapLocationName
     */
    public void setMapLocationName(String mapLocationName) {
        this.mapLocationName = WordUtils.capitalizeFully(mapLocationName);
    }
}
