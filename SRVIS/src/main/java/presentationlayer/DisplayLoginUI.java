package presentationlayer;

import customer.GenerateDataToDisplay;
import login.LoginService;
import registration.IRegistrationMain;
import registration.IValidation;
import registration.RegistrationMain;
import registration.Validation;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DisplayLoginUI
{
    private LoginService login = null;
    private DisplayToGetUserChoice objGetData = null;
    private Map<String, String> pendingBookingValues = null;
    private IValidation validate = null;
    private IRegistrationMain registerObj = null;
    private GenerateDataToDisplay objectDataToDisplay = null;
    private DisplayServiceCategoriesUI objDisplay = null;
    private IDisplayToGetUserChoice display = null;

    public DisplayLoginUI(IDisplayToGetUserChoice display)
    {
        login = new LoginService();
        objGetData = new DisplayToGetUserChoice();
        validate = new Validation();
        registerObj = new RegistrationMain(display);
        objectDataToDisplay = new GenerateDataToDisplay();
        registerObj = new RegistrationMain(new DisplayToGetUserChoice());
        objectDataToDisplay = new GenerateDataToDisplay();
        objDisplay = new DisplayServiceCategoriesUI();
    }

    public int showLoginScreen()
    {
        int userInput = 0;
        try
        {
            Scanner sc = new Scanner(System.in);
            Map<Integer, String> objDataToDisplay = objectDataToDisplay.generateLoginData();
            objDisplay.displayServiceCategory(objDataToDisplay);
            userInput = sc.nextInt();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userInput;
    }

    public Map<String, String> userLogin()
    {
        String email = objGetData.displayMessageGetStringChoiceFromUser("Enter your emailID: ");
        String getpassword = objGetData.displayMessageGetStringChoiceFromUser("Enter your password: ");
        String type = objGetData.displayMessageGetStringChoiceFromUser("Login as Customer(C)/Service Provider(SP) ( Type C or SP ): ");
        Map<String, String> mapLoginData = new HashMap<>();
        mapLoginData.put("email", email);
        mapLoginData.put("password", getpassword);
        mapLoginData.put("type", type);
        return mapLoginData;
    }

    public void showPendingRequest(String email, String type)
    {
        Map<String, String> pendingRequests = login.getPendingRequests(email, type);
        System.out.format("%1s%-20s%1s%-55s%1s", "|", "====================", "|", "========================================================", "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Request ID ", "| ", pendingRequests.get("service_request_id"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Service Provider ID ", "| ", pendingRequests.get("service_provider_id"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Request Description ", "| ", pendingRequests.get("service_request_description"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Request Status ", "| ", pendingRequests.get("request_acceptance_status"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", "--------------------", "|", "--------------------------------------------------------", "|\n");
    }
}