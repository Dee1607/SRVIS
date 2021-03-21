package presantationlayer;

import database.ConnectionToDB;

import java.sql.*;
import java.util.Scanner;

public class LoginPage {

    public void login() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("############# Welcome to SRVIS #################### ");
        System.out.println("Enter your Username:");
        String userinput = sc.nextLine();
        System.out.println("Enter your password:");
        String passwordinput = sc.nextLine();

        ConnectionToDB dbObject = new ConnectionToDB();
        Connection conn = dbObject.getConnection();

        Statement stmt = conn.createStatement();
        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.login where username='" + userinput + "' and password='" + passwordinput + "'";
        ResultSet rs = stmt.executeQuery(sql1);
        if(rs.next())
        {
            String name = rs.getString("username");
            String passwordFromDb = rs.getString("password");
            if (name.equals(userinput) && passwordFromDb.equals(passwordinput)) {
                System.out.println("--------------------------------------------------------------");
                System.out.println("Hi : " + name);
                System.out.println("--------------------------------------------------------------");

            }else{
                System.out.println("Invalid username or password .");
            }
        }else {
            System.out.println("Please register first for using the app .");
        }
        conn.close();;
    }

}
