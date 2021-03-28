package SearchServiceProvider;

import bookingServiceProvider.BookServiceProvider;
import presantationlayer.DisplayToGetUserChoice;
import presentationlayer.DisplayServiceProviderInfoUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SelectServiceProvider
{
    private DisplayToGetUserChoice objGetUserChoice = null;
    private DisplayServiceProviderInfoUI objDisplayServiceProvider = null;
    private BookServiceProvider objBookProvider = null;

    public void getSelectedServiceProvider(Map<String, Map<String, String>> mapOfDataFromDatabase,Map<String, Map<String, String>> customerSession)
    {
        objGetUserChoice = new DisplayToGetUserChoice();
        int userSelectedForServiceProvider = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the Id of the service provider you want to select: ");

        Map<String, String> objSelectedProviderInfo = mapOfDataFromDatabase.get(String.valueOf(userSelectedForServiceProvider));
        selectFromProvidedOptions(String.valueOf(userSelectedForServiceProvider), objSelectedProviderInfo,customerSession);
    }

    public void selectFromProvidedOptions(String serviceProviderID, Map<String,String> selectedServiceProvider,Map<String, Map<String, String>> customerSession)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objDisplayServiceProvider.displayServiceProviderAllInfo(serviceProviderID, selectedServiceProvider);

            String choiceToSelect = objGetUserChoice.displayMessageGetStringChoiceFromUser("Do you want to select this service provider [Y/N]: ");

            if(choiceToSelect.equalsIgnoreCase("Y"))
            {
                String selectedServiceProverID = serviceProviderID;
                objBookProvider = new BookServiceProvider();
                objBookProvider.bookServiceProvider(customerSession,serviceProviderID,selectedServiceProvider);
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