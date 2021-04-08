import Encryption.Encryption;
import controller.ApplicationController;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
import presentationlayer.LoginUI;
import registration.RegistrationMain;

public class mainUI
{
    public static void main(String[] args)
    {
        Encryption ob = new Encryption();
        ob.encryptString("Hello1");

        IDisplayToGetUserChoice objToDisplay =new DisplayToGetUserChoice();

        ApplicationController objApplication = new ApplicationController(objToDisplay);
        objToDisplay.displayMessage("########################### Welcome to SRVIS ###########################");
        LoginUI login = new LoginUI(objToDisplay);

        try
        {
            objApplication.initializeApplication();
            //login.showLoginScreen();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}