package customer;

import customer.*;
import enums.EnumServiceCategory;
import presentationlayer.DisplayServiceCategoriesUI;
import presentationlayer.DisplayToGetUserChoice;

import java.util.Map;

public class SelectServiceCategory implements ISelectServiceCategory
{
    private DisplayServiceCategoriesUI objDisplayServiceCategory = null;
    private GenerateDataToDisplay objGenerateServiceCategoryData = null;
    private DisplayToGetUserChoice objGetUserChoice = null;
    private EnumServiceCategory enumObjectOfChoice = null;
    private SelectServiceProvider objSelectedServiceProvider = null;

    private Map<Integer,String> mapSearchCategories = null;

    private Map<String,String> CUSTOMER_SESSION ;

    public SelectServiceCategory(Map<String,String> customerSession)
    {
        this.CUSTOMER_SESSION = customerSession;
    }

    public EnumServiceCategory getUserSelectedService()
    {
        int userSelectedServiceCategory = -1;

        try{
            objGenerateServiceCategoryData = new GenerateDataToDisplay();
            mapSearchCategories = objGenerateServiceCategoryData.generateServiceCategoryData();

            objDisplayServiceCategory = new DisplayServiceCategoriesUI();
            objDisplayServiceCategory.displayServiceCategory(mapSearchCategories);

            objGetUserChoice = new DisplayToGetUserChoice();
            userSelectedServiceCategory = objGetUserChoice.displayMessageGetNumberChoiceFromUser("Enter the number of Service You need: ");
            enumObjectOfChoice = EnumServiceCategory.values()[userSelectedServiceCategory - 1];

            return enumObjectOfChoice;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return enumObjectOfChoice;
    }
}