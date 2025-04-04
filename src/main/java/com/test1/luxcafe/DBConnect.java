package com.test1.luxcafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    private PreparedStatement preparedStatement;
    ResultSet resultSet;
    private static Connection connection;

    public DBConnect() {
        createConnection();
    }

    public void prepareStatement(String statement, boolean hasValue) {
        try {
            preparedStatement = connection.prepareStatement(statement);
            if (!hasValue) {
//                System.out.println("::"+statement);
                resultSet = preparedStatement.executeQuery();
            }
        } catch (SQLException ex) {
            System.out.println("statment: " + statement);
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertStatement(String statement, boolean hasValue) {
        try {
            preparedStatement = connection.prepareStatement(statement, java.sql.Statement.RETURN_GENERATED_KEYS);
            if (!hasValue) {
//                System.out.println("::"+statement);
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
            }
        } catch (SQLException ex) {
            System.out.println("statment: " + statement);
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createConnection() {
        String url = "jdbc:mysql://localhost:3306/lux_cafe?user=root&password=root3306";
        try {
            if (DBConnect.connection == null) {
                DBConnect.connection = DriverManager.getConnection(url);
            } else {
                if (DBConnect.connection.isClosed()) {
                    DBConnect.connection = DriverManager.getConnection(url);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
