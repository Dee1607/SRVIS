import controller.ApplicationController;
import presentationlayer.DisplayLoginUI;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
import registration.RegistrationMain;

public class mainUI {
    public static void main(String[] args) {
        IDisplayToGetUserChoice objToDisplay = new DisplayToGetUserChoice();

        ApplicationController objApplication = new ApplicationController(objToDisplay);
        objToDisplay.displayMessage("########################### Welcome to SRVIS ###########################");
        try {
            objApplication.initializeApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}