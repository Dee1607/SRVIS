package presentationlayer;

import login.LoginService;
import java.util.Scanner;

public class LoginUI
{
       LoginService login ;
       DisplayToGetUserChoice objGetData ;

    public void showLoginScreen() throws Exception
    {
           Scanner sc = new Scanner(System.in);
           System.out.println("########## Welcome to SRVIS ##########");
           System.out.println("Select from below options :");
           System.out.println("1. Login");
           System.out.println("2. Register");
           String userInput = sc.nextLine();
           if (userInput.equals("1"))
           {
                  userLogin();
           }
           else if(userInput.equals("2"))
           {
                  userRegistration();
           }
           else
           {
                  System.out.println("Please enter valid input .");
           }
    }

    public void userLogin() throws Exception
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
//           register.getProfessionalCategoryDetails();
           register.checkErrors();
    }
}