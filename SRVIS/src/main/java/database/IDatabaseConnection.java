package database;

import java.sql.Connection;
import java.util.Map;

public interface IDatabaseConnection
{
    public Connection makeConnection();
    public Map<String, Map<String,String>> selectQuery(String query);
    public void updateQuery(String query);
    public void closeConnection();
    public boolean insertQuery1(String query, Map<String,String> insertData);
    public boolean insertQuery(String query);
}
