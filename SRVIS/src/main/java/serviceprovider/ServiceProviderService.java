package serviceprovider;

import java.util.Map;

public class ServiceProviderService implements IServiceProviderService
{

    private ServiceProviderDAO serviceProviderDAO = null;
    public ServiceProviderService()
    {
        serviceProviderDAO = new ServiceProviderDAO();
    }

    public boolean updateAvailability(String Email)
    {
        boolean availabilityStatus = serviceProviderDAO.updateAvailabilityStatus(Email);
        return availabilityStatus;
    }

    public boolean rejectBooking(String customerID, String serviceProviderID)
    {
        boolean rejectionStatus = serviceProviderDAO.cancelBooking(customerID, serviceProviderID);
        return rejectionStatus;
    }

    public Map<String, Map<String, String>> showBooking()
    {
        Map<String, Map<String, String>> bookingResult = serviceProviderDAO.showAllBooking();
        return bookingResult;
    }

    public boolean acceptBooking(String customerID, String serviceProviderID)
    {
        boolean acceptanceStatus = serviceProviderDAO.acceptBookingStatus(customerID, serviceProviderID);
        return acceptanceStatus;
    }
}