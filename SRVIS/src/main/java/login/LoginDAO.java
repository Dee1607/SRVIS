package login;

import database.Database;

import java.sql.SQLException;
import java.util.Map;

public class LoginDAO {

    Database db= Database.databaseInstance();

    public Map<String, Map<String,String>> AppLogin(String email, String password,String type) throws Exception
    {
        db.makeConnection();
        String sql1 = "SELECT * FROM " +type+" WHERE Email='"+email+"' AND password="+password+"";
        Map<String,Map<String,String>> queryResult= db.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }


    public Map<String, Map<String,String>> getAllCustomerRequests(String user) throws Exception {

        db.makeConnection();
        String query="select * from customer left join  service_request on customer.customer_id=service_request.customer_id where Email='"+user+"';";
        Map<String, Map<String,String>> pendingRequest= null;
        try {
            pendingRequest = db.selectQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.closeConnection();
        return pendingRequest;
    }
}