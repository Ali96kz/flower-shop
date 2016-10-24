package com.epam.az.flower.shop.pool;

import com.epam.az.flower.shop.util.PropertyManager;
import com.epam.az.flower.shop.util.UtilClassException;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


public class ConnectionPool implements DataSource {
     String DB_DRIVER = "driver";
     String DB_URL = "url";
     String DB_USERNAME = "username";
     String DB_PASSWORD = "password";
     String DB_CONNECTIONS_LIMIT = "connections.limit";
     String DB_CONNECTION_TIMEOUT = "connection.timeout";
     String DATABASE_PROPERTIES = "database.properties";

    public static final UnsupportedOperationException UNSUPPORTED_OPERATION_EXCEPTION = new UnsupportedOperationException("Unsupported operation");
    private String driver;
    private String url;
    private String username;
    private String password;
    private int connectionsLimit;
    private int timeout;
    private volatile BlockingQueue<PooledConnection> freeConnections;
    private BlockingQueue<PooledConnection> usedConnections;

    public ConnectionPool() throws ConnectionPoolException {
        PropertyManager propertyManager = new PropertyManager();
        Properties properties;
        try {
            properties = propertyManager.readProperty(DATABASE_PROPERTIES);
        } catch (UtilClassException e) {
            throw new ConnectionPoolException("Could not load properties", e);

        }
        if (properties != null) {
            this.driver = properties.getProperty(DB_DRIVER);
            this.url = properties.getProperty(DB_URL);
            this.username = properties.getProperty(DB_USERNAME);
            this.password = properties.getProperty(DB_PASSWORD);
            this.connectionsLimit = Integer.parseInt(properties.getProperty(DB_CONNECTIONS_LIMIT));
            this.timeout = Integer.parseInt(properties.getProperty(DB_CONNECTION_TIMEOUT));
        }
        initConnections();
    }

    private void initConnections() {
        if (freeConnections == null) {
            synchronized (ConnectionPool.class) {
                if (freeConnections == null) {
                    freeConnections = new ArrayBlockingQueue<>(connectionsLimit);
                }
            }
        }
        try {
            Class.forName(driver);
            for (int i = 0; i < connectionsLimit; i++) {
                Connection connection = getPooledConnection();
                freeConnections.add((PooledConnection) connection);
            }
        } catch (ClassNotFoundException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public void close() throws ConnectionPoolException {
        closeAllConnectionsInQueue(freeConnections);
        closeAllConnectionsInQueue(usedConnections);
    }

    private void closeAllConnectionsInQueue(BlockingQueue<PooledConnection> queue) throws ConnectionPoolException {
        for (PooledConnection connection : queue) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Could not close connection", e);
            }
        }
    }

    private Connection getPooledConnection() throws ConnectionPoolException {
        Connection pooledConnection;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            pooledConnection = new PooledConnection(connection);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Could not get PooledConnection" + e.getMessage(), e);
        }
        return pooledConnection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        PooledConnection connection;
        try {
            if (usedConnections == null)
                usedConnections = new ArrayBlockingQueue<>(connectionsLimit);

            connection = freeConnections.poll(timeout, TimeUnit.SECONDS);
            if (connection == null)
                throw new SQLException("No free connection in pool");

            usedConnections.put(connection);
        } catch (InterruptedException e) {
            throw new SQLException("Could not get connection", e);
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    private class PooledConnection extends com.mysql.cj.jdbc.ConnectionImpl {


        private final Connection connection;

        PooledConnection(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void close() throws SQLException {
            try {
                setAutoCommit(true);
                usedConnections.remove(this);
                freeConnections.put(this);
            } catch (SQLException | InterruptedException e) {
                throw new SQLException("Could not release current connection", e);
            }
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() {
            try {
                return connection.createClob();
            } catch (SQLException e) {
                throw new ConnectionPoolException("", e);
            }

        }

        @Override
        public Blob createBlob() {
            try {
                return connection.createBlob();
            } catch (SQLException e) {
                throw new ConnectionPoolException("", e);
            }
        }

        @Override
        public NClob createNClob() {
            try {
                return connection.createNClob();
            } catch (SQLException e) {
                throw new ConnectionPoolException("", e);
            }
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        Connection getConnection() {
            return this.connection;
        }
    }
}
