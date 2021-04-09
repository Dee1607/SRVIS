package serviceprovider;

import java.util.Map;

public interface IServiceProviderService
{
    public boolean updateAvailability(String Email);

    public Map<String, Map<String, String>> showBooking();

    public boolean acceptBooking(String customerID, String serviceProviderID);

    public boolean rejectBooking(String customerID, String serviceProviderID);
}
