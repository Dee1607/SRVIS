public class mainUI {

    public static void main(String[] args){
        RegisterationPage register = new RegisterationPage();
        register.registerUser();

        ServiceCategoryUI serviceCategoryUI = new ServiceCategoryUI();
        serviceCategoryUI.searchService();
    }
}