package serviceprovider;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ServiceProviderServiceTest {

    private ServiceProviderService service;

    public ServiceProviderServiceTest(){

        service =new ServiceProviderService();
    }

    @Test
    void updateAvailabilityTest() {
        boolean result= service.updateAvailability("bp@gmail.com");
        assertTrue(result);
    }

    @Test
    void rejectBookingTest() {
        boolean result=service.rejectBooking("111","222");
        assertTrue(result);
    }

    @Test
    void showBookingTest() {
        Map<String, Map<String, String>> bookingResult=service.showBooking();
        assertFalse(bookingResult.isEmpty());
    }

    @Test
    void acceptBookingTest() {
            boolean result=service.acceptBooking("111","222");
            assertTrue(result);
    }
}