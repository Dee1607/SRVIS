import SearchServiceCategory.SelectServiceCategory;
import presantationlayer.LoginPage;

public class mainUI {

    public static void main(String[] args){

        LoginPage log=new LoginPage();
        try {
//            log.login();

            SelectServiceCategory obj = new SelectServiceCategory();
            obj.searchService();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}