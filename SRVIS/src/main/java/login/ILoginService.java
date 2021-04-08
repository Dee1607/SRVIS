package login;

import java.util.Map;

public interface ILoginService
{
    public Map<String,String> loginUser(String user, String password,String type);
    public Map<String,String>  getPendingRequests(String email,String type);
}