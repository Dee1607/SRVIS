package database;

import java.sql.*;

public class ConnectionToDB {

    private Connection conn = null;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;

    // Creating Database Connection..
    public Connection getConnection() throws SQLException {
        try {
            this.dbURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_3_DEVINT?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
            this.dbUsername = "CSCI5308_3_DEVINT_USER";
            this.dbPassword = "LGAf8ynVwrSQUa3m";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
//            conn.setAutoCommit(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Closing the database connection..
    public void closeConnection() throws SQLException{
        conn.close();
    }
}