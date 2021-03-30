package login;

import SearchServiceCategory.SelectServiceCategory;
import presentationlayer.ServiceProviderCustomerUI;
import java.util.Map;

public class LoginService
{
   private String Email = null;
   private String Password = null;
   private Map<String,String> tempValues = null;

   public void loginUser(String user, String password, String type) throws Exception
   {
      LoginDAO loginDAO = null;
      Map<String , Map<String,String>> result = null;
      SelectServiceCategory objServiceCategory = null;
      ServiceProviderCustomerUI serviceProvider = null;

      loginDAO = new LoginDAO();
      result = loginDAO.AppLogin(user,password,type);
      if(result.isEmpty())
      {
         System.out.println("Username/Password is incorrect . Please try again .");
      }
      else
      {
         for(String str : result.keySet())
         {
            tempValues = result.get(str);
            Email = tempValues.get("Email");
            Password = tempValues.get("Password");
         }
         if (Email.equals(user) && Password.equals(password) && type.equals("customer"))
         {
            objServiceCategory = new SelectServiceCategory(tempValues);
            objServiceCategory.getUserSelectedService();
         }
         else
         {
            serviceProvider = new ServiceProviderCustomerUI(result);
            serviceProvider.showCustomerRequestUI();
         }
      }
   }
}