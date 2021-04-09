package login;

import java.util.HashMap;
import java.util.Map;

public class LoginDAOMock {
    public Map<String, Map<String, String>> applicationLoginTest() {
        Map<String, Map<String, String>> result =null;

        Map<String,String> credentials = new HashMap<>();
        credentials.put("email","th@gmail.com");
        credentials.put("password","123abc");
        credentials.put("type","c");
        //result.put("111",credentials);
        return result;
    }

    public Map<String, Map<String, String>> getAllCustomerRequestsTest(){
        Map<String, Map<String, String>> result =null;

        Map<String,String> credentials = new HashMap<>();
        credentials.put("email","bp@gmail.com");
        credentials.put("password","12345678");
        credentials.put("type","sp");
        result.put("222",credentials);
        return result;
    }
}