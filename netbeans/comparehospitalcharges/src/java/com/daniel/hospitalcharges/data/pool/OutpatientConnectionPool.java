package com.daniel.hospitalcharges.data.pool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class returns and frees connections to the outpatient charges database.
 *
 * @author Bryan Daniel
 */
public class OutpatientConnectionPool {

    /**
     * The connection pool
     */
    private static OutpatientConnectionPool pool = null;

    /**
     * The data source
     */
    private static DataSource dataSource = null;

    /**
     * The logger for this class
     */
    private static Logger logger = LogManager.getLogger(OutpatientConnectionPool.class);

    /**
     * The private constructor ensures that only a single instance of
     * OutpatientConnectionPool is created.
     */
    private OutpatientConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/hospital_outpatient");
            logger.info("OutpatientConnectionPool successfully constructed.");
        } catch (NamingException e) {
            logger.error("OutpatientConnectionPool construction failed.", e);
        }
    }

    /**
     * This static method returns the OutpatientConnectionPool object.
     *
     * @return the OutpatientConnectionPool object
     */
    public synchronized static OutpatientConnectionPool getInstance() {
        if (pool == null) {
            pool = new OutpatientConnectionPool();
        }
        return pool;
    }

    /**
     * This method returns the connection to the database.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Exception occurred in getConnection method.", e);
            return null;
        }
    }

    /**
     * This method frees the connection to the database.
     *
     * @param connection the connection
     */
    public void freeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Closing of given connection failed.", e);
        }
    }
}
