package bookingserviceprovider;

import database.Database;
import presentationlayer.DisplayServiceProviderInfoUI;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.DisplayUpdates;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookServiceProvider
{
    private Map<String,String> CUSTOMER_SESSION;

    public BookServiceProvider(Map<String, String> customer_session) {
        this.CUSTOMER_SESSION = customer_session;
    }

    public boolean finalizeServiceProvider(String serviceProviderID, Map<String,String> selectedServiceProvider)
    {
        DisplayServiceProviderInfoUI objDisplayServiceProvider;
        DisplayToGetUserChoice objGetUserChoice = null;
        Scanner sc = new Scanner(System.in);
        boolean isSelected = false;

        try
        {
            objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objGetUserChoice = new DisplayToGetUserChoice();
            objDisplayServiceProvider.displayServiceProviderAllInfo(serviceProviderID, selectedServiceProvider);

            String choiceToSelect = objGetUserChoice.displayMessageGetStringChoiceFromUser("Do you want to select this service provider [Y/N]: ");

            if(choiceToSelect.equalsIgnoreCase("Y"))
            {
                getAdditionalDetailsToBookServiceProvider(selectedServiceProvider);
                isSelected = true;
            }
            return isSelected;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return isSelected;
        }
        finally
        {
            sc.close();
        }
    }

    public void getAdditionalDetailsToBookServiceProvider(Map<String,String> selectedServiceProvider){

        DisplayToGetUserChoice objToDisplayData;

        objToDisplayData = new DisplayToGetUserChoice();
        String descriptionOfWork = objToDisplayData.displayMessageGetStringChoiceFromUser("Give some brief information on the work needs to be done: ");

        Calendar calendar = Calendar.getInstance();
        java.sql.Date bookingDate = new java.sql.Date(calendar.getTime().getTime());

        Map<String,String> insertData = new HashMap<>();

        insertData.put("customer_id",CUSTOMER_SESSION.get("customerID"));
        insertData.put("service_provider_id",selectedServiceProvider.get("service_provider_id"));
        insertData.put("service_request_date",bookingDate.toString());
        insertData.put("service_request_category_id",selectedServiceProvider.get("spServiceCategoryID"));
        insertData.put("service_request_description",descriptionOfWork);

        generateBookingRequest(insertData);
    }

    public boolean generateBookingRequest(Map<String,String> dataToInsert){

        DisplayUpdates objDisplayMessage;
        Database db= Database.databaseInstance();

        try
        {
            String query1= " insert into CSCI5308_3_DEVINT.service_request (customer_id, service_provider_id, service_request_date, service_request_category_id,service_request_description)"
                    + " values (?, ?, ?, ?, ?)";

            objDisplayMessage = new DisplayUpdates();
            db.makeConnection();
            boolean insertStatus = db.insertQuery(query1,dataToInsert);

            if(insertStatus)
            {
                objDisplayMessage.displayMessage("Ticket Has been Generated.");
            }

            return insertStatus;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        } finally {
            try{
                db.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}