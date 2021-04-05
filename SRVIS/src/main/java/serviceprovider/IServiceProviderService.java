package serviceprovider;

import java.util.Map;

public interface IServiceProviderService
{
    public void updateAvailability(String Email);
    public Map<String , Map<String,String>> showBooking();
    public void acceptBooking(String customerID,String serviceProviderID);
    public void rejectBooking(String customerID,String serviceProviderID);
}
