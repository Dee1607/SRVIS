package login;

import database.DatabaseConnection;
import database.IDatabaseConnection;

import java.util.Map;

public class LoginDAO implements ILoginDAO{


    IDatabaseConnection db = DatabaseConnection.databaseInstance();

    public Map<String, Map<String,String>> applicationLogin(String email, String password,String type)
    {
        String sql1=null;
        db.makeConnection();
        if(type.equalsIgnoreCase("c"))
        {
            sql1 = "SELECT * FROM customer WHERE email='"+email+"'AND password="+password+"";
        }else{
            sql1 = "SELECT * FROM service_provider WHERE email='"+email+"'AND password='"+password+"'";
        }
        Map<String,Map<String,String>> queryResult= db.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }

    public Map<String, Map<String,String>> getAllCustomerRequests(String email, String type)
    {
        String query =null;
        db.makeConnection();
        if(type.equalsIgnoreCase("c")) {
            query = "SELECT * FROM customer INNER JOIN service_request on customer.customer_id=service_request.customer_id WHERE email='" + email + "'";
        }else if(type.equalsIgnoreCase("sp")){
            query = "SELECT * FROM service_provider INNER JOIN service_request on service_provider.service_provider_id=service_request.service_provider_id WHERE email='" + email + "'";
        }
        Map<String, Map<String, String>> pendingRequest = null;
        pendingRequest = db.selectQuery(query);
        db.closeConnection();
        return pendingRequest;
    }
}