package customer;

import java.util.Map;

public interface IBookServiceProviderDAO
{
    public boolean setBookingRequest(Map<String,String> dataToInsert);
}