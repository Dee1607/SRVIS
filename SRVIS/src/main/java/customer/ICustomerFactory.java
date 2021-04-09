package customer;

import presentationlayer.IDisplayToGetUserChoice;

import java.util.Map;

public interface ICustomerFactory
{
    public IAcceptedCustomer AcceptCustomer(String customerID);
    public IAcceptedCustomerDAO AcceptedCustomerDAO();
    public IBookServiceProvider BookServiceProvider(Map<String, String> customer_session, IDisplayToGetUserChoice display);
    public IBookServiceProviderDAO BookServiceProviderDAO();
    public IGenerateDataToDisplay GenerateDataToDisplay();
    public ISelectServiceCategory SelectServiceCategory(Map<String,String> customerSession);
    public ISelectServiceProvider SelectServiceProvider(Map<String,String> customerSession);
    public IServiceProviderDAO ServiceProviderDAO();
}