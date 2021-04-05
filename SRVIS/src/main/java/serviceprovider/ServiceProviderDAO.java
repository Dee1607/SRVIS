package serviceprovider;

import database.DatabaseConnection;
import java.util.Map;

public class ServiceProviderDAO implements IServiceProviderDAO
{
    DatabaseConnection db= DatabaseConnection.databaseInstance();

    public void updateAvailabilityStatus(String Email)
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

    public void updateBookingStatus(String customerID, String serviceProviderID)
    {
        db.makeConnection();
        String bookingStatusUpdate = " UPDATE service_request SET request_acceptance_status ='Y' WHERE customer_id='"+customerID+"' AND service_provider_id='"+serviceProviderID+"'";
        db.updateQuery(bookingStatusUpdate);
        db.closeConnection();
    }
}