package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private Connection conn = null;
    private String dbURL;
    private String dbUsername;
    private String dbPassword;
    private ResultSet rs;
    private Statement stmt ;
    Map<String,String> tableValues=null;
    private String tempKey;

    private static Database dbSingleton;

    private Database() {  }

    public static Database databaseInstance() {
        dbSingleton=new  Database();
        return dbSingleton;
    }

    public Connection makeConnection() throws SQLException {
        try {
            this.dbURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_3_DEVINT?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
            this.dbUsername = "CSCI5308_3_DEVINT_USER";
            this.dbPassword = "LGAf8ynVwrSQUa3m";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Map<String, Map<String,String>> selectQuery(String query) throws Exception {

        Map<String, Map<String,String>> resultMap = new HashMap<String, Map<String,String>>();
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        ResultSetMetaData rsMetadata = rs.getMetaData();
        int columnCount = rsMetadata.getColumnCount();
        tableValues=new HashMap<String,String>();
        if (rs.next()) {
            for (int i = 1; i <=columnCount; i++) {
                String columnNameValue = rsMetadata.getColumnName(i);
                if (i==1)
                {
                    tempKey=rs.getString(columnNameValue);
                }else{
                    tableValues.put(columnNameValue, rs.getString(columnNameValue));
                }
            }
            resultMap.put(tempKey, tableValues);
        }
        return resultMap;
    }


    public void updateQuery(String query) throws Exception {
        stmt = conn.createStatement();
        int rsUpdate = stmt.executeUpdate(query);
    }

    public boolean insertQuery(String query, Map<String,String> insertData) throws Exception
    {
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setInt (1, Integer.parseInt(insertData.get("customer_id")));
        preparedStmt.setInt (2, Integer.parseInt(insertData.get("service_provider_id")));
        preparedStmt.setDate(3, java.sql.Date.valueOf(insertData.get("service_request_date")));
        preparedStmt.setInt (4, Integer.parseInt(insertData.get("service_request_category_id")));
        preparedStmt.setString(5,insertData.get("service_request_description"));
        int insertStatus = preparedStmt.executeUpdate();

        if(insertStatus > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public void closeConnection() throws SQLException {
        conn.close();
    }

}