package customer;

import database.DatabaseConnection;
import enums.EnumServiceCategory;
import presentationlayer.DisplayServiceProviderInfoUI;
import presentationlayer.DisplayToGetUserChoice;

import java.util.Map;

public class SelectServiceProvider implements ISelectServiceProvider
{
    private DisplayToGetUserChoice objGetUserChoice = null;
    private DisplayServiceProviderInfoUI objDisplayServiceProvider = null;
    private final DatabaseConnection db = DatabaseConnection.databaseInstance();
    private Map<String,String> CUSTOMER_SESSION;
    private BookServiceProvider objBookServiceProvider= null;

    public SelectServiceProvider(Map<String,String> customerSession)
    {
        this.CUSTOMER_SESSION = customerSession;
    }

    public Map<String,Map<String,String>> getServiceProvidersOfSelectedCategory(EnumServiceCategory userChoice)
    {
        try
        {
            db.makeConnection();
            Map<String, Map<String, String>> mapOfDataFromDatabase = db.selectQuery("SELECT * FROM CSCI5308_3_DEVINT.service_provider where jobType = '"
                    + userChoice.toString() + "'");

            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objDisplayServiceProvider.displayServiceProviderBriefInfo(mapOfDataFromDatabase);

            selectFromAvailableServiceProvider(mapOfDataFromDatabase);
            return mapOfDataFromDatabase;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                db.closeConnection();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public int selectFromAvailableServiceProvider(Map<String, Map<String, String>> mapOfDataFromDatabase)
    {
        objGetUserChoice = new DisplayToGetUserChoice();
        int userSelectedForServiceProvider = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the Id of the service provider you want to select: ");

        return userSelectedForServiceProvider;
    }
}