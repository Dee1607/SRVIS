package presentationlayer;
import serviceprovider.ServiceProviderService;
import java.util.Map;
import java.util.Scanner;

public class ServiceProviderCustomerUI
{
    private String firstName;
    private String lastName;
    private String Email;

    Map<String, Map<String,String>> activeLoginServiceProvider;
    ServiceProviderService serviceProvider=new ServiceProviderService();
    Map<String,String> bookingValues =null;

    public ServiceProviderCustomerUI(Map<String, Map<String,String>> loginUser)
    {
            this.activeLoginServiceProvider=loginUser;

        for(String str : activeLoginServiceProvider.keySet())
        {
            Map<String,String> tempValues = activeLoginServiceProvider.get(str);
            firstName= tempValues.get("firstName");
            lastName= tempValues.get("lastName");
            Email= tempValues.get("email");
        }
    }

    public void showCustomerRequestUI()
    {
            System.out.println("Hi "+ firstName +  lastName);
            showAvailability(Email);
            getJobRequests();
            bookingOperation();
    }

    public void bookingOperation()
    {
        System.out.println("Please follow below options :");
            System.out.println("1 : Accept");
            System.out.println("2: Reject");
            Scanner sc = new Scanner(System.in);
            if(sc.nextLine().equals(1))
            {
                System.out.println("Please enter the customer id :");

            }
            else
            {
                System.out.println("Booking for ");
            }
        }

    public void showAvailability(String Email)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you available for work (yes/no)?");
        String availabilityStatus = sc.nextLine();
        if(availabilityStatus.equals("yes"))
        {
            serviceProvider.updateAvailability(Email);
            System.out.println("Status : ACTIVE");
        }
        else
        {
            System.out.println("Your availability has been marked as no");
        }
    }

    public Map<String , String> getJobRequests()
    {
        Map<String , Map<String,String>> viewBooking=serviceProvider.showBooking();
        for(String spID : viewBooking.keySet())
        {
            bookingValues = viewBooking.get(spID);
            System.out.println("--------------------------------------------------------------");
            System.out.println("customer_id: " +bookingValues.get("customer_id"));
            System.out.println("service_request_date: "+bookingValues.get("service_request_date"));
            System.out.println("service_request_description : " +bookingValues.get("service_request_description"));
            System.out.println("--------------------------------------------------------------");
        }
        return bookingValues;
    }
}