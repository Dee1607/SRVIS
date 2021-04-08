import controller.ApplicationController;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
import presentationlayer.DisplayLoginUI;

public class mainUI
{
    public static void main(String[] args)
    {
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