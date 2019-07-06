package com.daniel.hospitalcharges.data;

import com.daniel.hospitalcharges.data.pool.InpatientConnectionPool;
import com.daniel.hospitalcharges.model.InpatientComparisonResult;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.model.Provider;
import com.daniel.hospitalcharges.data.utility.DatabaseUtility;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class retrieves provider data from the database.
 *
 * @author Bryan Daniel
 */
public class InpatientIO {

    /**
     * The scale used for decimal numbers
     */
    public static final int SCALE = 2;

    // not called
    private InpatientIO() {
    }

    /**
     * The logger for this class
     */
    @SuppressWarnings("FieldMayBeFinal")
    private static Logger logger = LogManager.getLogger(InpatientIO.class);

    /**
     * This method retrieves the complete list of diagnosis-related group
     * definitions.
     *
     * @return the list of DRG definitions or null if an error occurs
     */
    public static ArrayList<DiagnosisRelatedGroup> getDiagnosisRelatedGroups() {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<DiagnosisRelatedGroup> diagnosisRelatedGroups = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("{CALL getDRGs(?)}");
            callableStatement.registerOutParameter(1, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                DiagnosisRelatedGroup diagnosisRelatedGroup = new DiagnosisRelatedGroup();
                diagnosisRelatedGroup.setDrgId(resultSet.getInt("drg id"));
                diagnosisRelatedGroup.setDrgDefinition(resultSet.getString("drg definition"));
                if (!diagnosisRelatedGroups.contains(diagnosisRelatedGroup)) {
                    diagnosisRelatedGroups.add(diagnosisRelatedGroup);
                }
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred in getDiagnosisRelatedGroups method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return diagnosisRelatedGroups;
    }

    /**
     * This method returns a list of states associated with a given DRG ID.
     *
     * @param drgId the DRG ID
     * @return the list of states or null if an error occurs
     */
    public static ArrayList<String> getStates(int drgId) {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getStates(?, ?)}");
            callableStatement.setInt(1, drgId);
            callableStatement.registerOutParameter(2, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            ArrayList<String> states = new ArrayList<>();
            while (resultSet.next()) {
                String state = resultSet.getString("state");
                if (!states.contains(state)) {
                    states.add(state);
                }
            }
            return states;
        } catch (SQLException e) {
            logger.error("SQLException occurred in getStates method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
    }

    /**
     * This method returns a list of cities in the given state for a DRG charge
     * comparison.
     *
     * @param drgId the DRG ID
     * @param providerState the state
     * @return the list of cities or null if an error occurs
     */
    public static ArrayList<String> getCitiesToCompare(int drgId, String providerState) {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getCitiesToCompare(?, ?, ?)}");
            callableStatement.setInt(1, drgId);
            callableStatement.setString(2, providerState);
            callableStatement.registerOutParameter(3, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            ArrayList<String> cities = new ArrayList<>();
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                if (!cities.contains(city)) {
                    cities.add(city);
                }
            }
            return cities;
        } catch (SQLException e) {
            logger.error("SQLException occurred in getCitiesToCompare method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
    }

    /**
     * This method returns a list of cities in the given state.
     *
     * @param providerState the state
     * @return the list of cities or null if an error occurs
     */
    public static ArrayList<String> getCities(String providerState) {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getCities(?, ?)}");
            callableStatement.setString(1, providerState);
            callableStatement.registerOutParameter(2, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            ArrayList<String> cities = new ArrayList<>();
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                if (!cities.contains(city)) {
                    cities.add(city);
                }
            }
            return cities;
        } catch (SQLException e) {
            logger.error("SQLException occurred in getCities method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
    }

    /**
     * This method retrieves a list of providers by state and city.
     *
     * @param state the state
     * @param city the city
     * @param drgId the DRG ID
     * @return the list of providers or null if an error occurs
     */
    public static ArrayList<Provider> getProviders(String state, String city,
            int drgId) {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Provider> providers = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("{CALL getProviders(?, ?, ?, ?)}");
            callableStatement.setInt(1, drgId);
            callableStatement.setString(2, city);
            callableStatement.setString(3, state);
            callableStatement.registerOutParameter(4, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                providers.add(provider);
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred in getProviders method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return providers;
    }

    /**
     * This method retrieves a list of diagnosis-related group definitions by
     * state and city.
     *
     * @param selectedState the state
     * @param selectedCity the city
     * @return the list of DRG definitions or null if an error occurs
     */
    public static ArrayList<DiagnosisRelatedGroup> getDiagnosisRelatedGroupsByRegion(String selectedState,
            String selectedCity) {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<DiagnosisRelatedGroup> diagnosisRelatedGroups = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("{CALL getRegionalDRGs(?, ?, ?)}");
            callableStatement.setString(1, selectedCity);
            callableStatement.setString(2, selectedState);
            callableStatement.registerOutParameter(3, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                DiagnosisRelatedGroup diagnosisRelatedGroup = new DiagnosisRelatedGroup();
                diagnosisRelatedGroup.setDrgId(resultSet.getInt("drg id"));
                diagnosisRelatedGroup.setDrgDefinition(resultSet.getString("drg definition"));
                diagnosisRelatedGroups.add(diagnosisRelatedGroup);
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred in getDiagnosisRelatedGroupsByRegion method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return diagnosisRelatedGroups;
    }

    /**
     * This method retrieves a charge description by the diagnosis-related group
     * ID and the provider ID.
     *
     * @param drgId the DRG ID
     * @param providerId the provider ID
     * @return the charges or null if an error occurs
     */
    public static DiagnosisRelatedGroup getCharges(int drgId, int providerId) {

        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        DiagnosisRelatedGroup diagnosisRelatedGroup = new DiagnosisRelatedGroup();

        try {
            callableStatement = connection.prepareCall("{CALL getCharges(?, ?, ?)}");
            callableStatement.setInt(1, drgId);
            callableStatement.setInt(2, providerId);
            callableStatement.registerOutParameter(3, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                diagnosisRelatedGroup.setAvgCharges(resultSet.getString("avg charges"));
                diagnosisRelatedGroup.setAvgPayments(resultSet.getString("avg payments"));
                diagnosisRelatedGroup.setAvgMedicarePayments(resultSet.getString("avg medicare payments"));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                diagnosisRelatedGroup.setAvgChargesPercentileRank(resultSet.getBigDecimal("avg charges percentile")
                        .setScale(SCALE, BigDecimal.ROUND_HALF_UP));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                diagnosisRelatedGroup.setAvgPaymentsPercentileRank(resultSet.getBigDecimal("avg payments percentile")
                        .setScale(SCALE, BigDecimal.ROUND_HALF_UP));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                diagnosisRelatedGroup.setAvgMedicarePaymentsPercentileRank(resultSet.getBigDecimal("avg medicare payments percentile")
                        .setScale(SCALE, BigDecimal.ROUND_HALF_UP));
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred in getCharges method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return diagnosisRelatedGroup;
    }

    /**
     * This method returns a list of charge descriptions for a geographic
     * region.
     *
     * @param state the state
     * @param city the city
     * @param drgId the DRG ID
     * @return the list of comparison results or null if an error occurs
     */
    public static ArrayList<InpatientComparisonResult> getRegionalResults(String state,
            String city, int drgId) {
        ArrayList<InpatientComparisonResult> results = new ArrayList<>();
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("{CALL getRegionalCharges(?, ?, ?, ?)}");

            callableStatement.setInt(1, drgId);
            callableStatement.setString(2, city);
            callableStatement.setString(3, state);
            callableStatement.registerOutParameter(4, java.sql.Types.TINYINT);

            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                InpatientComparisonResult inpatientComparisonResult = new InpatientComparisonResult();
                DiagnosisRelatedGroup diagnosisRelatedGroup = new DiagnosisRelatedGroup();
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                diagnosisRelatedGroup.setDrgId(resultSet.getInt("drg id"));
                diagnosisRelatedGroup.setDrgDefinition(resultSet.getString("drg definition"));
                diagnosisRelatedGroup.setAvgCharges(resultSet.getString("avg charges"));
                diagnosisRelatedGroup.setAvgPayments(resultSet.getString("avg payments"));
                diagnosisRelatedGroup.setAvgMedicarePayments(resultSet.getString("avg medicare payments"));
                inpatientComparisonResult.setProvider(provider);
                inpatientComparisonResult.setDrg(diagnosisRelatedGroup);
                results.add(inpatientComparisonResult);
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred in getRegionalResults method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return results;
    }
}
