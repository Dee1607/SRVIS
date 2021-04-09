package serviceprovider;

import database.DatabaseConnection;
import java.util.Map;

public class ServiceProviderDAO implements IServiceProviderDAO
{
    DatabaseConnection db = DatabaseConnection.databaseInstance();

    public boolean updateAvailabilityStatus(String Email)
    {
        db.makeConnection();
        String sql1 = " UPDATE service_provider SET availability ='Y' WHERE email='" + Email + "'";
        boolean updateStatus = db.updateQuery(sql1);
        db.closeConnection();
        return updateStatus;
    }

    public Map<String, Map<String, String>> showAllBooking()
    {
        db.makeConnection();
        String sql1 = "SELECT * FROM service_request";
        Map<String,Map<String,String>> queryResult= db.selectQuery(sql1);
        db.closeConnection();
        return queryResult;
    }

    public boolean acceptBookingStatus(String customerID, String serviceProviderID)
    {
        db.makeConnection();
        String bookingStatusUpdate = " UPDATE service_request SET request_acceptance_status ='ACCEPT' WHERE customer_id='" + customerID + "' AND service_provider_id='" + serviceProviderID + "'";
        boolean updateStatus = db.updateQuery(bookingStatusUpdate);
        db.closeConnection();
        return updateStatus;
    }

    public boolean cancelBooking(String customerID, String serviceProviderID)
    {
        db.makeConnection();
        String bookingStatusUpdate = " UPDATE service_request SET request_acceptance_status ='REJECT' WHERE customer_id='" + customerID + "' AND service_provider_id='" + serviceProviderID + "'";
        boolean cancelStatus = db.updateQuery(bookingStatusUpdate);
        db.closeConnection();
        return cancelStatus;
    }
}