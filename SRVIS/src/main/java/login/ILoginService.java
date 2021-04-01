package login;

import java.util.Map;

public interface ILoginService {

    public boolean loggingUser(String email, String password,String type) throws Exception;
    public Map<String, Map<String,String>>  getPendingRequests(String username);
}
