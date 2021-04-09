package customer;

import database.DatabaseConnection;
import presentationlayer.DisplayToGetUserChoice;
import java.sql.PreparedStatement;
import java.util.Map;

public class BookServiceProviderDAO implements IBookServiceProviderDAO{

    DisplayToGetUserChoice objDisplayMessage;
    DatabaseConnection db= DatabaseConnection.databaseInstance();

    public BookServiceProviderDAO()
    {
        objDisplayMessage = new DisplayToGetUserChoice();
    }

    public boolean setBookingRequest(Map<String,String> dataToInsert)
    {
        PreparedStatement preparedStmt = null;
        boolean insertStatus = false;

        String query1= " insert into `service_request` (`customer_id`, `service_provider_id` , `service_request_date` , `service_request_category_id` , `service_request_description` )"
                + " values (?, ?, ?, ?, ?)";
        try
        {
            preparedStmt = db.makeConnection().prepareStatement(query1);
            preparedStmt.setInt (1, Integer.parseInt(dataToInsert.get("customer_id")));
            preparedStmt.setInt (2, Integer.parseInt(dataToInsert.get("service_provider_id")));
            preparedStmt.setDate(3, java.sql.Date.valueOf(dataToInsert.get("service_request_date")));
            preparedStmt.setInt (4, 333);
            preparedStmt.setString(5,(dataToInsert.get("service_request_description")));

            String str = preparedStmt.toString();
            String[] arrSplitString = str.split(":");
            objDisplayMessage = new DisplayToGetUserChoice();
            db.makeConnection();
            insertStatus = db.insertQuery(arrSplitString[1]);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return insertStatus;
    }
}