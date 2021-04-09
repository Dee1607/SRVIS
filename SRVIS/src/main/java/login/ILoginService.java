package login;

import java.util.Map;

public interface ILoginService
{
    Map<String, String> loginUser(String email, String password, String type);

    Map<String, String> getPendingRequests(String email, String type);
}
