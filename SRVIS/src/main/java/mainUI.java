import controller.ApplicationController;
import presentationlayer.LoginUI;

public class mainUI
{
    public static void main(String[] args)
    {
        ApplicationController objApplication = new ApplicationController();
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