package login;

import SearchServiceCategory.SelectServiceCategory;
import presentationlayer.ServiceProviderCustomerUI;

import java.util.Map;
import java.util.Set;

public class LoginService implements ILoginService
{
   private String Email;
   private String Password;
   private LoginDAO loginDAO =null;
   private Map<String , Map<String,String>> result = null;
   private Map<String,String> tempValues = null;
   private SelectServiceCategory objServiceCategory = null;
   private ServiceProviderCustomerUI serviceProvider = null;



   public boolean loginUser(String email, String password,String type)
   {
      loginDAO=new LoginDAO();
      result=loginDAO.applicationLogin(email,password,type);
      if(result.isEmpty())
      {
         System.out.println("Username/Password is incorrect . Please try again.");
         return false;
      } else {
         for(String str : result.keySet())
         {
            tempValues = result.get(str);
            Email = tempValues.get("Email");
            Password = tempValues.get("Password");
         }
         if (Email.equals(email) && Password.equals(password) && type.equals("customer"))
         {
            objServiceCategory = new SelectServiceCategory(tempValues);
            objServiceCategory.getUserSelectedService();

         } else {
            serviceProvider = new ServiceProviderCustomerUI(result);
            serviceProvider.showCustomerRequestUI();
         }
         return true;
      }
   }

   public Map<String, Map<String,String>>  getPendingRequests(String username)
   {
      loginDAO =new LoginDAO();
      Map<String, Map<String,String>> customerRequests= null;
      try {
         customerRequests = loginDAO.getAllCustomerRequests(username);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return customerRequests;
   }

}
