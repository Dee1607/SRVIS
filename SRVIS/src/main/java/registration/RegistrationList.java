package registration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationList
{
    public static Map<Integer, Runnable> getRegisterUserMethods()
    {
        return registerUserMethods;
    }

    public static void setRegisterUserMethods(Map<Integer, Runnable> registerUserMethods)
    {
        RegistrationList.registerUserMethods = registerUserMethods;
    }

    public static void removeRegisterUserMethods(int key)
    {
        registerUserMethods.remove(key);
    }

    public static Map<Integer, Runnable> registerUserMethods = new ConcurrentHashMap<Integer, Runnable>();

    public static HashMap<String, String> getUserDetails()
    {
        return userDetails;
    }

    public static void setUserDetails(String key, String value)
    {
        userDetails.put(key, value);
    }

    public static HashMap<String, String> userDetails = new HashMap<>();
}
