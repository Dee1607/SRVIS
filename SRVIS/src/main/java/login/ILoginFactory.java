package login;

public interface ILoginFactory
{
    static LoginFactory LoginInstance()
    {
      return new LoginFactory();
    }

    ILoginDAO loginDAO();

    ILoginService loginService();
}