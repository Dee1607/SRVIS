package login;

public class LoginFactory implements ILoginFactory{

    private static LoginFactory loginFactory ;

    public static LoginFactory LoginInstance() {
        loginFactory = new LoginFactory();
        return loginFactory;
    }

    public ILoginDAO getLoginDAO(){
        return new LoginDAO();
    }

    public ILoginService getLoginService(){
        return new LoginService();
    }
}
