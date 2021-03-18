import java.sql.*;

public class Database {
    public void dbConnection() throws SQLException {
        Connection con = DriverManager.getConnection("db-5308.cs.dal.ca","CSCI5308_3_DEVINT_USER","LGAf8ynVwrSQUa3m");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from customers where customer_unique_id = '861eff4711a542e4b93843c6dd7febb0';");
        while(rs.next())
            System.out.println(rs.getInt("customer_city"));
        con.close();
    }
}
