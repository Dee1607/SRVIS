package serviceprovider;

import java.util.Map;

public interface IServiceProviderDAO
{
    public void updateServiceStatus(String email);
    public Map<String, Map<String,String>> showAllBooking();
}
