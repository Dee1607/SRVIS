package presentationlayer;
import CustomerDetails.AcceptedCustomer;
import payment.IPaymentInfo;
import payment.PaymentInfo;
import payment.PaymentInfoDAO;
import serviceprovider.ServiceProviderService;
import java.util.Map;
import java.util.Scanner;

public class ServiceProviderCustomerUI
{
    private Map<String,String> activeLoginServiceProvider;
    private ServiceProviderService serviceProvider;
    private PaymentUI paymentUI=null;
    private IPaymentInfo receiverPayment=null;

    public ServiceProviderCustomerUI(Map<String,String> loginUser)
    {
            this.activeLoginServiceProvider=loginUser;
            serviceProvider=new ServiceProviderService();
            paymentUI=new PaymentUI();
            receiverPayment=new PaymentInfo();
    }

    public void showCustomerRequestUI()
    {
            String firstName= activeLoginServiceProvider.get("firstName");
            String lastName= activeLoginServiceProvider.get("lastName");
            String Email=activeLoginServiceProvider.get("email");
            System.out.println("Hi "+ firstName +  lastName);
            showAvailability(Email);
            getJobRequests();
            bookingOperation(activeLoginServiceProvider);

    }

    public void bookingOperation(Map<String,String> serviceProviderDetails)
    {
            String serviceProviderID=serviceProviderDetails.get("service_provider_id");
            String customerID = null;
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the customer id for selecting request:");
            customerID=sc.nextLine();
            System.out.println("Please follow below options :");
            System.out.println("1: Accept");
            System.out.println("2: Reject");
            String input=sc.nextLine();
            if(input.equals("1"))
            {
                serviceProvider.acceptBooking(customerID,serviceProviderID);
                System.out.println("Booking for " + customerID + " has been assigned");
                System.out.println("==========Customer Details==========");
                AcceptedCustomer customerData = new AcceptedCustomer(customerID);
                customerData.customerDetails();
            }
            else if(input.equals("2"))
            {
                serviceProvider.rejectBooking(customerID,serviceProviderID);
                System.out.println("Please enter valid customer ID");
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
            System.out.println("Your availability has been marked as NO ");
        }
    }

    public Map<String , String> getJobRequests()
    {
        Map<String , Map<String,String>> viewBooking=serviceProvider.showBooking();
        Map<String,String> bookingValues = null;
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


    public boolean acceptPayment(IPaymentInfo senderPaymentDetails)
    {
        paymentUI.getPaymentReceiverDetailsInput(receiverPayment);
        // PaymentInfoDAO.write(receiverPayment);
        paymentUI.addPaymentProcessInput(senderPaymentDetails,receiverPayment);
        return true;
    }
}