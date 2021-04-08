package registration;

public interface IRegistrationMethods {
    public void addMethodToHashMap(String methodDetail, String pattern);

    public void addMethods(String getUser);

    public boolean callMethod();
}
