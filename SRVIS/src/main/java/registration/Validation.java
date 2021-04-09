package registration;

import java.util.regex.Pattern;

public class Validation implements IValidation
{
    public boolean isValidString(String pattern, String userDetail)
    {
        String removeWhitespace = userDetail.replaceAll("\\s", "");
        if (removeWhitespace.isEmpty())
        {
            return false;
        }
        else
        {
            if (Pattern.matches(pattern, removeWhitespace))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}