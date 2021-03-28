import login.LoginService;
import presantationlayer.DisplayToGetUserChoice;
import presentationlayer.LoginUI;

public class mainUI
{
    public static void main(String[] args)
    {
        LoginUI log = null;
        LoginService login = null;
        DisplayToGetUserChoice objGetData = null;
        try
        {
            log = new LoginUI();

//            log.showLoginScreen();

            objGetData = new DisplayToGetUserChoice();
            String username = objGetData.displayMessageGetStringChoiceFromUser("Enter your Username: ");
            String password = objGetData.displayMessageGetStringChoiceFromUser("Enter your password: ");
            String type = objGetData.displayMessageGetStringChoiceFromUser("Login as Customer/Service provider: ");

            login = new LoginService();
            login.loginUser(username, password,type);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}