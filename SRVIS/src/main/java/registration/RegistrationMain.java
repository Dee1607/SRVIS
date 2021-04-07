package registration;

import presentationlayer.DisplayServiceCategoriesUI;
import presentationlayer.DisplayUpdates;
import presentationlayer.LoginUI;

import java.util.HashMap;
import java.util.Scanner;

public class RegistrationMain implements IRegistrationMain{
    DisplayServiceCategoriesUI displayData;
    IRegistrationMethods registrationMethods;
    DisplayUpdates objDisplayMessage;
    IValidation validateInput;
    public RegistrationMain()
    {
        displayData = new DisplayServiceCategoriesUI();
        registrationMethods = new RegistrationMethods();
        objDisplayMessage = new DisplayUpdates();
        validateInput = new Validation();
    }
    public void register(){
        try{
            System.out.println("Register as");
            HashMap<Integer, String> registerAs = new HashMap<>();
            registerAs.put(1,"Customer");
            registerAs.put(2,"Service Provider");
            for(Integer i: registerAs.keySet())
            {
                System.out.println (i + " " + registerAs.get(i));
            }

            Scanner sc = new Scanner(System.in);
            String  value = sc.nextLine();
            if(validateInput.isValidString("^[1-2]$",value)){
                Integer getValue = Integer.valueOf(value);
                System.out.println("======== " + registerAs.get(getValue) +" Registration" + " ========");
                registrationMethods.addMethods(registerAs.get(getValue));
                boolean result = registrationMethods.callMethod();
                if(result==true){
                    objDisplayMessage.displayMessage("Thank you for registering with us." + "\n" + "Please login.");
                    LoginUI login = new LoginUI();
                    login.showLoginScreen();
                }
                else{
                    objDisplayMessage.displayMessage("Problem in connection with database");
                    System.exit(0);
                }
            }
            else {
                System.out.println("Invalid Input");
            }
        }
        catch (Exception ex){
            System.out.println("Problem in parsing registration page." + "\n" + "Error Code- 100");
        }
    }
}
