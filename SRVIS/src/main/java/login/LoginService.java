package login;

import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;

import java.util.Map;

public class LoginService implements ILoginService {
   private ILoginDAO IloginDAO = null;
   private IDisplayToGetUserChoice display=null;

   public LoginService()
   {
      this.display=new DisplayToGetUserChoice();
      IloginDAO=new LoginDAO();
   }

   @Override
   public Map<String,String> loginUser(String email, String password, String type) {

      String Email = null;
      String Password = null;
      String name=null;
      Map<String,String> tempValues = null;
      Map<String , Map<String,String>> result = IloginDAO.applicationLogin(email, password, type);
      for (String str : result.keySet()) {
         tempValues = result.get(str);
         Email = tempValues.get("email");
         Password = tempValues.get("password");
      }

      if ((Email.equals(email) && Password.equals(password))) {
            display.displayMessage("Login Successful !!");
      }
         return tempValues;
   }

   @Override
   public Map<String,String>  getPendingRequests(String email, String type)
   {
      Map<String, Map<String,String>> customerRequests=null;
      customerRequests = IloginDAO.getAllCustomerRequests(email,type);
      Map<String,String> result=null;
      for (String str : customerRequests.keySet())
      {
         result = customerRequests.get(str);
      }
      return result;
   }
}