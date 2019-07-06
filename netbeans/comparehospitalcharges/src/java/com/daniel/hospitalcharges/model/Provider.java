package com.daniel.hospitalcharges.model;

import java.io.Serializable;

/**
 * This class contains information about a health care provider.
 *
 * @author Bryan Daniel
 */
public class Provider implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5853779034907199899L;

    /**
     * The id
     */
    private int id;
    /*
     The name
     */
    private String name;
    /**
     * The street
     */
    private String street;
    /**
     * The city
     */
    private String city;
    /**
     * The state
     */
    private String state;
    /**
     * The zip code
     */
    private String zipCode;

    /**
     * This constructor sets the instance variables to default values.
     */
    public Provider() {
        name = "";
        street = "";
        city = "";
        state = "";
        zipCode = "";
    }

    /**
     * Returns the id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the street
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Returns the city
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the zip code
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the id
     *
     * @param newId the id
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Sets the name
     *
     * @param newName the name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the street
     *
     * @param newStreet the street
     */
    public void setStreet(String newStreet) {
        street = newStreet;
    }

    /**
     * Sets the city
     *
     * @param newCity the city
     */
    public void setCity(String newCity) {
        city = newCity;
    }

    /**
     * Sets the state
     *
     * @param newState the state
     */
    public void setState(String newState) {
        state = newState;
    }

    /**
     * Sets the zip code
     *
     * @param newZipCode the zip code
     */
    public void setZipCode(String newZipCode) {
        zipCode = newZipCode;
    }

}
