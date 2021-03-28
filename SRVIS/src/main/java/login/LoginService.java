package login;


import SearchServiceCategory.SelectServiceCategory;
import presentationlayer.RegistrationPageUI;
import presentationlayer.ServiceProviderCustomerUI;

import java.util.Map;

public class LoginService {


   private String Email;
   private String Password;


   public void loginUser(String user, String password, String type) throws Exception {
      LoginDAO loginDAO=new LoginDAO();
      Map<String , Map<String,String>> result=loginDAO.AppLogin(user,password,type);

      if(result.isEmpty())
      {
         System.out.println("Please register  your account !!");
         RegistrationPageUI register = new RegistrationPageUI();
         register.getFirstName();
         register.getLastName();
         register.getAddress();
         register.getContact();
         register.getEmail();
         register.getPassword();
         register.getProfessionalCategoryDetails();
         register.checkErrors();
      } else{
         for(String str : result.keySet()){
            Map<String,String> tempValues = result.get(str);
            Email = tempValues.get("Email");
            Password = tempValues.get("Password");
         }
         if (Email.equals(user) && Password.equals(password) && type.equals("customer"))
         {
            SelectServiceCategory obj = new SelectServiceCategory(result);
            obj.searchService();
         } else {
            ServiceProviderCustomerUI serviceProvider =new ServiceProviderCustomerUI(result);
            serviceProvider.showCustomerRequestUI();
      }
      }
   }
}
