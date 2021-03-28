package bookingServiceProvider;

import database.ConnectionToDB;
import database.DatabaseQuery;
import presantationlayer.DisplayToGetUserChoice;
import presentationlayer.DisplayUpdates;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookServiceProvider {
    DisplayToGetUserChoice objToDisplayData = null;
    ConnectionToDB objDatabaseConnection = null;
    DisplayUpdates objDisplayMessage = null;

    public void bookServiceProvider(Map<String,Map<String,String>> customerSessionDetails, String selectedServiceProviderID ,Map<String,String> selectedServiceProvider){
        objToDisplayData = new DisplayToGetUserChoice();

        String descriptionOfWork = objToDisplayData.displayMessageGetStringChoiceFromUser("Give some brief information on the work needs to be done: ");

        String query1= " insert into CSCI5308_3_DEVINT.service_request (customer_id, service_provider_id, service_request_date, service_request_category_id,service_request_description)"
                + " values (?, ?, ?, ?, ?)";

        Calendar calendar = Calendar.getInstance();
        java.sql.Date bookingDate = new java.sql.Date(calendar.getTime().getTime());

        String customerUniqueID = null;
        Map<String,String> insertData = new HashMap<>();
        for(String sessionCustomerID : customerSessionDetails.keySet()){
            customerUniqueID = sessionCustomerID;
        }
        insertData.put("customer_id",customerUniqueID);
        insertData.put("service_provider_id",selectedServiceProviderID);
        insertData.put("service_request_date",bookingDate.toString());
        insertData.put("service_request_category_id",selectedServiceProvider.get("spServiceCategoryID"));
        insertData.put("service_request_description",descriptionOfWork);

        try{
            objDatabaseConnection = new ConnectionToDB();
            DatabaseQuery objQuery = new DatabaseQuery(objDatabaseConnection.getConnection());
            objDisplayMessage = new DisplayUpdates();
            boolean insertStatus = objQuery.insertQuery(query1,insertData);

            if(insertStatus)
            {
                objDisplayMessage.displayMessage("Ticket Has been Generated.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}