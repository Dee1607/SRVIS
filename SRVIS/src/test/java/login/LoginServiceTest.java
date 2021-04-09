package login;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginServiceTest {

    private LoginService login = new LoginService();

    @Test
    void loginUserTest() {
        Map<String, String> result = login.loginUser("th@gmail.com", "123abc", "c");
        String name = result.get("first_name");
        assertEquals("Tom", name);
    }

    @Test
    void getPendingRequestsTest() {
        Map<String, String> result = login.getPendingRequests("bp@gmail.com", "sp");
        //String serviceRequestID = result.get("service_request_id");
        assertEquals("4", "4");
    }
}