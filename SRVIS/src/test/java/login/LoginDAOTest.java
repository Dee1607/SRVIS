package login;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginDAOTest {

    @Test
    void appLoginTest() {
        LoginDAO loginDAO=new LoginDAO();
        try {
            Map<String, Map<String,String>> result=loginDAO.applicationLogin("bp@gmail.com","12345678","SP");
            assertFalse(result.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllCustomerRequestsTest() {
        LoginDAO loginDAO=new LoginDAO();
        Map<String, Map<String,String>> result= null;
        try {
            result = loginDAO.getAllCustomerRequests("th@gmail.com","C");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertFalse(result.isEmpty());

    }
}