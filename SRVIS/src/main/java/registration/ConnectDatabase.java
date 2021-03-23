package registration;

import database.IConnectionToDB;

import java.sql.*;
import java.util.HashMap;

public class ConnectDatabase implements IConnectionToDB {
    private Connection conn = null;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private String selectQuery;
    private String insertQuery;

    Statement statement;
    public Connection getConnection(HashMap<String,String> userInput){
        try {
            this.dbURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_3_DEVINT?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
            this.dbUsername = "CSCI5308_3_DEVINT_USER";
            this.dbPassword = "LGAf8ynVwrSQUa3m";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = conn.createStatement();
            if(userInput.containsKey("experience") && userInput.containsKey("description")){

            }
            else{
                selectQuery = "SELECT * FROM customer where cEmail='" + userInput.get("email") + "'";
                insertQuery="INSERT INTO customer values('" + userInput.get("firstName") + "', '" + userInput.get("lastName") + "', '" + userInput.get("contact") + "' , '" + userInput.get("address") + "', '" + userInput.get("email") + "', '" + userInput.get("password") + "');";
            }


            ResultSet rs = statement.executeQuery(selectQuery);
            if(rs.next()){
                String name = rs.getString("firstName") + " " + rs.getString("lastName");
                System.out.println("You are already registered with us under name " + name + ".");
            }
            else {
                statement.executeUpdate(insertQuery);
                System.out.println("Thank you for registering with us.");
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
