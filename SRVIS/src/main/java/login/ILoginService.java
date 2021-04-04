package login;

import java.util.Map;

public interface ILoginService
{
    public void loginUser(String user, String password, String type);
    public Map<String, Map<String,String>>  getPendingRequests(String username);
}