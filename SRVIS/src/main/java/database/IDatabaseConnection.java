package database;

import java.sql.Connection;
import java.util.Map;

public interface IDatabaseConnection {
    static DatabaseConnection databaseInstance()
    {
        return new DatabaseConnection();
    }

    Connection makeConnection();

    Map<String, Map<String, String>> selectQuery(String query);

    boolean updateQuery(String query);

    void closeConnection();

    boolean insertQuery(String query);
}
