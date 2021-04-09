package registration;

import presentationlayer.DisplayRegistrationPageUI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationMethods implements IRegistrationMethods
{
    public static int count = 1;
    public ArrayList<String> result;
    Map<Integer, Runnable> registerUserMethods = new ConcurrentHashMap<Integer, Runnable>();
    RegistrationList genericList;
    DisplayRegistrationPageUI registerUser;
    IRegistrationDAO hitDB;

    public RegistrationMethods()
    {
        hitDB = new RegistrationDAO();
        registerUser = new DisplayRegistrationPageUI();
        genericList = new RegistrationList();
    }

    public void addMethodToHashMap(String methodDetail, String pattern)
    {
        if (methodDetail == "job type")
        {
            registerUserMethods.put(count, () -> result = registerUser.getJobType(pattern));
        }
        else
        {
            registerUserMethods.put(count, () -> result = registerUser.getUserDetails(methodDetail, pattern));
        }
        count++;
    }

    public Map<Integer, Runnable> addMethods(String getUser)
    {
        addMethodToHashMap("first name", "[a-zA-Z]+");
        addMethodToHashMap("last name", "[a-zA-Z]+");
        addMethodToHashMap("contact number", "^[0-9]{10}$");
        addMethodToHashMap("address", "^[a-zA-Z0-9-/,]+$");
        addMethodToHashMap("email ID", "^[a-zA-Z0-9][-\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        addMethodToHashMap("new password(only characters and numbers are allowed)", "^[a-zA-Z0-9]+$");
        if (getUser == "Service Provider")
        {
            addMethodToHashMap("job type", "^[1-5]$");
            addMethodToHashMap("years of experience in the field", "^[0-9]+$");
            addMethodToHashMap("certification", "^[a-zA-Z0-9]+$");
            addMethodToHashMap("hourly rate", "^[\\d]+$");
            addMethodToHashMap("age", "^[\\d]+$");
        }
        genericList.setRegisterUserMethods(registerUserMethods);
        return registerUserMethods;
    }

    public boolean callMethod(Map<Integer, Runnable> methodList)
    {
        Map<Integer, Runnable> registerMethods;
        registerMethods = genericList.getRegisterUserMethods();
        Iterator<Integer> iterator = registerMethods.keySet().iterator();
        boolean dbStatus = false;
        while (iterator.hasNext())
        {
            for (int key : registerMethods.keySet())
            {
                registerMethods.get(key).run();
                if (result.size() > 0) {
                    String getData = result.get(0);
                    String[] getValueresult = getData.split("-");
                    if (getValueresult[0].equals("true")) {
                        genericList.removeRegisterUserMethods(key);
                        genericList.setUserDetails(String.valueOf(key), getValueresult[1]);
                    }
                }
            }
            if (registerMethods.size() == 0)
            {
                dbStatus = hitDB.getConnection(genericList.getUserDetails());
                return dbStatus;
            }
            else
            {
                System.out.println("Please enter invalid values");
            }
        }
        return dbStatus;
    }
}