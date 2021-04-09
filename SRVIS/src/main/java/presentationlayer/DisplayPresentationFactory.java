package presentationlayer;

import java.util.Map;

public class DisplayPresentationFactory
{
    private static DisplayPresentationFactory presentationFactory;
    public static DisplayPresentationFactory presentationInstance()
    {
        presentationFactory = new DisplayPresentationFactory();
        return presentationFactory;
    }

    public DisplayServiceCategoriesUI DisplayServiceCategoriesUI()
    {
        return new DisplayServiceCategoriesUI();
    }

    public DisplayServiceProviderInfoUI DisplayServiceProviderInfoUI()
    {
        return new DisplayServiceProviderInfoUI();
    }

    public DisplayToGetUserChoice DisplayToGetUserChoice()
    {
        return new DisplayToGetUserChoice();
    }

    public DisplayLoginUI LoginUI(IDisplayToGetUserChoice display)
    {
        return new DisplayLoginUI(display);
    }

    public DisplayRegistrationPageUI RegistrationPageUI()
    {
        return new DisplayRegistrationPageUI();
    }

    public DisplayServiceProviderUI ServiceProviderCustomerUI(Map<String, String> loginUser, IDisplayToGetUserChoice display)
    {
        return new DisplayServiceProviderUI(loginUser, display);
    }
}