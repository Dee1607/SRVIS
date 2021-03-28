package presantationlayer;

import java.util.Map;
import java.util.Scanner;

public class DisplayServiceCategoriesUI {

    public void displayServiceCategory(Map<Integer,String> mapOfServiceCategory)
    {
        System.out.println("=========================================================================");
        for(int i : mapOfServiceCategory.keySet())
        {
            System.out.println(mapOfServiceCategory.get(i));
        }
        System.out.println("=========================================================================");
    }
}