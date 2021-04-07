package CustomerDetails;

import database.DatabaseConnection;
import database.IDatabaseConnection;

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
        try
        {
            db.makeConnection();
            String selectQuery = "SELECT * FROM customer WHERE customer_id='"+ customerID +"'";
            queryResult= db.selectQuery(selectQuery);
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
