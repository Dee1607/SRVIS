package customer;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AcceptedCustomerDAO implements IAcceptedCustomerDAO {
    IDatabaseConnection db;

    public AcceptedCustomerDAO()
    {
        db = DatabaseConnection.databaseInstance();
    }

    public Map<String, String> getConnection(String customerID)
    {
        ArrayList<String> queryList = new ArrayList<>();
        queryList.add("SELECT * FROM customer WHERE customer_id='" + customerID + "'");
        queryList.add("SELECT * FROM payment WHERE sender_id='" + customerID + "'");
        Map<String, Map<String, String>> queryResult = null;
        Map<String, Map<String, String>> customerDetails = new HashMap<>();
        Map<String, String> getAmount = null;
        Map<String, String> customerAmountDetails = null;

        try
        {
            for (String query : queryList)
            {
                db.makeConnection();
                queryResult = db.selectQuery(query);
                for (String keys : queryResult.keySet())
                {
                    if (customerID.equals(keys))
                    {
                        customerDetails = queryResult;
                    }
                    else
                    {
                        for (String geykey : queryResult.keySet())
                        {
                            getAmount = queryResult.get(geykey);
                            String amount = getAmount.get("amount");
                            for (String key : customerDetails.keySet())
                            {
                                customerAmountDetails = customerDetails.get(key);
                                customerAmountDetails.put("amount", amount);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                db.closeConnection();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return customerAmountDetails;
    }
}