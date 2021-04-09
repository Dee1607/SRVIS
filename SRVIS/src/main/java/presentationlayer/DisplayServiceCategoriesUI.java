package presentationlayer;

import java.util.Map;

public class DisplayServiceCategoriesUI
{
    public void displayServiceCategory(Map<Integer, String> mapOfServiceCategory)
    {
        System.out.println("=========================================================================");
        for (Integer tempNumber : mapOfServiceCategory.keySet())
        {
            System.out.println(mapOfServiceCategory.get(tempNumber));
        }
        System.out.println("=========================================================================");
    }
}