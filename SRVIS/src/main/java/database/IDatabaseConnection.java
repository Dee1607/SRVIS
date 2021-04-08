package database;

import java.sql.Connection;
import java.util.Map;

public interface IDatabaseConnection {
    static DatabaseConnection databaseInstance() {
        return new DatabaseConnection();
    }

    Connection makeConnection();

    Map<String, Map<String, String>> selectQuery(String query);

    boolean updateQuery(String query);

    boolean insertQuery(String query, Map<String, String> insertData);

    void closeConnection();

    boolean insertQuery1(String query, Map<String, String> insertData);

    boolean insertQuery(String query);
}
