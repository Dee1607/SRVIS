package login;

import SearchServiceCategory.ISelectServiceCategory;
import SearchServiceCategory.SelectServiceCategory;
import SearchServiceProvider.ISelectServiceProvider;
import presentationlayer.ServiceProviderCustomerUI;
import java.util.Map;

public class LoginService implements ILoginService
{

   private Map<String,String> tempValues = null;
   private ILoginDAO IloginDAO = null;
   private Map<String , Map<String,String>> result = null;
   private ISelectServiceCategory objServiceCategory = null;
   private ServiceProviderCustomerUI serviceProvider = null;

   public LoginService()
   {
      IloginDAO=new LoginDAO();
   }

   public void loginUser(String email, String password,String type)
   {
      result = IloginDAO.applicationLogin(email,password,type);
      String Email=null;
      String Password=null;

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
            if (Email.equals(email) && Password.equals(password) && type.equalsIgnoreCase("c"))
            {
               objServiceCategory = new SelectServiceCategory(tempValues);
               objServiceCategory.getUserSelectedService();
            }
            else
            {
               serviceProvider = new ServiceProviderCustomerUI(result);
               serviceProvider.showCustomerRequestUI(tempValues);

            }
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public Map<String, Map<String,String>>  getPendingRequests(String email,String type)
   {
      Map<String, Map<String,String>> customerRequests= null;
      try
      {
         customerRequests = IloginDAO.getAllCustomerRequests(email,type);
      } catch (Exception e)
      {
         e.printStackTrace();
      }
      return customerRequests;
   }
}