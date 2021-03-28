package serviceprovider;

import java.util.Map;

public class ServiceProviderService {

    ServiceProviderDAO d = new ServiceProviderDAO();
    public void updateAvailability(String Email) throws Exception {
        d.updateServiceStatus(Email);
    }


    public void acceptBooking(){


    }

    public void rejectBooking(){


    }


    public Map<String , Map<String,String>>  showBooking() throws Exception {
        Map<String , Map<String,String>> bookingResult=d.showAllBooking();
        return bookingResult;
    }
}
