package login;

public interface ILoginFactory {

    public ILoginDAO getLoginDAO();
    public ILoginService getLoginService();

}
