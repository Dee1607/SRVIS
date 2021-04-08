package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection
{

    private Connection conn = null;
    private String dbURL=null;
    private String dbUsername=null;
    private String dbPassword=null;
    private Statement stmt;
    private String tempKey;

    private static DatabaseConnection dbSingleton;

    public static DatabaseConnection databaseInstance()
    {
        dbSingleton = new DatabaseConnection();
        return dbSingleton;
    }

        Properties prop = new Properties();
        InputStream input;

    {
        try {
            input = new FileInputStream("./src/main/resources/config.properties");
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection makeConnection()
    {
        try {
            this.dbURL =prop.getProperty("dbURL");
            this.dbUsername = prop.getProperty("dbUsername");
            this.dbPassword = prop.getProperty("dbPassword");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Map<String, Map<String, String>> selectQuery(String query) {
        Map<String, String> tableValues;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            ResultSetMetaData rsMetadata = rs.getMetaData();
            int columnCount = rsMetadata.getColumnCount();
            tableValues = new HashMap<String, String>();
            while(rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnNameValue = rsMetadata.getColumnName(i);
                    if (i == 1) {
                        tempKey = rs.getString(columnNameValue);
                    }
                    tableValues.put(columnNameValue, rs.getString(columnNameValue));
                }
                resultMap.put(tempKey, tableValues);
            }

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateQuery(String query) {
        try {
            stmt = conn.createStatement();
            int rowCount = stmt.executeUpdate(query);
            if (rowCount > 0) {
                return true;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertQuery1(String query, Map<String, String> insertData) {
        PreparedStatement preparedStmt = null;

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


//            preparedStmt.setInt (2, Integer.parseInt(insertData.get("service_provider_id")));
//            preparedStmt.setDate(3, java.sql.Date.valueOf(insertData.get("service_request_date")));
//            preparedStmt.setInt (4, Integer.parseInt(insertData.get("service_request_category_id")));
//            preparedStmt.setString(5,insertData.get("service_request_description"));


            if (insertStatus > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStmt.close();
                closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, Integer.parseInt(insertData.get("customer_id")));
            preparedStmt.setInt (2, Integer.parseInt(insertData.get("service_provider_id")));
            preparedStmt.setDate(3, java.sql.Date.valueOf(insertData.get("service_request_date")));
            preparedStmt.setInt (4, Integer.parseInt(insertData.get("service_request_category_id")));
            preparedStmt.setString(5,insertData.get("service_request_description"));

            int insertStatus = preparedStmt.executeUpdate();
            if (insertStatus > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStmt.close();
                closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}