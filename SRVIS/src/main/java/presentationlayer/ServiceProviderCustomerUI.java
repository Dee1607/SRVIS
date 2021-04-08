package presentationlayer;

import CustomerDetails.AcceptedCustomer;
import payment.*;
import serviceprovider.ServiceProviderService;
import java.util.Map;
import java.util.Scanner;

public class ServiceProviderCustomerUI
{
    private Map<String,String> activeLoginServiceProvider;
    private ServiceProviderService serviceProvider;
    private PaymentUI paymentUI=null;
    private IDisplayToGetUserChoice display;
    private IPayment acceptPay=null;
    private IPaymentService paymentProcess=null;

    public ServiceProviderCustomerUI(Map<String,String> loginUser,IDisplayToGetUserChoice display )
    {
            this.activeLoginServiceProvider=loginUser;
            this.serviceProvider=new ServiceProviderService();
            this.paymentUI=new PaymentUI();
            this.display=display;
            this.acceptPay=new Payment();
    }

    public Map<String,String> getActiveServiceProvider()
    {
            String firstName= activeLoginServiceProvider.get("firstName");
            String lastName= activeLoginServiceProvider.get("lastName");
            String Email=activeLoginServiceProvider.get("email");
            display.displayMessage("Hi "+ firstName +  lastName);
            return activeLoginServiceProvider;
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
                System.out.println("Booking has been removed from your queue.!!");
            }
    }

    public boolean showAvailability(String Email)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you available for work (yes/no)?");
        String availabilityStatus = sc.nextLine();
        if(availabilityStatus.equals("yes"))
        {
            serviceProvider.updateAvailability(Email);
            System.out.println("Status : ACTIVE");
            return true;
        }
        else
        {
            System.out.println("Your availability has been marked as NO ");
            return false;
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


    public boolean acceptPayment(IPaymentInfo paySenderObject,String amount)
    {
        IPaymentInfo receiverPayment=new PaymentInfo();
        paymentUI.getPaymentDetailsInput(receiverPayment);
        acceptPay.setReceiver(receiverPayment);
        acceptPay.setSender(paySenderObject);
        acceptPay.setAmount(amount);
        boolean paymentStatus=paymentProcess.processPayment(acceptPay);
        return paymentStatus;
    }
}