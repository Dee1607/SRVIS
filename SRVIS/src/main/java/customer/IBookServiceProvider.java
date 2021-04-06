package customer;

import java.util.Map;

public interface IBookServiceProvider
{
    public boolean finalizeServiceProvider(String serviceProviderID, Map<String,String> selectedServiceProvider);
    public void getAdditionalDetailsToBookServiceProvider(Map<String,String> selectedServiceProvider);
    public boolean generateBookingRequest(Map<String,String> dataToInsert);
}
