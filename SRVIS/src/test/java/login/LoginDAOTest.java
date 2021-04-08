package login;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginDAOTest {

    LoginDAO loginDAO = null;

    public LoginDAOTest() {

        loginDAO = new LoginDAO();
    }

    @Test
    void applicationLoginTest() {
        Map<String, Map<String, String>> result = loginDAO.applicationLogin("th@gmail.com", "123abc", "c");
        Map<String, String> tempValues;
        String name = null;
        for (String str : result.keySet()) {
            tempValues = result.get(str);
            name = tempValues.get("first_name");
        }
        assertEquals("Tom", name);
    }

    @Test
    void getAllCustomerRequestsTest() {
        Map<String, Map<String, String>> result = loginDAO.getAllCustomerRequests("bp@gmail.com", "sp");
        Map<String, String> tempValues;
        String name = null;

        for (String str : result.keySet()) {
            tempValues = result.get(str);
            String requestID = tempValues.get("service_request_id");
            assertEquals("20", requestID);
        }
    }

}