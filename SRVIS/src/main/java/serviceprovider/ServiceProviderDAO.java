package serviceprovider;

import database.DatabaseConnection;
import java.util.Map;

public class ServiceProviderDAO implements IServiceProviderDAO
{
    DatabaseConnection db= DatabaseConnection.databaseInstance();

    public void updateServiceStatus(String Email)
    {
        db.makeConnection();
        String sql1 = " UPDATE service_provider SET availability ='Y' WHERE email='"+Email+"'";
        db.updateQuery(sql1);
        db.closeConnection();
    }

    public Map<String,Map<String,String>> showAllBooking()
    {
        db.makeConnection();
        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.service_request";
        Map<String,Map<String,String>> queryResult= db.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }
}