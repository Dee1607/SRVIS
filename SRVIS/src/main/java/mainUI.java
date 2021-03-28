import presentationlayer.LoginUI;

public class mainUI
{
    public static void main(String[] args)
    {
        LoginUI log = null;
        try
        {
            log = new LoginUI();

            log.showLoginScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}