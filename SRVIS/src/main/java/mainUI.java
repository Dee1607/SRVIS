import presantationlayer.LoginPage;
import presantationlayer.ServiceCategoryUI;
import registration.RegistrationPage;

public class mainUI {

    public static void main(String[] args){

        LoginPage log=new LoginPage();
        try {
//            log.login();

            RegistrationPage register = new RegistrationPage();
            register.getFirstName();
            register.getLastName();
            register.getAddress();
            register.getContact();
            register.getEmail();
            register.getPassword();
            register.getProfessionalCategoryDetails();
            register.checkErrors();

            ServiceCategoryUI obj = new ServiceCategoryUI();
            obj.searchService();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}