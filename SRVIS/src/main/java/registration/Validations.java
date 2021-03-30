package registration;

import java.util.regex.*;

public class Validations {
    public boolean isValidName(String name)
    {
        if(name.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("[a-zA-Z]+", name))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidAddress(String address)
    {
        if(address.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("^[a-zA-Z0-9-/,]+$", address))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidContact(String contact)
    {
        if(contact.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("^[0-9]{10}$", contact))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidJobType(String jobType)
    {
        if(jobType.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("^[1-5]$", jobType))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidHourlyRate(String hourlyRate)
    {
        if(hourlyRate.isEmpty())
        {
            return false;
        }
        else

        {
            if(Pattern.matches("^[\\d]+$", hourlyRate))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidEmail(String emailId)
    {
        if(emailId.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("^[a-zA-Z0-9][-\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", emailId))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidExperience(String experience)
    {
        if(experience.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("^[0-9]+$", experience))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidDescription(String description)
    {
        if(description.isEmpty())
        {
            return false;
        }
        else
        {
            if(Pattern.matches("^[a-zA-Z0-9]+$", description))
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean isValidPassword(String password)
    {
        return true;
    }
}