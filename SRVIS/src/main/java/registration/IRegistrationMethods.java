package registration;

import java.util.Map;

public interface IRegistrationMethods
{
    public void addMethodToHashMap(String methodDetail, String pattern);

    public Map<Integer, Runnable> addMethods(String getUser);

    public boolean callMethod(Map<Integer, Runnable> methodList);
}
