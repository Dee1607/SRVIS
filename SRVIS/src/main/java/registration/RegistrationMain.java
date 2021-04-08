package registration;

import presentationlayer.DisplayServiceCategoriesUI;
import presentationlayer.IDisplayToGetUserChoice;
import presentationlayer.DisplayLoginUI;

import java.util.HashMap;
import java.util.Scanner;

public class RegistrationMain implements IRegistrationMain{

    private DisplayServiceCategoriesUI displayData;
    private IRegistrationMethods registrationMethods;
    private IValidation validateInput;
    private IDisplayToGetUserChoice display=null;
    public RegistrationMain(IDisplayToGetUserChoice display)
    {
        displayData = new DisplayServiceCategoriesUI();
        registrationMethods = new RegistrationMethods();
        validateInput = new Validation();
        this.display=display;

    }
    public void register(){
        try{
            System.out.println("Register as");
            HashMap<Integer, String> registerAs = new HashMap<>();
            registerAs.put(1,"Customer");
            registerAs.put(2,"Service Provider");
            for(Integer i: registerAs.keySet())
            {
                display.displayMessage(i + " " + registerAs.get(i));
            }

            Scanner sc = new Scanner(System.in);
            String  value = sc.nextLine();
            if(validateInput.isValidString("^[1-2]$",value)){
                Integer getValue = Integer.valueOf(value);
                display.displayMessage("======== " + registerAs.get(getValue) +" Registration" + " ========");
                registrationMethods.addMethods(registerAs.get(getValue));
                boolean result = registrationMethods.callMethod();
                if(result==true){
                    display.displayMessage("Thank you for registering with us." + "\n" + "Please login.");
                    DisplayLoginUI login = new DisplayLoginUI(display);
                    login.showLoginScreen();
                }
                else{
                    display.displayMessage("Problem in connection with database");
                    System.exit(0);
                }
            }
            else {
                display.displayMessage("Invalid Input");
            }
        }
        catch (Exception ex){
            display.displayMessage("Problem in parsing registration page." + "\n" + "Error Code- 100");
        }
    }
}
