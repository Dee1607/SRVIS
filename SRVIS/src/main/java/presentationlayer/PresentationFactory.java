package presentationlayer;

import java.util.Map;

public class PresentationFactory {

        private static PresentationFactory presentationFactory;

        public static PresentationFactory presentationInstance() {
            presentationFactory = new PresentationFactory();
            return presentationFactory;
        }

        public DisplayServiceCategoriesUI DisplayServiceCategoriesUI(){
            return new DisplayServiceCategoriesUI();
        }
        public DisplayServiceProviderInfoUI DisplayServiceProviderInfoUI(){
            return new DisplayServiceProviderInfoUI();
        }
        public DisplayToGetUserChoice DisplayToGetUserChoice(){
            return new DisplayToGetUserChoice();
        }
        public DisplayUpdates DisplayUpdates(){
            return new DisplayUpdates();
        }
        public LoginUI LoginUI(IDisplayToGetUserChoice display){
            return new LoginUI(display);
        }
        public RegistrationPageUI RegistrationPageUI(){
            return new RegistrationPageUI();
        }
        public ServiceProviderCustomerUI ServiceProviderCustomerUI(Map<String,String> loginUser,IDisplayToGetUserChoice display){
            return new ServiceProviderCustomerUI(loginUser,display);
        }
}