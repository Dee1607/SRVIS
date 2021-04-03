package presentationlayer;

import login.LoginService;

import java.util.Map;
import java.util.Scanner;

public class LoginUI
{
    private String username;
    private String password;
    private String type;
    private LoginService login ;
    private DisplayToGetUserChoice objGetData ;
    private Map<String,String> pendingBookingValues =null;

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
           login = new LoginService();
           objGetData = new DisplayToGetUserChoice();
           String username = objGetData.displayMessageGetStringChoiceFromUser("Enter your Username: ");
           String password = objGetData.displayMessageGetStringChoiceFromUser("Enter your password: ");
           String type = objGetData.displayMessageGetStringChoiceFromUser("Login as Customer/Service provider: ");
           login.loginUser(username, password,type);
    }

    public void userRegistration()
    {
           System.out.println("Please register  your account !!");
           RegistrationPageUI register = new RegistrationPageUI();
           register.getFirstName();
           register.getLastName();
           register.getAddress();
           register.getContact();
           register.getEmail();
           register.getPassword();
           register.getProfessionalCategoryDetails();
           register.checkErrors();
    }

    public void showPendingRequest(String username) throws Exception
    {
        Map<String, Map<String, String>> pendingRequests = login.getPendingRequests(username);
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