package controller;

import customer.*;
import enums.EnumServiceCategory;
import login.LoginService;
import presentationlayer.DisplayToGetUserChoice;
import presentationlayer.IDisplayToGetUserChoice;
import presentationlayer.LoginUI;
import presentationlayer.ServiceProviderCustomerUI;
import registration.IRegistrationMain;
import registration.IValidation;
import registration.Validation;
import java.util.Map;

public class ApplicationController implements IApplicationController
{
    private IRegistrationMain registerObj=null;
    private IValidation validate;
    private ISelectServiceCategory objServiceCategory = null;
    private ServiceProviderCustomerUI serviceProvider = null;
    private LoginService objLoginService = null;
    private Map<String,String> SESSION_DETAILS = null;
    private SelectServiceProvider objSelectedServiceProvider = null;
    private IBookServiceProvider objBookServiceProvider = null;
    private IDisplayToGetUserChoice display = null;

    public ApplicationController(IDisplayToGetUserChoice objToDisplay)
    {
        this.validate = new Validation();
        this.objLoginService = new LoginService();
        this.display = objToDisplay;
    }

    public void initializeApplication()
    {
        try
        {
            LoginUI login = new LoginUI();
            int userChoice = login.showLoginScreen();

            if (userChoice == 1)
            {
                Map<String,String> mapLoginData = login.userLogin();
                String email=mapLoginData.get("email");
                String password=mapLoginData.get("password");
                String type=mapLoginData.get("type");

                if(validate.isValidString("^\\w{1,}@[\\w+]+.\\w+",email))
                {
                    Map<String,String> tempValues =  objLoginService.loginUser(email,password,type);

                    SESSION_DETAILS = tempValues;

                    if (mapLoginData.get("type").equalsIgnoreCase("c"))
                    {
                        objServiceCategory = new SelectServiceCategory(tempValues);
                        EnumServiceCategory enumChoice= objServiceCategory.getUserSelectedService();

                        objSelectedServiceProvider = new SelectServiceProvider(SESSION_DETAILS);
                        Map<String,Map<String,String>> mapServiceProvider = objSelectedServiceProvider.getServiceProvidersOfSelectedCategory(enumChoice);
                        int userSelectedServiceProvider = objSelectedServiceProvider.selectFromAvailableServiceProvider(mapServiceProvider);

                        objBookServiceProvider = new BookServiceProvider(SESSION_DETAILS);
                        Map<String, String> objSelectedProviderInfo = mapServiceProvider.get(String.valueOf(userSelectedServiceProvider));

                        boolean isSelected = objBookServiceProvider.finalizeServiceProvider(String.valueOf(userSelectedServiceProvider),objSelectedProviderInfo);

                        if(isSelected){
                            Map<String,String> mapServiceProviderToBook = objBookServiceProvider.getAdditionalDetailsToBookServiceProvider(objSelectedProviderInfo);
                            objBookServiceProvider.generateBookingRequest(mapServiceProviderToBook);

                        }
                    }else if (mapLoginData.get("type").equalsIgnoreCase("sp")){
                        // **********************
                        // SP CODE LEFT TO MERGE
                        // *********************

                        serviceProvider = new ServiceProviderCustomerUI(tempValues,display);
                        Map<String,String> serviceProviderSession =serviceProvider.getActiveServiceProvider();
                        boolean onlineStatus=serviceProvider.showAvailability(email);
                        if(onlineStatus) {
                            serviceProvider.getJobRequests();
                        }
                        serviceProvider.bookingOperation(serviceProviderSession);
                    }else
                    {
                        display.displayMessage("Please enter valid option for the type");
                    }
                    display.displayMessage("All the pending requests in your queue.!!!!");
                    login.showPendingRequest(mapLoginData.get("email"), mapLoginData.get("type"));
                }
                else
                {
                    display.displayMessage("Please enter valid email-id or Password !!!!");
                }
            }
            else if (userChoice == 2)
            {
                registerObj.register();
            } else {
                display.displayMessage("Please enter valid input .");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}