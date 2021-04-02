package RegistrationDAO;

import presentationlayer.LoginUI;


import java.sql.*;
import java.util.HashMap;

public class ConnectDatabase
{
    private Connection conn = null;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private String selectQuery;
    private String insertQuery;

    Statement statement;
    public Connection getConnection(HashMap<String,String> userInput)
    {
        try
        {
            this.dbURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_3_DEVINT?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
            this.dbUsername = "CSCI5308_3_DEVINT_USER";
            this.dbPassword = "LGAf8ynVwrSQUa3m";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = conn.createStatement();
            if(userInput.containsKey("experience") && userInput.containsKey("jobType"))
            {
                selectQuery = "SELECT * FROM service_provider where Email='" + userInput.get("email") + "'";
                insertQuery="INSERT INTO service_provider(firstName, lastName, spAddress, spContact, Email, Password, spExperience, spJobType, spHourlyRate) values('" + userInput.get("firstName") + "', '" + userInput.get("lastName") + "', '" + userInput.get("address") + "', '" + userInput.get("contact") + "', '" + userInput.get("email") + "', '" + userInput.get("password") + "', '" + userInput.get("experience") + "', '" + userInput.get("jobType") + "', '" + userInput.get("hourlyRate") + "');";
            }
            else
            {
                selectQuery = "SELECT * FROM customer where Email='" + userInput.get("email") + "'";
                insertQuery="INSERT INTO customer(firstName,lastName,cContact,cAddress,Email,Password) values('" + userInput.get("firstName") + "', '" + userInput.get("lastName") + "', '" + userInput.get("contact") + "' , '" + userInput.get("address") + "', '" + userInput.get("email") + "', '" + userInput.get("password") + "');";
            }

            ResultSet rs = statement.executeQuery(selectQuery);
            if(rs.next())
            {
                String name = rs.getString("firstName") + " " + rs.getString("lastName");
            }
            else
            {
                statement.executeUpdate(insertQuery);
                System.out.println("Thank you for registering with us.");
                LoginUI log = new LoginUI();
            }
            conn.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}