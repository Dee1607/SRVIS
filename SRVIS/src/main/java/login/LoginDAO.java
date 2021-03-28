package login;

import database.ConnectionToDB;
import database.DatabaseQuery;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDAO {

    ConnectionToDB db = new ConnectionToDB();

    public Map<String, Map<String,String>> AppLogin(String user, String password, String type) throws Exception {
        DatabaseQuery q=new DatabaseQuery(db.getConnection());
        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT."+type+" where Email='" + user + "' and Password='" + password + "'";
        Map<String,Map<String,String>> queryResult= q.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }
}