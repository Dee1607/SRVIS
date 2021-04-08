package CustomerDetails;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import presentationlayer.DisplayServiceProviderInfoUI;

import java.util.HashMap;
import java.util.Map;

public class AcceptedCustomerDAO implements IAcceptedCustomerDAO{
    private String insertQuery;
    public boolean insertStatus;
    IDatabaseConnection db;

    public AcceptedCustomerDAO(){
        db = DatabaseConnection.databaseInstance();
    }

    public Map<String, Map<String,String>> getConnection(String customerID)
    {
        Map<String, Map<String,String>> queryResult = null;
        Map<String, Map<String,String>> queryResult1 = null;
        Map<String, String> getSelectedUserData1;
        try
        {
            db.makeConnection();
            String selectQuery = "SELECT * FROM customer WHERE customer_id='"+ customerID +"'";
            queryResult= db.selectQuery(selectQuery);
            db.makeConnection();
            String selectQuery1 = "SELECT * FROM payment WHERE sender_id='"+ customerID +"'";
            queryResult1= db.selectQuery(selectQuery1);
            queryResult1.get("amount");
            for (String keys : queryResult1.keySet()) {
                getSelectedUserData1 = queryResult1.get(keys);
                System.out.println(getSelectedUserData1.get("amount"));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        } finally {
            try{
                db.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return queryResult;
    }
}
