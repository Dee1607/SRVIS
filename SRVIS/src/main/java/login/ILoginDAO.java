package login;

import java.util.Map;

public interface ILoginDAO
{
    public Map<String, Map<String,String>> AppLogin(String user, String password, String type);
}
