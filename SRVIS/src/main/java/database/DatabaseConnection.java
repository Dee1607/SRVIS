package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection {

    private final Properties defaultProperties;
    private Connection conn = null;

    public DatabaseConnection() {
        defaultProperties = new Properties();
    }

    public static DatabaseConnection databaseInstance() {
        return new DatabaseConnection();
    }

    public Connection makeConnection() {
        loadProperties();
        try {
            String dbURL = defaultProperties.getProperty("dbURL");
            String dbUsername = defaultProperties.getProperty("dbUsername");
            String dbPassword = defaultProperties.getProperty("dbPassword");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void loadProperties() {
        try {
            InputStream in = new FileInputStream("./src/main/resources/config.properties");
            defaultProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, String>> selectQuery(String query) {
        Map<String, Map<String, String>> resultMap = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMetadata = rs.getMetaData();
            int columnCount = rsMetadata.getColumnCount();
            Map<String, String> tableValues = new HashMap<>();
            String tempKey = null;
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnNameValue = rsMetadata.getColumnName(i);
                    if (i == 1) {
                        tempKey = rs.getString(columnNameValue);
                    }
                    tableValues.put(columnNameValue, rs.getString(columnNameValue));
                }
                resultMap = new HashMap<>();
                resultMap.put(tempKey, tableValues);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public boolean updateQuery(String query) {
        try {
            Statement stmt = conn.createStatement();
            int rowCount = stmt.executeUpdate(query);
            stmt.close();
            return rowCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertRegQuery(String query, Map<String, String> insertData) {
        PreparedStatement preparedStmt;

        try {
            int insertStatus;

            if (insertData.size() > 7) {
                preparedStmt = conn.prepareStatement(query);
            } else {
                preparedStmt = conn.prepareStatement(query);
                int count = 1;
                for (String str : insertData.keySet()) {
                    preparedStmt.setString(count, insertData.get(str));
                    count++;
                }
            }
            insertStatus = preparedStmt.executeUpdate();

            preparedStmt.close();
            return insertStatus > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertQuery(String query) {
        boolean result = false;
        try {
            Statement stmt = conn.createStatement();
            if (stmt.executeUpdate(query) == 1) {
                result = true;
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertQuery(String query, Map<String, String> insertData) {
        PreparedStatement preparedStmt;
        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, Integer.parseInt(insertData.get("customer_id")));
            preparedStmt.setInt (2, Integer.parseInt(insertData.get("service_provider_id")));
            preparedStmt.setDate(3, java.sql.Date.valueOf(insertData.get("service_request_date")));
            preparedStmt.setInt (4, Integer.parseInt(insertData.get("service_request_category_id")));
            preparedStmt.setString(5,insertData.get("service_request_description"));

            int insertStatus = preparedStmt.executeUpdate();
            preparedStmt.close();
            return insertStatus > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception ignore) {
        }
    }
}