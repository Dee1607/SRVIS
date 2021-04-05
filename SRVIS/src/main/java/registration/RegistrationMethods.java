package registration;

import presentationlayer.DisplayUpdates;
import presentationlayer.LoginUI;
import presentationlayer.RegistrationPageUI;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationMethods {
    RegistrationPageUI registerUser = new RegistrationPageUI();
    public static int count =1;

    Map<Integer,Runnable> registerUserMethods = new ConcurrentHashMap<Integer,Runnable>();
    Register genericList = new Register();
    public String result;
    public void addMethodToHashMap(String methodDetail, String pattern){
        if(methodDetail=="job type"){
            registerUserMethods.put(count,() -> result = registerUser.getJobType(pattern));
        }
        else{
            registerUserMethods.put(count,() -> result = registerUser.getUserDetails(methodDetail,pattern));
        }
        count++;
    }

    public void addMethods(String getUser){
        addMethodToHashMap("first name", "[a-zA-Z]+");
        addMethodToHashMap("last name", "[a-zA-Z]+");
        addMethodToHashMap("contact number", "^[0-9]{10}$");
        addMethodToHashMap("address", "^[a-zA-Z0-9-/,]+$");
        addMethodToHashMap("email ID", "^[a-zA-Z0-9][-\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        addMethodToHashMap("new password", "[a-zA-Z]+");
        if(getUser == "Service Provider"){
            addMethodToHashMap("job type","^[1-5]$");
            addMethodToHashMap("years of experience in the field", "^[0-9]+$");
            addMethodToHashMap("certification", "^[a-zA-Z0-9]+$");
            addMethodToHashMap("hourly rate", "^[\\d]+$");
            addMethodToHashMap("age", "^[\\d]+$");
        }
        genericList.setRegisterUserMethods(registerUserMethods);
    }

    public void callMethods(){
        registerUserMethods = genericList.getRegisterUserMethods();

        Iterator<Integer> iterator = registerUserMethods.keySet().iterator();

        while(iterator.hasNext()){
            for (int key : registerUserMethods.keySet()) {
                registerUserMethods.get(key).run();
                String[] getValueresult = result.split("-");
                if (getValueresult[0].equals("Success")){
                    genericList.removeRegisterUserMethods(key);
                    genericList.setUserDetails(String.valueOf(key), getValueresult[1]);
                }
            }
            if(registerUserMethods.size() == 0){
                RegistrationDAO hitDB = new RegistrationDAO();
                boolean result = hitDB.getConnection(genericList.getUserDetails());
                if(result==true){
                    DisplayUpdates objDisplayMessage = new DisplayUpdates();
                    objDisplayMessage.displayMessage("Thank you for registering with us." + "\n" + "Please login.");
                    LoginUI login = new LoginUI();
                    login.showLoginScreen();
                }
                return;
            }
            else {
                System.out.println("Please enter invalid values");
            }
        }
    }
}
