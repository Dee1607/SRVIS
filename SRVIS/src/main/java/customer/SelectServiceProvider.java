package customer;

import database.DatabaseConnection;
import presentationlayer.DisplayServiceProviderInfoUI;
import presentationlayer.DisplayToGetUserChoice;
import java.util.Map;

public class SelectServiceProvider implements ISelectServiceProvider
{
    private DisplayToGetUserChoice objGetUserChoice = null;
    private DisplayServiceProviderInfoUI objDisplayServiceProvider = null;
    private final DatabaseConnection db = DatabaseConnection.databaseInstance();
    private Map<String, String> CUSTOMER_SESSION;
    private BookServiceProvider objBookServiceProvider = null;
    ServiceProviderDAO objServiceProviderDAO = null;

    public SelectServiceProvider(Map<String, String> customerSession)
    {
        this.CUSTOMER_SESSION = customerSession;
        objServiceProviderDAO = new ServiceProviderDAO();
    }

    public Map<String,Map<String,String>> getServiceProvidersOfSelectedCategory(EnumServiceCategory userChoice)
    {
        try
        {
            Map<String, Map<String, String>> mapOfDataFromDatabase = objServiceProviderDAO.getServiceProvider(userChoice);

            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objDisplayServiceProvider.displayServiceProviderBriefInfo(mapOfDataFromDatabase);

            return mapOfDataFromDatabase;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            try
            {
                db.closeConnection();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public int selectFromAvailableServiceProvider(Map<String, Map<String, String>> mapOfDataFromDatabase)
    {
        objGetUserChoice = new DisplayToGetUserChoice();
        int userSelectedForServiceProvider = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the id of the service provider you want to select: ");

        return userSelectedForServiceProvider;
    }
}