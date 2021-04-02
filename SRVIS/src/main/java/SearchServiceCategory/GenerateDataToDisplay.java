package SearchServiceCategory;

import enums.EnumServiceCategory;
import java.util.HashMap;
import java.util.Map;

public class GenerateDataToDisplay implements IGenerateDataToDisplay
{
    public Map<Integer,String> generateServiceCategoryData()
    {
        Map<Integer,String> mapSearchCategories = new HashMap<Integer,String>();

        String questionMessage = "What service are you looking for:";
        mapSearchCategories.put(0,questionMessage);
        mapSearchCategories.put(1,"1."+ EnumServiceCategory.Electrician);
        mapSearchCategories.put(2,"2."+ EnumServiceCategory.Plumber);
        mapSearchCategories.put(3,"3."+ EnumServiceCategory.Carpenter);
        mapSearchCategories.put(4,"4."+ EnumServiceCategory.Painter);
        mapSearchCategories.put(5,"5."+ EnumServiceCategory.Cleaner);

        return mapSearchCategories;
    }
}