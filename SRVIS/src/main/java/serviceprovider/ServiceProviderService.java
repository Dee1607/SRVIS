package serviceprovider;

import java.util.Map;

public class ServiceProviderService implements IServiceProviderService
{

    ServiceProviderDAO d = new ServiceProviderDAO();
    public void updateAvailability(String Email)
    {
        d.updateServiceStatus(Email);
    }

    public void acceptBooking()
    {


    }

    public void rejectBooking()
    {


    }


    public Map<String , Map<String,String>>  showBooking()
    {
        Map<String , Map<String,String>> bookingResult = d.showAllBooking();
        return bookingResult;
    }
}