package com.daniel.hospitalcharges.data.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class closes database objects
 *
 * @author Bryan Daniel
 */
public class DatabaseUtility {

    // not called
    private DatabaseUtility() {
    }

    /**
     * The logger for this class
     */
    @SuppressWarnings("FieldMayBeFinal")
    private static Logger logger = LogManager.getLogger(DatabaseUtility.class);

    /**
     * This static method closes callable statements
     *
     * @param callableStatement the callable statement
     */
    public static void closeCallableStatement(Statement callableStatement) {
        try {
            if (callableStatement != null) {
                callableStatement.close();
            }
        } catch (SQLException e) {
            logger.error("A SQLException occurred in the closeCallableStatement method.", e);
        }
    }

    /**
     * This static method closes result sets
     *
     * @param resultSet the result set
     */
    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("A SQLException occurred in the closeResultSet method.", e);
        }
    }
}
