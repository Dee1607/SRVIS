import presantationlayer.LoginPage;

public class mainUI {

    public static void main(String[] args){

        LoginPage log=new LoginPage();
        try {
            log.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}