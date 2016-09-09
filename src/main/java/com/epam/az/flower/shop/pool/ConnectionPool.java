package com.epam.az.flower.shop.pool;

import com.epam.az.flower.shop.adapter.StringAdapter;
import com.epam.az.flower.shop.util.PropertyWorker;
import com.epam.az.flower.shop.util.PropertyWorkerException;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionPool  implements DataSource{
    private ConnectionStack<Connection> stack = new ConnectionStack<>();
    private static ConnectionPool connectionPool;
    private int max;
    private int initial;
    private int increment;
    private int connNumber;
    private String driverName, url, username, password;

    private ConnectionPool() {
        init();
        addConnectionInStack(initial);
    }

    private void init() {
        StringAdapter stringAdapter = new StringAdapter();
        PropertyWorker propertyWorker = new PropertyWorker();
        try {
            Properties properties = propertyWorker.readProperty("database.properties");
            driverName = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            initial = stringAdapter.toInt(properties.getProperty("connection.initial"));
            max = stringAdapter.toInt(properties.getProperty("connection.limit"));
            increment = stringAdapter.toInt(properties.getProperty("connection.increment"));
        } catch (PropertyWorkerException e) {
            e.printStackTrace();
        }
    }

    private void addConnectionInStack(int count) {
        try {
            if(connNumber + count <= max) {
                Class.forName(driverName);
                for (int i = 0; i < initial; i++) {
                    Connection conn = DriverManager.getConnection(url, username, password);
                    stack.push(conn);
                    connNumber++;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null)
            connectionPool = new ConnectionPool();
        return connectionPool;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (stack.isEmpty()) {
            addConnectionInStack(increment);
            getConnection("root", "root");
        }
        return stack.pop();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }


    public void returnConnection(Connection connection){
        stack.push(connection);
        connNumber--;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
