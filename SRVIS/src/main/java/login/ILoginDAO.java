package login;

import java.util.Map;

public interface ILoginDAO
{
    public Map<String, Map<String,String>> applicationLogin(String user, String password, String type);
    public Map<String, Map<String,String>> getAllCustomerRequests(String user);
}
