package presentationlayer;

import java.util.Map;

public class DisplayServiceCategoriesUI
{
    public void displayServiceCategory(Map<Integer,String> mapOfServiceCategory)
    {
        System.out.println("=========================================================================");
        for(int i : mapOfServiceCategory.keySet())
        {
            System.out.println(i + " " + mapOfServiceCategory.get(i));
        }
        System.out.println("=========================================================================");
    }
}