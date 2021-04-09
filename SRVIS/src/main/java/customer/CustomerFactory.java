package customer;

import presentationlayer.IDisplayToGetUserChoice;
import java.util.Map;

public class CustomerFactory
{
    public IAcceptedCustomer AcceptCustomer(String customerID)
    {
            return new AcceptedCustomer(customerID);
    }
    public IAcceptedCustomerDAO AcceptedCustomerDAO()
    {
        return new AcceptedCustomerDAO();
    }
    public IBookServiceProvider BookServiceProvider(Map<String, String> customer_session, IDisplayToGetUserChoice display)
    {
        return new BookServiceProvider(customer_session,display);
    }
    public IBookServiceProviderDAO BookServiceProviderDAO()
    {
        return new BookServiceProviderDAO();
    }
    public IGenerateDataToDisplay GenerateDataToDisplay()
    {
        return new GenerateDataToDisplay();
    }
    public ISelectServiceCategory SelectServiceCategory(Map<String,String> customerSession)
    {
        return new SelectServiceCategory(customerSession);
    }
    public ISelectServiceProvider SelectServiceProvider(Map<String,String> customerSession)
    {
        return new SelectServiceProvider(customerSession);
    }
    public IServiceProviderDAO ServiceProviderDAO()
    {
        return new ServiceProviderDAO();
    }
}