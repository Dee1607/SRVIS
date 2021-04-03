package serviceprovider;

import java.util.Map;

public class ServiceProviderService implements IServiceProviderService
{

    ServiceProviderDAO serviceProviderDAO = new ServiceProviderDAO();
    public void updateAvailability(String Email)
    {
        serviceProviderDAO.updateServiceStatus(Email);
    }

    public void acceptBooking()
    {


    }

    public void rejectBooking()
    {


    }


    public Map<String , Map<String,String>>  showBooking()
    {
        Map<String , Map<String,String>> bookingResult = serviceProviderDAO.showAllBooking();
        return bookingResult;
    }
}