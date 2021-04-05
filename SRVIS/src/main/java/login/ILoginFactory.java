package login;

public interface ILoginFactory {

    public ILoginDAO loginDAO();
    public ILoginService loginService();

}
