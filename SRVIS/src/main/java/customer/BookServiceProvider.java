package customer;

import database.DatabaseConnection;
import feedback.*;
import payment.IPaymentInfo;
import payment.IPaymentService;
import payment.PaymentInfo;
import payment.PaymentService;
import presentationlayer.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookServiceProvider implements IBookServiceProvider
{
    private Map<String, String> CUSTOMER_SESSION;
    private IPaymentInfo senderPaymentDetails = null;
    private DisplayPaymentUI displayPaymentUI = null;
    private DisplayServiceProviderUI serviceProvider = null;
    private DisplayFeedbackUI feedbackUI = null;
    private FeedbackDAO feedbackDAO = null;
    private IFeedback customerReview = null;
    private IReview review = null;

    public BookServiceProvider(Map<String, String> customer_session,IDisplayToGetUserChoice display)
    {
        this.CUSTOMER_SESSION = customer_session;
        this.senderPaymentDetails = new PaymentInfo();
        this.displayPaymentUI = new DisplayPaymentUI();
        this.feedbackUI = new DisplayFeedbackUI();
        this.customerReview = new Feedback("1");
        this.review = new Review();
        this.feedbackDAO = new FeedbackDAO();
        this.serviceProvider=new DisplayServiceProviderUI(customer_session,display);
    }

    public boolean finalizeServiceProvider(String serviceProviderID, Map<String, String> selectedServiceProvider)
    {
        DisplayServiceProviderInfoUI objDisplayServiceProvider;
        DisplayToGetUserChoice objGetUserChoice = null;
        boolean isSelected = false;

        try
        {
            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objGetUserChoice = new DisplayToGetUserChoice();
            objDisplayServiceProvider.displayServiceProviderAllInfo(serviceProviderID, selectedServiceProvider);

            String choiceToSelect = objGetUserChoice.displayMessageGetStringChoiceFromUser("Do you want to select this service provider [Y/N]: ");

            if(choiceToSelect.equalsIgnoreCase("y"))
            {
                isSelected = true;
            }
            return isSelected;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            return isSelected;
        }
    }

    public Map<String, String> getAdditionalDetailsToBookServiceProvider(Map<String, String> selectedServiceProvider)
    {

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

    public boolean generateBookingRequest(Map<String,String> dataToInsert)
    {
        DisplayToGetUserChoice objDisplayMessage = new DisplayToGetUserChoice();
        DatabaseConnection db = DatabaseConnection.databaseInstance();
        IBookServiceProviderDAO objBookServiceProvider = null;
        objBookServiceProvider = new BookServiceProviderDAO();
        try
        {
            boolean insertStatus = objBookServiceProvider.setBookingRequest(dataToInsert);
            objBookServiceProvider = new BookServiceProviderDAO();

            IPaymentService paymentService=new PaymentService();
            boolean paymentStatus=false;
            IPaymentInfo paymentInfo=paymentService.getPaymentInfoFromDatabase(CUSTOMER_SESSION.get("customer_id"));
            if(paymentInfo==null)
            {
                displayPaymentUI.getPaymentDetailsInput(senderPaymentDetails);
            }
            if (insertStatus)
            {
                objDisplayMessage.displayMessage("Ticket has been generated.");
                paymentStatus = serviceProvider.acceptPayment(senderPaymentDetails);
            }
            boolean feedbackStatus = false;
            if(insertStatus)
            {
                objDisplayMessage.displayMessage("Ticket has been generated.");
            }
            if (paymentStatus)
            {
                feedbackUI.getReviewDetailsInput(review);
                feedbackUI.setFeedback(customerReview,review);
                feedbackStatus = feedbackDAO.write(customerReview);
                if (feedbackStatus)
                {
                    System.out.println("Thanks For feedback!");
                }
            }
            return insertStatus;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                db.closeConnection();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}