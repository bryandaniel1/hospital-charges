package com.daniel.hospitalcharges.service;

/**
 * This class constructs and returns service implementations.
 *
 * @author Bryan Daniel
 */
public class ServiceManager {

    /**
     * The service for comparing regional inpatient charges
     */
    private static RegionalInpatientService regionalInpatientService = null;

    /**
     * The service for comparing regional outpatient charges
     */
    private static RegionalOutpatientService regionalOutpatientService = null;

    /**
     * The service for comparing two inpatient charges
     */
    private static InpatientComparisonService inpatientComparisonService = null;

    /**
     * The service for comparing two outpatient charges
     */
    private static OutpatientComparisonService outpatientComparisonService = null;

    /**
     * This block constructs the service implementations.
     */
    static {
        regionalInpatientService = new SimpleRegionalInpatientService();
        regionalOutpatientService = new SimpleRegionalOutpatientService();
        inpatientComparisonService = new SimpleInpatientComparisonService();
        outpatientComparisonService = new SimpleOutpatientComparisonService();
    }

    /**
     * This method returns a regional inpatient service implementation.
     *
     * @return the regional inpatient service
     */
    public static RegionalInpatientService getRegionalInpatientService() {
        return regionalInpatientService;
    }

    /**
     * This method returns a regional outpatient service implementation.
     *
     * @return the regional outpatient service
     */
    public static RegionalOutpatientService getRegionalOutpatientService() {
        return regionalOutpatientService;
    }

    /**
     * This method returns an inpatient comparison service implementation.
     *
     * @return the inpatient comparison service
     */
    public static InpatientComparisonService getInpatientComparisonService() {
        return inpatientComparisonService;
    }

    /**
     * This method returns an outpatient comparison service implementation.
     *
     * @return the outpatient comparison service
     */
    public static OutpatientComparisonService getOutpatientComparisonService() {
        return outpatientComparisonService;
    }
}
