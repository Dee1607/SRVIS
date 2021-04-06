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

public class LoginUI
{
    private LoginService login=null;
    private DisplayToGetUserChoice objGetData=null;
    private Map<String,String> pendingBookingValues =null;
    private IValidation validate=null;
    private IRegistrationMain registerObj=null;
    private GenerateDataToDisplay objgetDataToDisplay=null;
    private DisplayServiceCategoriesUI objDisplay=null;

    public LoginUI()
    {
        login=new LoginService();
        objGetData=new DisplayToGetUserChoice();
        validate=new Validation();
        registerObj = new RegistrationMain();
        objgetDataToDisplay = new GenerateDataToDisplay();
        objDisplay = new DisplayServiceCategoriesUI();
    }


    public int showLoginScreen()
    {
        int userInput = 0;
          try {
              Scanner sc = new Scanner(System.in);

              System.out.println("########## Welcome to SRVIS ##########");
              Map<Integer,String> objDataToDisplay = objgetDataToDisplay.generateLoginData();

              objDisplay.displayServiceCategory(objDataToDisplay);

              userInput = sc.nextInt();

              return userInput;
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return userInput;
    }

    public Map<String,String> userLogin()
    {
        String email = objGetData.displayMessageGetStringChoiceFromUser("Enter your emailID: ");
        String password = objGetData.displayMessageGetStringChoiceFromUser("Enter your password: ");
        String type = objGetData.displayMessageGetStringChoiceFromUser("Login as Customer(C)/Service Provider(SP) ( Type C or SP ): ");
        Map<String,String> mapLoginData = new HashMap<>();
        mapLoginData.put("email",email);
        mapLoginData.put("password",password);
        mapLoginData.put("type",type);

        return mapLoginData;
    }

    public void userRegistration()
    {
        registerObj.register();
    }

    public void showPendingRequest(String email,String type)
    {
        Map<String, Map<String, String>> pendingRequests = login.getPendingRequests(email,type);


        for (String keys : pendingRequests.keySet()) {
            pendingBookingValues = pendingRequests.get(keys);
            System.out.format("%1s%-20s%1s%-55s%1s", "|", "====================", "|", "========================================================", "|\n");
            System.out.format("%1s%-20s%1s%-55s%1s", "|", " Request ID ", "| ", pendingBookingValues.get("service_request_id"), "|\n");
            System.out.format("%1s%-20s%1s%-55s%1s", "|", " Service Provider ID ", "| ", pendingBookingValues.get("service_provider_id"), "|\n");
            System.out.format("%1s%-20s%1s%-55s%1s", "|", " Request Description ", "| ", pendingBookingValues.get("service_request_description"), "|\n");
            System.out.format("%1s%-20s%1s%-55s%1s", "|", "--------------------", "|", "--------------------------------------------------------", "|\n");
        }
    }
}