package registration;

import java.util.regex.*;

public class Validations {
    public boolean isValidString(String pattern, String userDetail){
        String removeWhitespace = userDetail.replaceAll("\\s","");
        if(removeWhitespace.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches(pattern, removeWhitespace))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }
}