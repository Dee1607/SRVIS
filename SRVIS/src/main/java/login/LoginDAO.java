package login;

import database.IDatabaseConnection;
import java.util.Map;

public class LoginDAO implements ILoginDAO
{
    private final IDatabaseConnection db = IDatabaseConnection.databaseInstance();
    private final static String CUSTOMER = "c";
    private final static String SERVICE_PROVIDER = "sp";

    @Override
    public Map<String, Map<String,String>> applicationLogin(String email, String password, String type)
    {
        db.makeConnection();
        Map<String, Map<String,String>> queryResult = null;
        if (type.equalsIgnoreCase(CUSTOMER))
        {
            String customerQuery = String.format("SELECT * FROM customer WHERE email = '%s' AND password= '%s'", email, password);
            queryResult = db.selectQuery(customerQuery);
        }
        else if (type.equalsIgnoreCase(SERVICE_PROVIDER))
        {
            String serviceProviderQuery = String.format("SELECT * FROM service_provider WHERE email = '%s' AND password = '%s'", email, password);
            queryResult = db.selectQuery(serviceProviderQuery);
        }
        db.closeConnection();
        return queryResult;
    }

    @Override
    public Map<String, Map<String,String>> getAllCustomerRequests(String email, String type)
    {
        Map<String, Map<String, String>> pendingRequest = null;
        db.makeConnection();
        if (type.equalsIgnoreCase(CUSTOMER))
        {
            String customerQuery = "select * from service_request as rs where ( select customer_id from customer as c where c.email = '" + email + "' ) AND rs.request_acceptance_status = 'PENDING'";
            pendingRequest = db.selectQuery(customerQuery);
        }
        else if(type.equalsIgnoreCase(SERVICE_PROVIDER))
        {
            String serviceProviderQuery = "select * from service_request as rs where ( select service_provider_id from service_provider as sp where sp.email = '" + email + "' ) AND rs.request_acceptance_status = 'ACCEPT'";
            pendingRequest = db.selectQuery(serviceProviderQuery);
        }
        db.closeConnection();
        return pendingRequest;
    }
}