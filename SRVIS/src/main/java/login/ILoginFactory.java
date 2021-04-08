package login;

import presentationlayer.IDisplayToGetUserChoice;

public interface ILoginFactory {

    public ILoginDAO loginDAO();
    public ILoginService loginService();

}
