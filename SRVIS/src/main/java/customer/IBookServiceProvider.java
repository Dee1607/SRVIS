package customer;

import java.util.Map;

public interface IBookServiceProvider
{
    public boolean finalizeServiceProvider(String serviceProviderID, Map<String, String> selectedServiceProvider);

    public Map<String, String> getAdditionalDetailsToBookServiceProvider(Map<String, String> selectedServiceProvider);

    public boolean generateBookingRequest(Map<String, String> dataToInsert);
}
