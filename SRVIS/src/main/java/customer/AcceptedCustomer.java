package customer;

import presentationlayer.DisplayServiceProviderInfoUI;
import java.util.Map;

public class AcceptedCustomer implements IAcceptedCustomer
{
    public String customerID;
    IAcceptedCustomerDAO connectDB;
    DisplayServiceProviderInfoUI objDisplay;

    public AcceptedCustomer(String customerID)
    {
        this.customerID = customerID;
        connectDB = new AcceptedCustomerDAO();
        objDisplay = new DisplayServiceProviderInfoUI();
    }

    public void customerDetails()
    {
        Map<String, String> getDetails = connectDB.getConnection(customerID);
        objDisplay.displayCustomerAllInfo(getDetails);
    }
}