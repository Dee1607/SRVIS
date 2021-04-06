import controller.ApplicationController;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.LoginUI;

public class mainUI
{
    public static void main(String[] args)
    {
        DisplayToGetUserChoice objToDisplay = new DisplayToGetUserChoice();

        ApplicationController objApplication = new ApplicationController(objToDisplay);
        objToDisplay.displayMessage("########################### Welcome to SRVIS ###########################");
        LoginUI login = new LoginUI();

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