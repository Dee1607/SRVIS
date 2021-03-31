package serviceprovider;

import java.util.Map;

public interface IServiceProviderService
{
    public void updateAvailability(String Email);
    public void acceptBooking();
    public void rejectBooking();
    public Map<String , Map<String,String>> showBooking();
}
