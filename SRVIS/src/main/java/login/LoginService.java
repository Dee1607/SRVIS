package login;

import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
import java.util.Map;

public class LoginService implements ILoginService
{
   private final ILoginDAO IloginDAO;
   private final IDisplayToGetUserChoice display;

   public LoginService()
   {
      this.display = new DisplayToGetUserChoice();
      this.IloginDAO = new LoginDAO();
   }


   @Override
   public Map<String,String> loginUser(String email, String password, String type)
   {
      String expectedEmail = "";
      String expectedPassword = "";
      Map<String,String> tempValues = null;
      Map<String , Map<String,String>> result = IloginDAO.applicationLogin(email, password, type);
      if (result == null)
      {
         display.displayMessage("User is not registered!");
         System.exit(0);
      }
      else
      {
         for (String str : result.keySet())
         {
            tempValues = result.get(str);

            expectedEmail = tempValues.get("email");
            expectedPassword = tempValues.get("password");
         }
         if ((expectedEmail.equals(email) && expectedPassword.equals(password)))
         {
            display.displayMessage("Login Successful!");
         }
      }
      return tempValues;
   }

   @Override
   public Map<String,String>  getPendingRequests(String email, String type)
   {
      Map<String, Map<String,String>> customerRequests;
      customerRequests = IloginDAO.getAllCustomerRequests(email,type);
      Map<String,String> result = null;
      for (String str : customerRequests.keySet())
      {
         result = customerRequests.get(str);
      }
      return result;
   }
}