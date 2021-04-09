package customer;

import java.util.Map;

public interface ISelectServiceProvider
{
    public Map<String, Map<String, String>> getServiceProvidersOfSelectedCategory(EnumServiceCategory userChoice);
    public int selectFromAvailableServiceProvider(Map<String, Map<String, String>> mapOfDataFromDatabase);
}
