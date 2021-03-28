package bookingServiceProvider;

import database.ConnectionToDB;
import presantationlayer.DisplayToGetUserChoice;

import java.util.Map;

public class BookServiceProvider {
    DisplayToGetUserChoice objToDisplayData = null;
    ConnectionToDB objDatabaseConnection = null;

    public void bookServiceProvider(Map<String,Map<String,String>> customerSessionDetails, String selectedServiceProviderID){
        objToDisplayData = new DisplayToGetUserChoice();

        String descriptionOfWork = objToDisplayData.displayMessageGetStringChoiceFromUser("Give some brief information on the work needs to be done: ");

    }
}