package customer;

import database.DatabaseConnection;
import database.IDatabaseConnection;

import java.util.Map;

public class ServiceProviderDAO implements IServiceProviderDAO
{
    IDatabaseConnection db;
    public ServiceProviderDAO()
    {
        db = DatabaseConnection.databaseInstance();
    }

    public Map<String, Map<String, String>> getServiceProvider(EnumServiceCategory userChoice )
    {
        db.makeConnection();
        Map<String, Map<String, String>> mapOfDataFromDatabase = db.selectQuery("SELECT * FROM service_provider where jobType = '"
                + userChoice.toString() + "' AND availability = 'Y'");

        db.closeConnection();
        return mapOfDataFromDatabase;
    }
}