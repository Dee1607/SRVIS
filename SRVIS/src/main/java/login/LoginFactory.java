package login;

import presentationlayer.IDisplayToGetUserChoice;

public class LoginFactory implements ILoginFactory{

    private static LoginFactory loginFactory =null;
    private IDisplayToGetUserChoice display=null;

    public LoginFactory(IDisplayToGetUserChoice display){
        this.display=display;
    }

    public LoginFactory() {

    }

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
