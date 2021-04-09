package controller;

import customer.*;
import login.LoginService;
import presentationlayer.DisplayLoginUI;
import presentationlayer.DisplayServiceProviderUI;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
import registration.IRegistrationMain;
import registration.IValidation;
import registration.RegistrationMain;
import registration.Validation;
import java.util.Map;

public class ApplicationController implements IApplicationController
{
    public IRegistrationMain registerObj;
    private IValidation validate;
    private ISelectServiceCategory objServiceCategory = null;
    private DisplayServiceProviderUI serviceProvider = null;
    private LoginService objLoginService = null;
    private Map<String, String> SESSION_DETAILS = null;
    private SelectServiceProvider objSelectedServiceProvider = null;
    private IBookServiceProvider objBookServiceProvider = null;
    private IDisplayToGetUserChoice display = null;
    private DisplayLoginUI login = null;

    public ApplicationController(IDisplayToGetUserChoice objToDisplay)
    {
        this.validate = new Validation();
        this.objLoginService = new LoginService();
        this.display = objToDisplay;
        this.login = new DisplayLoginUI(objToDisplay);
    }

    public void initializeApplication()
    {
        try
        {
            int userChoice = login.showLoginScreen();
            if (userChoice == 1)
            {
                Map<String, String> mapLoginData = login.userLogin();
                String email = mapLoginData.get("email");
                String password = mapLoginData.get("password");
                String type = mapLoginData.get("type");

                if (validate.isValidString("^\\w{1,}@[\\w+]+.\\w+", email))
                {
                    Map<String, String> tempValues = objLoginService.loginUser(email, password, type);

                    SESSION_DETAILS = tempValues;

                    display.displayMessage("1.Show Pending Requests\n2.Search Service");
                    int cChoice = display.displayMessageGetNumberChoiceFromUser("Select your choice:");

                    if(cChoice == 1)
                    {
                        display.displayMessage("All the pending requests in your queue.!!!!");
                        login.showPendingRequest(mapLoginData.get("email"), mapLoginData.get("type"));
                    }

                    if (mapLoginData.get("type").equalsIgnoreCase("c"))
                    {
                        objServiceCategory = new SelectServiceCategory(tempValues);
                        EnumServiceCategory enumChoice = objServiceCategory.getUserSelectedService();

                        objSelectedServiceProvider = new SelectServiceProvider(SESSION_DETAILS);
                        Map<String, Map<String, String>> mapServiceProvider = objSelectedServiceProvider.getServiceProvidersOfSelectedCategory(enumChoice);
                        int userSelectedServiceProvider = objSelectedServiceProvider.selectFromAvailableServiceProvider(mapServiceProvider);

                        objBookServiceProvider = new BookServiceProvider(SESSION_DETAILS,display);
                        Map<String, String> objSelectedProviderInfo = mapServiceProvider.get(String.valueOf(userSelectedServiceProvider));

                        boolean isSelected = objBookServiceProvider.finalizeServiceProvider(String.valueOf(userSelectedServiceProvider), objSelectedProviderInfo);

                        if (isSelected) {
                            Map<String, String> mapServiceProviderToBook = objBookServiceProvider.getAdditionalDetailsToBookServiceProvider(objSelectedProviderInfo);
                            objBookServiceProvider.generateBookingRequest(mapServiceProviderToBook);
                        }

                    }
                    else if (mapLoginData.get("type").equalsIgnoreCase("sp"))
                    {
                        display.displayMessage("All the pending requests in your queue.!!!!");
                        login.showPendingRequest(mapLoginData.get("email"), mapLoginData.get("type"));

                        serviceProvider = new DisplayServiceProviderUI(tempValues, display);
                        Map<String, String> serviceProviderSession = serviceProvider.getActiveServiceProvider();
                        boolean onlineStatus = serviceProvider.showAvailability(email);
                        if (onlineStatus) {
                            serviceProvider.getJobRequests();
                        }
                        serviceProvider.bookingOperation(serviceProviderSession);
                    }
                    else
                    {
                        display.displayMessage("Please enter valid option for the type");
                    }
                }
                else
                {
                    display.displayMessage("Please enter valid email-id or Password !!!!");
                }
            }
            else if (userChoice == 2)
            {
                registerObj = new RegistrationMain(new DisplayToGetUserChoice());
                registerObj.register();
            }
            else
            {
                display.displayMessage("Please enter valid input .");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}