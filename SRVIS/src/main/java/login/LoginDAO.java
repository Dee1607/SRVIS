package login;

import database.IDatabaseConnection;
import java.util.Map;

public class LoginDAO implements ILoginDAO {

    private final IDatabaseConnection db = IDatabaseConnection.databaseInstance();
    private final static String CUSTOMER = "c";
    private final static String SERVICE_PROVIDER = "sp";

    @Override
    public Map<String, Map<String,String>> applicationLogin(String email, String password, String type) {
        db.makeConnection();
        Map<String, Map<String,String>> queryResult = null;
        if (type.equalsIgnoreCase(CUSTOMER))
        {
            String customerQuery = String.format("SELECT * FROM customer WHERE email = '%s' AND password= '%s'", email, password);
            queryResult = db.selectQuery(customerQuery);
        } else if (type.equalsIgnoreCase(SERVICE_PROVIDER)){
            String serviceProviderQuery = String.format("SELECT * FROM service_provider WHERE email = '%s' AND password = '%s'", email, password);
            queryResult = db.selectQuery(serviceProviderQuery);
        }
        db.closeConnection();
        return queryResult;
    }

    @Override
    public Map<String, Map<String,String>> getAllCustomerRequests(String email, String type) {
        Map<String, Map<String, String>> pendingRequest = null;
        db.makeConnection();
        if (type.equalsIgnoreCase(CUSTOMER)) {
            String customerQuery = String.format("SELECT * FROM customer INNER JOIN service_request on customer.customer_id=service_request.customer_id WHERE email = '%s'", email);
            pendingRequest = db.selectQuery(customerQuery);
        } else if(type.equalsIgnoreCase(SERVICE_PROVIDER)){
            String serviceProviderQuery = String.format("SELECT * FROM service_provider INNER JOIN service_request on service_provider.service_provider_id=service_request.service_provider_id WHERE email = '%s'", email);
            pendingRequest = db.selectQuery(serviceProviderQuery);
        }
        db.closeConnection();
        return pendingRequest;
    }
}