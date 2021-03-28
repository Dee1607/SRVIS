package serviceprovider;

import database.Database;

import java.util.Map;

public class ServiceProviderDAO {

    Database db= Database.databaseInstance();

    public void updateServiceStatus(String email) throws Exception {
        db.makeConnection();
        String sql1 = " UPDATE service_provider SET spAvailability ='Y' WHERE service_provider_id=1";
        db.updateQuery(sql1);
        db.closeConnection();
    }

    public Map<String,Map<String,String>> showAllBooking() throws Exception {
        db.makeConnection();
        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.service_request";
        Map<String,Map<String,String>> queryResult= db.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }

}
