package com.epam.az.flower.shop.pool;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class ConnectionPool  implements DataSource{
    private ConnectionStack<Connection> stack = new ConnectionStack<>();
    private static ConnectionPool connectionPool;
    private int max = 7;
    private int initial = 2;
    private int increment = 1;
    private int connNumber  = 0;
    private ConnectionPool() {
        addConnectionInStack(initial);
    }

    private void addConnectionInStack(int count) {
        try {
            if(connNumber + count <= max) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                for (int i = 0; i < initial; i++) {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowershop", "root", "root");
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
        return null;
    }

    public Connection getConnection(String username, String password) {
        if (stack.isEmpty()) {
            addConnectionInStack(increment);
            getConnection("root", "root");
        }
        return stack.pop();
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
