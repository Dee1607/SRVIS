package login;

import database.Database;

import java.util.Map;

public class LoginDAO {

    Database db= Database.databaseInstance();

    public Map<String, Map<String,String>> AppLogin(String user, String password, String type) throws Exception
    {
        db.makeConnection();
        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT."+type+" where Email='" + user + "' and Password='" + password + "'";
        Map<String,Map<String,String>> queryResult= db.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }


    public Map<String, Map<String,String>> getAllCustomerRequests(String user)
    {

        String q="select * from
        Map<String, Map<String,String>> pendingRequest=db.selectQuery()
        return pendingRequest;
    }
}