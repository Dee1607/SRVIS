package serviceprovider;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ServiceProviderDAOTest {

    ServiceProviderDAO serviceDAO=null;

    public ServiceProviderDAOTest(){
     serviceDAO=new ServiceProviderDAO();
    }

    @Test
    void updateAvailabilityStatusTest() {
        boolean result=serviceDAO.updateAvailabilityStatus("bp@gmail.com");
        assertTrue(result);
        ServiceProviderDAO serviceDAO = new ServiceProviderDAO();
    }

    @Test
    void showAllBookingTest() {
        Map<String, Map<String,String>> result=serviceDAO.showAllBooking();
        assertFalse(result.isEmpty());
    }

    @Test
    void acceptBookingStatusTest() {
        boolean result=serviceDAO.acceptBookingStatus("111","222");
        assertTrue(result);
    }

    @Test
    void cancelBookingTest() {
        boolean result=serviceDAO.cancelBooking("111","222");
        assertTrue(result);
    }
}