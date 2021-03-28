package serviceprovider;

import database.ConnectionToDB;
import database.DatabaseQuery;

import java.sql.SQLException;
import java.util.Map;

public class ServiceProviderDAO {


    public void updateServiceStatus(String email) throws Exception {
        ConnectionToDB db = new ConnectionToDB();
        DatabaseQuery q = new DatabaseQuery(db.getConnection());
        String sql1 = " UPDATE service_provider SET spAvailability ='Y' WHERE service_provider_id=1";
        q.updateQuery(sql1);
        db.closeConnection();
    }

    public Map<String,Map<String,String>> showAllBooking() throws Exception {
        ConnectionToDB db = new ConnectionToDB();
        DatabaseQuery q = new DatabaseQuery(db.getConnection());
        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.service_request";
        Map<String,Map<String,String>> queryResult= q.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }


}
