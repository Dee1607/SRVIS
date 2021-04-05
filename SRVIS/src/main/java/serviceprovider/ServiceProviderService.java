package serviceprovider;

import java.util.Map;

public class ServiceProviderService implements IServiceProviderService
{

    ServiceProviderDAO serviceProviderDAO = new ServiceProviderDAO();

    public void updateAvailability(String Email)
    {
        serviceProviderDAO.updateAvailabilityStatus(Email);
    }


    public void rejectBooking(String customerID,String serviceProviderID)
    {

    }


    public Map<String , Map<String,String>>  showBooking()
    {
        Map<String , Map<String,String>> bookingResult = serviceProviderDAO.showAllBooking();
        return bookingResult;
    }

    @Override
    public void acceptBooking(String customerID, String serviceProviderID)
    {
        serviceProviderDAO.updateBookingStatus(customerID,serviceProviderID);
    }
}