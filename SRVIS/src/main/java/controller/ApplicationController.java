package controller;

import customer.*;
import enums.EnumServiceCategory;
import login.LoginService;
import presentationlayer.LoginUI;
import presentationlayer.ServiceProviderCustomerUI;
import registration.IRegistrationMain;
import registration.IValidation;
import registration.Validation;

import java.util.Map;

public class ApplicationController
{
    private IRegistrationMain registerObj=null;
    private IValidation validate;
    private ISelectServiceCategory objServiceCategory = null;
    private ServiceProviderCustomerUI serviceProvider = null;
    private LoginService objLoginService = null;
    private Map<String,String> SESSION_DETAILS = null;
    private SelectServiceProvider objSelectedServiceProvider = null;
    IBookServiceProvider objBookServiceProvider = null;

    public ApplicationController(){
        validate = new Validation();
        objLoginService = new LoginService();
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

                if(validate.isValidString("^\\w{1,}@[\\w+]+.\\w+",mapLoginData.get("email")))
                {
                    Map<String,String> tempValues =  objLoginService.loginUser(mapLoginData.get("email"), mapLoginData.get("password"), mapLoginData.get("type"));

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
                    } else {
                        // **********************
                        // SP CODE LEFT TO MERGE
                        // *********************
                        serviceProvider = new ServiceProviderCustomerUI(tempValues);
                        serviceProvider.showCustomerRequestUI();
                    }
                    System.out.println("All the pending requests in your queue.!!!!");
                    login.showPendingRequest(mapLoginData.get("email"), mapLoginData.get("type"));
                }
                else
                {
                    System.out.println("Please enter valid email-id or Password !!!!");
                }
            }
            else if (userChoice == 2)
            {
                registerObj.register();
            } else {
                System.out.println("Please enter valid input .");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}