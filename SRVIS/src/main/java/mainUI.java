import presentationlayer.LoginUI;

public class mainUI {

    public static void main(String[] args) {

        LoginUI log = new LoginUI();
        try {
            log.showLoginScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}