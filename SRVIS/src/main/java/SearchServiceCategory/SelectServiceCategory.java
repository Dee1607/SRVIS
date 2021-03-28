package SearchServiceCategory;

import SearchServiceProvider.SelectServiceProvider;
import database.Database;
import enums.EnumServiceCategory;
import presentationlayer.DisplayServiceCategoriesUI;
import presentationlayer.DisplayServiceProviderInfoUI;
import presentationlayer.DisplayToGetUserChoice;

import java.sql.SQLException;
import java.util.*;

public class SelectServiceCategory
{
    Map<String,Map<String,String>> CUSTOMER_SESSION ;
    DisplayServiceCategoriesUI objDisplayServiceCategory = null;
    GenerateDataToDisplay objGenerateServiceCategoryData = null;
    Database db= Database.databaseInstance();

    public SelectServiceCategory(Map<String,Map<String,String>> customerSession)
    {
        this.CUSTOMER_SESSION = customerSession;
    }

    public void searchService() {

        objGenerateServiceCategoryData = new GenerateDataToDisplay();
        Map<Integer,String> mapSearchCategories = objGenerateServiceCategoryData.generateServiceCategoryData();

        objDisplayServiceCategory = new DisplayServiceCategoriesUI();
        objDisplayServiceCategory.displayServiceCategory(mapSearchCategories);

        DisplayToGetUserChoice objGetUserChoice = new DisplayToGetUserChoice();
        int userSelectedServiceCategory = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the number of Service You need: ");
        EnumServiceCategory enumObjectOfChoice = EnumServiceCategory.values()[userSelectedServiceCategory - 1];

        try
        {
            db.makeConnection();
            Map<String, Map<String, String>> mapOfDataFromDatabase = db.selectQuery("SELECT * FROM CSCI5308_3_DEVINT.service_provider where spJobType = '"
                    + enumObjectOfChoice.toString() + "'");

            DisplayServiceProviderInfoUI objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objDisplayServiceProvider.displayServiceProviderBriefInfo(mapOfDataFromDatabase);

            SelectServiceProvider objServiceProviderInfo = new SelectServiceProvider();
            objServiceProviderInfo.getSelectedServiceProvider(mapOfDataFromDatabase,CUSTOMER_SESSION);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                db.closeConnection();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}