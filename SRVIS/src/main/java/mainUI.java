import presentationlayer.LoginUI;

public class mainUI
{
    public static void main(String[] args)
    {
        LoginUI login = new LoginUI();
        try
        {
            login.showLoginScreen();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}