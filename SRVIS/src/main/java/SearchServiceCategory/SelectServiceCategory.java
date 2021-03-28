package SearchServiceCategory;

import SearchServiceProvider.SelectServiceProvider;
import database.ConnectionToDB;
import enums.EnumServiceCategory;
import presantationlayer.*;
import java.sql.SQLException;
import java.util.*;

public class SelectServiceCategory
{
    DisplayServiceCategoriesUI objDisplayServiceCategory = null;
    GenerateDataToDisplay objGenerateServiceCategoryData = null;
    ConnectionToDB objConnect = null;

    public void searchService() {
        objConnect = new ConnectionToDB();

        objGenerateServiceCategoryData = new GenerateDataToDisplay();
        Map<Integer,String> mapSearchCategories = objGenerateServiceCategoryData.generateServiceCategoryData();

        objDisplayServiceCategory = new DisplayServiceCategoriesUI();
        objDisplayServiceCategory.displayServiceCategory(mapSearchCategories);

        DisplayToGetUserChoice objGetUserChoice = new DisplayToGetUserChoice();
        int userSelectedServiceCategory = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the number of Service You need: ");
        EnumServiceCategory enumObjectOfChoice = EnumServiceCategory.values()[userSelectedServiceCategory - 1];

        try
        {
            Map<String, Map<String, String>> mapOfDataFromDatabase = objConnect.getDataFromDatabase("SELECT * FROM CSCI5308_3_DEVINT.service_provider where spJobType = '"
                    + enumObjectOfChoice.toString() + "'");

            DisplayServiceProviderInfoUI objDisplayServiceProvider = new DisplayServiceProviderInfoUI();
            objDisplayServiceProvider.displayServiceProviderBriefInfo(mapOfDataFromDatabase);

            SelectServiceProvider objServiceProviderInfo = new SelectServiceProvider();
            objServiceProviderInfo.getSelectedServiceProvider(mapOfDataFromDatabase);
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