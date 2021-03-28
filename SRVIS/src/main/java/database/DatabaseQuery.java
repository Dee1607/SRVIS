package database;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DatabaseQuery {

    private Connection connect;
    private ResultSet rs;
    private Statement stmt ;
    Map<String,String> tableValues=null;
    private String tempKey;

    public DatabaseQuery(Connection con)
    {
        this.connect=con;
    }

    public Map<String, Map<String,String>> selectQuery(String query) throws Exception {

        Map<String, Map<String,String>> resultMap = new HashMap<String, Map<String,String>>();
        //stmt = connect.createStatement();
        PreparedStatement ps = connect.prepareStatement(query);
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
        stmt = connect.createStatement();
        int rsUpdate = stmt.executeUpdate(query);
    }

    public boolean insertQuery(String query, Map<String,String> insertData) throws Exception
    {
        PreparedStatement preparedStmt = connect.prepareStatement(query);

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
}
