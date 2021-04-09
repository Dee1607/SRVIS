package login;

public class LoginFactory implements ILoginFactory
{
    @Override
    public ILoginDAO loginDAO()
    {
        return new LoginDAO();
    }

    @Override
    public ILoginService loginService()
    {
        return new LoginService();
    }
}