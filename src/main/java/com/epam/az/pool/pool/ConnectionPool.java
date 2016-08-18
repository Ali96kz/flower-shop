package com.epam.az.pool.pool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool  {
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

    public Connection getConnection() {
        if (stack.isEmpty()) {
            addConnectionInStack(increment);
            getConnection();
        }
        return stack.pop();
    }
    public void returnConnection(Connection connection){
        stack.push(connection);
        connNumber--;
    }
}
