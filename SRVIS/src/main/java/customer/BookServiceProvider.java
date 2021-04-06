package customer;

import database.DatabaseConnection;
import feedback.*;
import payment.*;
import presentationlayer.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookServiceProvider implements IBookServiceProvider
{
    private Map<String,String> CUSTOMER_SESSION;
    private IPaymentInfo senderPaymentDetails = null;
    private PaymentUI paymentUI=null;
    private ServiceProviderCustomerUI serviceProvider=null;

    public BookServiceProvider(Map<String, String> customer_session) {
        this.CUSTOMER_SESSION = customer_session;
        senderPaymentDetails = new PaymentInfo();
        paymentUI=new PaymentUI();
    }

    public boolean finalizeServiceProvider(String serviceProviderID, Map<String,String> selectedServiceProvider)
    {
        DisplayServiceProviderInfoUI objDisplayServiceProvider;
        DisplayToGetUserChoice objGetUserChoice = null;
        Scanner sc = new Scanner(System.in);
        boolean isSelected = false;

        try
        {
            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objGetUserChoice = new DisplayToGetUserChoice();
            objDisplayServiceProvider.displayServiceProviderAllInfo(serviceProviderID, selectedServiceProvider);

            String choiceToSelect = objGetUserChoice.displayMessageGetStringChoiceFromUser("Do you want to select this service provider [Y/N]: ");

            if(choiceToSelect.equalsIgnoreCase("Y"))
            {
                getAdditionalDetailsToBookServiceProvider(selectedServiceProvider);
                isSelected = true;
            }
            return isSelected;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return isSelected;
        }
        finally
        {
            sc.close();
        }
    }

    public void getAdditionalDetailsToBookServiceProvider(Map<String,String> selectedServiceProvider)
    {

        DisplayToGetUserChoice objToDisplayData;

        objToDisplayData = new DisplayToGetUserChoice();
        String descriptionOfWork = objToDisplayData.displayMessageGetStringChoiceFromUser("Give some brief information on the work needs to be done: ");

        Calendar calendar = Calendar.getInstance();
        java.sql.Date bookingDate = new java.sql.Date(calendar.getTime().getTime());

        Map<String,String> insertData = new HashMap<>();

        insertData.put("customer_id",CUSTOMER_SESSION.get("customer_id"));
        insertData.put("service_provider_id",selectedServiceProvider.get("service_provider_id"));
        insertData.put("service_request_date",bookingDate.toString());
        insertData.put("service_request_category_id",selectedServiceProvider.get("service_category_id"));
        insertData.put("service_request_description",descriptionOfWork);

        generateBookingRequest(insertData);
    }

    public boolean generateBookingRequest(Map<String,String> dataToInsert)
    {
        DisplayUpdates objDisplayMessage;
        DatabaseConnection db= DatabaseConnection.databaseInstance();

        try
        {
            String query1= " insert into CSCI5308_3_DEVINT.service_request (customer_id, service_provider_id, service_request_date, service_request_category_id,service_request_description)"
                    + " values (?, ?, ?, ?, ?)";

            objDisplayMessage = new DisplayUpdates();
            db.makeConnection();
            boolean insertStatus = db.insertQuery(query1,dataToInsert);
            System.out.println("Please enter payment details : ");
            paymentUI.getPaymentSenderDetailsInput(senderPaymentDetails);
            boolean paymentStatus=serviceProvider.acceptPayment(senderPaymentDetails);
            if(insertStatus)
            {
                objDisplayMessage.displayMessage("Ticket has been generated.");
                PaymentInfoDAO.write(senderPaymentDetails);
            }

            boolean feedbackStatus = false;
            if(paymentStatus){
                IFeedback testFeedbackObject;
                IReview review = new Review();
                String reviewString = "This is a test review string.";
                review.setReviewString(reviewString);
                review.setDate("January 1, 2020");
                review.setReviewee("Reviewee");
                review.setAuthor("Author");
                testFeedbackObject = new Feedback("testID");
                testFeedbackObject.setRating("5");
                testFeedbackObject.setReview(review);

                feedbackStatus = FeedbackDAO.write(testFeedbackObject);
                if(feedbackStatus){
                    System.out.println("Thanks For feedback!");
                }
            }

            return insertStatus;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        } finally {
            try{
                db.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}