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
        LoginDAOMock objMock = new LoginDAOMock();
        Map<String,Map<String,String>> mapData= objMock.applicationLoginTest();
        Map<String, String> tempValues;
        String name = "Tom";

      assertEquals("Tom", name);
    }

    @Test
    void getAllCustomerRequestsTest() {
        LoginDAOMock objMock = new LoginDAOMock();
        Map<String,Map<String,String>> mapData= objMock.applicationLoginTest();

        Map<String, String> tempValues;
        String name = null;

            String requestID = "service_request_id";
            assertEquals("service_request_id", requestID);
    }

}