package presentationlayer;

import login.LoginService;
import registration.IRegistrationMain;
import registration.IValidation;
import registration.RegistrationMain;
import registration.Validation;
import java.util.Map;
import java.util.Scanner;

public class LoginUI
{
    private LoginService login=null;
    private DisplayToGetUserChoice objGetData=null;
    private Map<String,String> pendingBookingValues =null;
    IValidation validate=null;
    IRegistrationMain registerObj;

    public LoginUI()
    {
        login=new LoginService();
        objGetData=new DisplayToGetUserChoice();
        validate=new Validation();
        registerObj = new RegistrationMain();
    }


    public void showLoginScreen()
    {
          try {
              Scanner sc = new Scanner(System.in);
              System.out.println("########## Welcome to SRVIS ##########");
              System.out.println("Select from below options :");
              System.out.println("1. Login");
              System.out.println("2. Register");
              String userInput = sc.nextLine();
              if (userInput.equals("1")) {
                  userLogin();

              } else if (userInput.equals("2")) {
                  userRegistration();
              } else {
                  System.out.println("Please enter valid input .");
              }
          }catch (Exception e)
          {
              e.printStackTrace();
          }
    }

    public void userLogin()
    {
           String email = objGetData.displayMessageGetStringChoiceFromUser("Enter your emailID: ");
           String password = objGetData.displayMessageGetStringChoiceFromUser("Enter your password: ");
           String type = objGetData.displayMessageGetStringChoiceFromUser("Login as Customer(C)/Service Provider(SP) ( Type C or SP ): ");
           if(validate.isValidString("^\\w{1,}@[\\w+]+.\\w+",email)) {
               login.loginUser(email, password, type);
               System.out.println("All the pending requests in your queue.!!!!");
               showPendingRequest(email, type);
           }else{
               System.out.println("Please enter valid email-id or Password !!!!");
           }
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