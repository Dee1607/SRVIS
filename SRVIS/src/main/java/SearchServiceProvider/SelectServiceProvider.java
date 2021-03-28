package SearchServiceProvider;

import bookingServiceProvider.BookServiceProvider;
import presantationlayer.DisplayServiceProviderInfoUI;
import presantationlayer.DisplayToGetUserChoice;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SelectServiceProvider
{
    private DisplayToGetUserChoice objGetUserChoice = null;
    private DisplayServiceProviderInfoUI objDisplayServiceProvider = null;
    private BookServiceProvider objBookProvider = null;

    public void getSelectedServiceProvider(Map<String, Map<String, String>> mapOfDataFromDatabase)
    {
        objGetUserChoice = new DisplayToGetUserChoice();
        int userSelectedForServiceProvider = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the Id of the service provider you want to select: ");

        Map<String, String> objSelectedProviderInfo = mapOfDataFromDatabase.get(String.valueOf(userSelectedForServiceProvider));
        selectFromProvidedOptions(String.valueOf(userSelectedForServiceProvider), objSelectedProviderInfo);
    }

    public void selectFromProvidedOptions(String serviceProviderID, Map<String,String> selectedServiceProvider)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objDisplayServiceProvider.displayServiceProviderAllInfo(serviceProviderID, selectedServiceProvider);

            String choiceToSelect = objGetUserChoice.displayMessageGetStringChoiceFromUser("Do you want to select this service provider [Y/N]: ");


            Map<String,String> mapCustomerSession = new HashMap<String,String>();

            if(choiceToSelect.equalsIgnoreCase("Y"))
            {
                String selectedServiceProverID = serviceProviderID;
//                objBookProvider = new BookServiceProvider();
//                objBookProvider.bookServiceProvider(mapCustomerSession,selectedServiceProverID);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            sc.close();
        }
    }
}