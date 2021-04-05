package bookingserviceprovider;

import database.DatabaseConnection;
import feedback.*;
import payment.*;
import presentationlayer.DisplayServiceProviderInfoUI;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.DisplayUpdates;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookServiceProvider implements IBookServiceProvider
{
    private Map<String,String> CUSTOMER_SESSION;

    public BookServiceProvider(Map<String, String> customer_session) {
        this.CUSTOMER_SESSION = customer_session;
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


            PaymentInfo objPayment = new PaymentInfo();
            objPayment.setPaymentType(PaymentType.valueOf("VISA"));
            objPayment.setCardNumber("1234567890");
            objPayment.setUserID("111");
            objPayment.setFullName("Johnny Appleseed");
            objPayment.setExpiryDate("12/12/12");
            objPayment.setSecurityCode("123");

            PaymentInfo objPayment1 = new PaymentInfo();
            objPayment1.setPaymentType(PaymentType.valueOf("VISA"));
            objPayment1.setCardNumber("1234567890");
            objPayment1.setUserID("222");
            objPayment1.setFullName("Johnny Appleseed");
            objPayment1.setExpiryDate("12/12/12");
            objPayment1.setSecurityCode("123");

            IPayment paymentTestObject = new Payment("ReadTestPaymentID");
            paymentTestObject.setSender(objPayment);
            paymentTestObject.setReceiver(objPayment1);
            paymentTestObject.setAmount("100");
            paymentTestObject.setStatus(PaymentStatus.PENDING);
            paymentTestObject.setDate("2021/12/12");
            paymentTestObject.setServiceRequestID("ServiceID");

            boolean paymentStatus = false;

            if(insertStatus)
            {
                objDisplayMessage.displayMessage("Ticket Has been Generated.");

                PaymentInfoDAO.write(objPayment);
                PaymentInfoDAO.write(objPayment1);
                paymentStatus =  PaymentDAO.write(paymentTestObject);
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