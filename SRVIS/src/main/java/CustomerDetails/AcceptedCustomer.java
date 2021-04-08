package CustomerDetails;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import presentationlayer.DisplayServiceProviderInfoUI;

import java.util.HashMap;
import java.util.Map;


public class AcceptedCustomer implements IAcceptedCustomer {
    public String customerID;
    IAcceptedCustomerDAO connectDB;
    Map<String, String> getSelectedUserData;

    public AcceptedCustomer(String customerID) {
        this.customerID = customerID;
        connectDB = new AcceptedCustomerDAO();
    }

    public void customerDetails() {
        Map<String, Map<String, String>> getDetails = connectDB.getConnection(customerID);
        for (String keys : getDetails.keySet()) {
            getSelectedUserData = getDetails.get(keys);
            DisplayServiceProviderInfoUI obj = new DisplayServiceProviderInfoUI();
            obj.displayCustomerAllInfo(getSelectedUserData);
        }
    }
}

