import encryption.Encryption;
import controller.ApplicationController;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
<<<<<<< HEAD
import presentationlayer.DisplayLoginUI;
=======
import presentationlayer.LoginUI;
>>>>>>> dc01aef00a3a908351559ebb5395422cbc090c2e

public class mainUI
{
    public static void main(String[] args)
    {
        Encryption ob = new Encryption();
        ob.encryptString("Hello1");

        IDisplayToGetUserChoice objToDisplay =new DisplayToGetUserChoice();

        ApplicationController objApplication = new ApplicationController(objToDisplay);
        objToDisplay.displayMessage("########################### Welcome to SRVIS ###########################");
        DisplayLoginUI login = new DisplayLoginUI(objToDisplay);

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