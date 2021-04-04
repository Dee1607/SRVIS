package login;

import SearchServiceCategory.SelectServiceCategory;
import presentationlayer.ServiceProviderCustomerUI;
import java.util.Map;

public class LoginService implements ILoginService
{
   private String Email = null;
   private String Password = null;
   private Map<String,String> tempValues = null;
   private ILoginDAO loginDAO = null;
   private Map<String , Map<String,String>> result = null;
   private SelectServiceCategory objServiceCategory = null;
   private ServiceProviderCustomerUI serviceProvider = null;

   public void loginUser(String email, String password, String type)
   {
      ILoginDAO loginDAO = new LoginDAO();
      result = loginDAO.applicationLogin(email,password,type);
      try
      {
         if(result.isEmpty())
         {
            System.out.println("Username/Password is incorrect . Please try again .");
         }
         else
         {
            for(String str : result.keySet())
            {
               tempValues = result.get(str);
               Email = tempValues.get("email");
               Password = tempValues.get("password");
            }
            if (Email.equals(email) && Password.equals(password) && type.equals("customer"))
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
      catch(Exception e)
      {
         e.printStackTrace();
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