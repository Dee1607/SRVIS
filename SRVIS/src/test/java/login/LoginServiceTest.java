package login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private Map<String , Map<String,String>> result=new HashMap<>();

    @Test
    void getPendingRequestsTest() throws Exception {
        LoginService log=new LoginService();
        Map<String, Map<String,String>> result=log.getPendingRequests("bp@gmail.com");
        assertTrue(result.isEmpty());
    }
}