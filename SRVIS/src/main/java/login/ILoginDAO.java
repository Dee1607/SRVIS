package login;

import java.util.Map;

public interface ILoginDAO
{
    Map<String, Map<String, String>> applicationLogin(String email, String password, String type);

    Map<String, Map<String, String>> getAllCustomerRequests(String email, String type);
}
