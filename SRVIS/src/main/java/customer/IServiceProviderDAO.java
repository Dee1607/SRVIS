package customer;

import java.util.Map;

public interface IServiceProviderDAO
{
    public Map<String, Map<String, String>> getServiceProvider(EnumServiceCategory enumServiceCategory);
}
