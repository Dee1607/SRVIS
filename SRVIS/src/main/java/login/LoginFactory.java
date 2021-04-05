package login;

public class LoginFactory implements ILoginFactory{

    private static LoginFactory loginFactory ;

    public static LoginFactory LoginInstance() {
        loginFactory = new LoginFactory();
        return loginFactory;
    }

    public ILoginDAO loginDAO(){
        return new LoginDAO();
    }

    public ILoginService loginService(){
        return new LoginService();
    }
}
