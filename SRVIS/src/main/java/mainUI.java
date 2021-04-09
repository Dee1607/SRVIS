import controller.ApplicationController;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;

public class mainUI {
    public static void main(String[] args)
    {
        IDisplayToGetUserChoice objToDisplay = new DisplayToGetUserChoice();

        ApplicationController objApplication = new ApplicationController(objToDisplay);
        objToDisplay.displayMessage("########################### Welcome to SRVIS ###########################");
        try
        {
            objApplication.initializeApplication();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}