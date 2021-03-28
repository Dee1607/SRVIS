package SearchServiceCategory;

import SearchServiceProvider.SelectServiceProvider;
import database.ConnectionToDB;
import database.DatabaseQuery;
import enums.EnumServiceCategory;
import presentationlayer.DisplayServiceCategoriesUI;
import presentationlayer.DisplayServiceProviderInfoUI;

import java.sql.SQLException;
import java.util.*;

public class SelectServiceCategory
{
    Map<String,Map<String,String>> CUSTOMER_SESSION ;
    DisplayServiceCategoriesUI objDisplayServiceCategory = null;
    GenerateDataToDisplay objGenerateServiceCategoryData = null;
    ConnectionToDB objConnect = null;
    DatabaseQuery objQuery = null;

    public SelectServiceCategory(Map<String,Map<String,String>> customerSession)
    {
        this.CUSTOMER_SESSION = customerSession;
    }

    public void searchService() {
        objConnect = new ConnectionToDB();

        objGenerateServiceCategoryData = new GenerateDataToDisplay();
        Map<Integer,String> mapSearchCategories = objGenerateServiceCategoryData.generateServiceCategoryData();

        objDisplayServiceCategory = new DisplayServiceCategoriesUI();
        objDisplayServiceCategory.displayServiceCategory(mapSearchCategories);

        presantationlayer.DisplayToGetUserChoice objGetUserChoice = new presantationlayer.DisplayToGetUserChoice();
        int userSelectedServiceCategory = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the number of Service You need: ");
        EnumServiceCategory enumObjectOfChoice = EnumServiceCategory.values()[userSelectedServiceCategory - 1];

        try
        {
            objQuery = new DatabaseQuery(objConnect.getConnection());
            Map<String, Map<String, String>> mapOfDataFromDatabase = objQuery.selectQuery("SELECT * FROM CSCI5308_3_DEVINT.service_provider where spJobType = '"
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
                objConnect.closeConnection();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}