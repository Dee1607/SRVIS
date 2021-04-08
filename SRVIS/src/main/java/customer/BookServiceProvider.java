package customer;

import database.DatabaseConnection;
import feedback.*;
import payment.IPaymentInfo;
import payment.PaymentInfo;
import presentationlayer.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookServiceProvider implements IBookServiceProvider {
    private Map<String, String> CUSTOMER_SESSION;
    private IPaymentInfo senderPaymentDetails = null;
    private DisplayPaymentUI paymentUI = null;
    private DisplayServiceProviderUI serviceProvider = null;
    private DisplayFeedbackUI feedbackUI = null;
    private FeedbackDAO feedbackDAO = null;
    private IFeedback customerReview = null;
    private IReview review = null;

    public BookServiceProvider(Map<String, String> customer_session) {
        this.CUSTOMER_SESSION = customer_session;
        this.senderPaymentDetails = new PaymentInfo();
        this.paymentUI = new DisplayPaymentUI();
        this.feedbackUI = new DisplayFeedbackUI();
        this.customerReview = new Feedback("1");
        this.review = new Review();
        this.feedbackDAO = new FeedbackDAO();
    }

    public boolean finalizeServiceProvider(String serviceProviderID, Map<String, String> selectedServiceProvider) {
        DisplayServiceProviderInfoUI objDisplayServiceProvider;
        DisplayToGetUserChoice objGetUserChoice = null;
        boolean isSelected = false;

        try {
            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objGetUserChoice = new DisplayToGetUserChoice();
            objDisplayServiceProvider.displayServiceProviderAllInfo(serviceProviderID, selectedServiceProvider);

            String choiceToSelect = objGetUserChoice.displayMessageGetStringChoiceFromUser("Do you want to select this service provider [Y/N]: ");

            if (choiceToSelect.equalsIgnoreCase("Y")) {
                getAdditionalDetailsToBookServiceProvider(selectedServiceProvider);
                isSelected = true;
            }
            return isSelected;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isSelected;
        }
    }

    public Map<String, String> getAdditionalDetailsToBookServiceProvider(Map<String, String> selectedServiceProvider) {

        DisplayToGetUserChoice objToDisplayData = null;

        objToDisplayData = new DisplayToGetUserChoice();
        String descriptionOfWork = objToDisplayData.displayMessageGetStringChoiceFromUser("Give some brief information on the work needs to be done:");

        Calendar calendar = Calendar.getInstance();
        java.sql.Date bookingDate = new java.sql.Date(calendar.getTime().getTime());

        Map<String, String> insertData = new HashMap<>();

        insertData.put("customer_id", CUSTOMER_SESSION.get("customer_id"));
        insertData.put("service_provider_id", selectedServiceProvider.get("service_provider_id"));
        insertData.put("service_request_date", bookingDate.toString());
        insertData.put("service_request_category_id", selectedServiceProvider.get("service_category_id"));
        insertData.put("service_request_description", descriptionOfWork);

        return insertData;
    }

    public boolean generateBookingRequest(Map<String, String> dataToInsert) {
        IDisplayUpdates objDisplayMessage;
        DatabaseConnection db = DatabaseConnection.databaseInstance();

        try {
            String query1 = " insert into CSCI5308_3_DEVINT.service_request (customer_id, service_provider_id, service_request_date, service_request_category_id,service_request_description)"
                    + " values (?, ?, ?, ?, ?)";

            objDisplayMessage = new DisplayUpdates();
            db.makeConnection();
            boolean insertStatus = db.insertQuery(query1, dataToInsert);

            //return insertStatus;

            System.out.println("Please enter payment details : ");
            paymentUI.getPaymentDetailsInput(senderPaymentDetails);
            boolean paymentStatus = serviceProvider.acceptPayment(senderPaymentDetails, "100");
            if (insertStatus) {
                objDisplayMessage.displayMessage("Ticket has been generated.");
            }

            boolean feedbackStatus = false;
            if (paymentStatus) {

                feedbackUI.getReviewDetailsInput(review);
                feedbackUI.setFeedback(customerReview);
                customerReview.setReview(review);
                feedbackStatus = feedbackDAO.write(customerReview);
                if (feedbackStatus) {
                    System.out.println("Thanks For feedback!");
                }
            }

            return insertStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                db.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}