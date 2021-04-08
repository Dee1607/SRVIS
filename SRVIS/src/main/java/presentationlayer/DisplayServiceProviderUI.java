package presentationlayer;

import customer.GenerateDataToDisplay;
import customer.AcceptedCustomer;
import payment.*;
import serviceprovider.ServiceProviderService;
import java.util.Map;
import java.util.Scanner;

public class DisplayServiceProviderUI
{
    private Map<String,String> activeLoginServiceProvider;
    private ServiceProviderService serviceProvider;
    private DisplayPaymentUI paymentUI=null;
    private IDisplayToGetUserChoice display;
    private IPayment acceptPay=null;
    private IPaymentService paymentProcess=null;
    private DisplayServiceCategoriesUI objDisplay=null;
    private GenerateDataToDisplay objectDataToDisplay=null;

    public DisplayServiceProviderUI(Map<String,String> loginUser, IDisplayToGetUserChoice display )
    {
            this.activeLoginServiceProvider=loginUser;
            this.serviceProvider=new ServiceProviderService();
            this.paymentUI=new DisplayPaymentUI();
            this.display=display;
            this.acceptPay=new Payment();
            this.objDisplay = new DisplayServiceCategoriesUI();
            this.objectDataToDisplay = new GenerateDataToDisplay();
    }

    public Map<String,String> getActiveServiceProvider()
    {
            String firstName= activeLoginServiceProvider.get("firstName");
            String lastName= activeLoginServiceProvider.get("lastName");
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
        boolean availability = false;
        if(availabilityStatus.equals("yes"))
        {
            availability=serviceProvider.updateAvailability(Email);
            System.out.println("Status : ACTIVE");
        }
        else
        {
            System.out.println("Your availability has been marked as NO ");
        }
        return availability;
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