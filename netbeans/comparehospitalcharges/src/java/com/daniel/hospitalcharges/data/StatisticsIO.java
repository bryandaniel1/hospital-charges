package com.daniel.hospitalcharges.data;

import com.daniel.hospitalcharges.data.pool.OutpatientConnectionPool;
import com.daniel.hospitalcharges.data.pool.InpatientConnectionPool;
import com.daniel.hospitalcharges.model.Provider;
import com.daniel.hospitalcharges.model.StatisticsResult;
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
 * Connects to the databases to retrieve statistical information on DRG or APC
 * charges
 *
 * @author Bryan Daniel
 */
public class StatisticsIO {

    /**
     * The logger for this class
     */
    @SuppressWarnings("FieldMayBeFinal")
    private static Logger logger = LogManager.getLogger(StatisticsIO.class);

    /**
     * Retrieves the diagnosis-related group charge statistics.
     *
     * @param drgId the DRG ID
     * @return the statistics or null if an error occurs
     */
    public static StatisticsResult getDiagnosisRelatedGroupStats(int drgId) {
        InpatientConnectionPool pool = InpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        StatisticsResult statsResult = new StatisticsResult();

        try {
            callableStatement = connection.prepareCall("{CALL getDRGStats(?, ?)}");
            callableStatement.setInt(1, drgId);
            callableStatement.registerOutParameter(2, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }

            //single stats
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                statsResult.setAvgCharge(resultSet.getBigDecimal("avg charge"));
                statsResult.setMedian(resultSet.getBigDecimal("median"));
                statsResult.setStdDeviation(resultSet.getBigDecimal("std deviation"));
                statsResult.setRange(resultSet.getBigDecimal("range"));
                statsResult.setMinCharge(resultSet.getBigDecimal("min charge"));
                statsResult.setMaxCharge(resultSet.getBigDecimal("max charge"));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }

            //all charges for the histogram
            resultSet = callableStatement.getResultSet();
            ArrayList<BigDecimal> charges = new ArrayList<>();
            while (resultSet.next()) {
                charges.add(resultSet.getBigDecimal("avg charges"));
            }
            statsResult.setAllCharges(charges);

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }

            //max charge provider
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("max provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                statsResult.setMaxProvider(provider);
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }

            //min charge provider
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("min provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                statsResult.setMinProvider(provider);
            }
        } catch (SQLException e) {
            logger.error("A SQLException occurred in the getDiagnosisRelatedGroupStats method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return statsResult;
    }

    /**
     * Retrieves the ambulatory payment classification charge statistics
     *
     * @param apcId the APC ID
     * @return the statistics or null if an error occurs
     */
    public static StatisticsResult getAmbulatoryPaymentClassificationStats(int apcId) {
        OutpatientConnectionPool pool = OutpatientConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        StatisticsResult statsResult = new StatisticsResult();

        try {
            callableStatement = connection.prepareCall("{CALL getAPCStats(?, ?)}");
            callableStatement.setInt(1, apcId);
            callableStatement.registerOutParameter(2, java.sql.Types.TINYINT);

            //reads true if result set exists
            boolean success = callableStatement.execute();
            if (!success) {
                return null;
            }

            //single stats
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                statsResult.setAvgCharge(resultSet.getBigDecimal("avg charge"));
                statsResult.setMedian(resultSet.getBigDecimal("median"));
                statsResult.setStdDeviation(resultSet.getBigDecimal("std deviation"));
                statsResult.setRange(resultSet.getBigDecimal("range"));
                statsResult.setMinCharge(resultSet.getBigDecimal("min charge"));
                statsResult.setMaxCharge(resultSet.getBigDecimal("max charge"));
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }

            //all charges for the histogram
            resultSet = callableStatement.getResultSet();
            ArrayList<BigDecimal> charges = new ArrayList<>();
            while (resultSet.next()) {
                charges.add(resultSet.getBigDecimal("avg charges"));
            }
            statsResult.setAllCharges(charges);

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }

            //max charge provider
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("max provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                statsResult.setMaxProvider(provider);
            }

            success = callableStatement.getMoreResults();
            if (!success) {
                return null;
            }

            //min charge provider
            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                Provider provider = new Provider();
                provider.setId(resultSet.getInt("min provider id"));
                provider.setName(resultSet.getString("provider name"));
                provider.setStreet(resultSet.getString("provider street"));
                provider.setCity(resultSet.getString("provider city"));
                provider.setState(resultSet.getString("provider state"));
                provider.setZipCode(resultSet.getString("provider zip"));
                statsResult.setMinProvider(provider);
            }
        } catch (SQLException e) {
            logger.error("A SQLException occurred in the getAmbulatoryPaymentClassificationStats method.", e);
            return null;
        } finally {
            DatabaseUtility.closeResultSet(resultSet);
            DatabaseUtility.closeCallableStatement(callableStatement);
            pool.freeConnection(connection);
        }
        return statsResult;
    }
}
