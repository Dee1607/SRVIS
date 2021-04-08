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
    DisplayServiceProviderInfoUI objDisplay;

    public AcceptedCustomer(String customerID) {
        this.customerID = customerID;
        connectDB = new AcceptedCustomerDAO();
        objDisplay = new DisplayServiceProviderInfoUI();
    }

    public void customerDetails() {
        Map<String, String> getDetails = connectDB.getConnection(customerID);
        objDisplay.displayCustomerAllInfo(getDetails);
    }
}

