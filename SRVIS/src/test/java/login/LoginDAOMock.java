package login;

import java.util.HashMap;
import java.util.Map;

public class LoginDAOMock {
    public Map<String, Map<String, String>> applicationLoginTest() {
        Map<String, Map<String, String>> result =null;

        Map<String,String> credentials = new HashMap<>();
        credentials.put("email","th@gmail.com");
        credentials.put("password","th@gmail.com");
        credentials.put("type","th@gmail.com");
        result.put("111",credentials);

        return result;
    }
}