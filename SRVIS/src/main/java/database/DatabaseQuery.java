package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseQuery {

    private Connection connect;
    private ResultSet rs;
    private Statement stmt ;
    Map<String,String> tableValues=null;
    private String tempKey;

    public DatabaseQuery(Connection con){
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
}
