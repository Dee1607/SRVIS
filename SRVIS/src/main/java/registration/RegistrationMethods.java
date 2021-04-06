package registration;


import presentationlayer.RegistrationPageUI;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationMethods implements IRegistrationMethods{

    public static int count =1;
    public String result;
    Map<Integer,Runnable> registerUserMethods = new ConcurrentHashMap<Integer,Runnable>();

    RegistrationList genericList;
    RegistrationPageUI registerUser;
    IRegistrationDAO hitDB;
    public RegistrationMethods(){
        hitDB = new RegistrationDAO();
        registerUser = new RegistrationPageUI();
        genericList = new RegistrationList();
    }

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
        addMethodToHashMap("new password(only characters and numbers are allowed)", "^[a-zA-Z0-9]+$");
        if(getUser == "Service Provider"){
            addMethodToHashMap("job type","^[1-5]$");
            addMethodToHashMap("years of experience in the field", "^[0-9]+$");
            addMethodToHashMap("certification", "^[a-zA-Z0-9]+$");
            addMethodToHashMap("hourly rate", "^[\\d]+$");
            addMethodToHashMap("age", "^[\\d]+$");
        }
        genericList.setRegisterUserMethods(registerUserMethods);
    }

    public boolean callMethod(){
        registerUserMethods = genericList.getRegisterUserMethods();
        Iterator<Integer> iterator = registerUserMethods.keySet().iterator();
        boolean dbStatus=false;
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
                dbStatus = hitDB.getConnection(genericList.getUserDetails());
                return dbStatus;
            }
            else {
                System.out.println("Please enter invalid values");
            }
        }
        return dbStatus;
    }
}
