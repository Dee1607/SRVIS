import java.sql.*;

public class ConnectionToDB {
    public Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_3_DEVINT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","CSCI5308_3_DEVINT_USER","LGAf8ynVwrSQUa3m");
        }catch(Exception e) {
            e.getMessage();
        }
        return  conn;
    }
}
