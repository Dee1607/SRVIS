package SearchServiceCategory;

import SearchServiceProvider.SelectServiceProvider;
import enums.EnumServiceCategory;
import presentationlayer.DisplayServiceCategoriesUI;
import presentationlayer.DisplayToGetUserChoice;
import java.util.*;

public class SelectServiceCategory
{
    private Map<String,String> CUSTOMER_SESSION ;

    public SelectServiceCategory(Map<String,String> customerSession)
    {
        this.CUSTOMER_SESSION = customerSession;
    }

    public void getUserSelectedService()
    {
        DisplayServiceCategoriesUI objDisplayServiceCategory = null;
        GenerateDataToDisplay objGenerateServiceCategoryData = null;
        DisplayToGetUserChoice objGetUserChoice = null;
        int userSelectedServiceCategory = -1;
        EnumServiceCategory enumObjectOfChoice = null;
        SelectServiceProvider objSelectedServiceProvider = null;
        Map<Integer,String> mapSearchCategories = null;

        objGenerateServiceCategoryData = new GenerateDataToDisplay();
        mapSearchCategories = objGenerateServiceCategoryData.generateServiceCategoryData();

        objDisplayServiceCategory = new DisplayServiceCategoriesUI();
        objDisplayServiceCategory.displayServiceCategory(mapSearchCategories);

        objGetUserChoice = new DisplayToGetUserChoice();
        userSelectedServiceCategory = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the number of Service You need: ");
        enumObjectOfChoice = EnumServiceCategory.values()[userSelectedServiceCategory - 1];

        objSelectedServiceProvider = new SelectServiceProvider(CUSTOMER_SESSION);
        objSelectedServiceProvider.getServiceProvidersOfSelectedCategory(enumObjectOfChoice);
    }
}