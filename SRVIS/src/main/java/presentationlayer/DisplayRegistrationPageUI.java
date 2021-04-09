package presentationlayer;

import encryptionservice.EncryptionService;
import encryptionservice.IEncryptionService;
import registration.IValidation;
import registration.Validation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DisplayRegistrationPageUI
{
    IValidation validate = null;
    DisplayServiceCategoriesUI displayData;
    IEncryptionService encryptSecurity = null;

    public DisplayRegistrationPageUI()
    {
        validate = new Validation();
        displayData = new DisplayServiceCategoriesUI();
        encryptSecurity = new EncryptionService();
    }

    public ArrayList<String> getUserDetails(String methodName, String pattern)
    {
        ArrayList<String> result = new ArrayList<>();
        try
        {
            System.out.println("Enter " + methodName);
            Scanner sc = new Scanner(System.in);
            String value = sc.nextLine();
            boolean validation = validate.isValidString(pattern, value);

            if(methodName=="new password(only characters and numbers are allowed)")
            {
                ArrayList<String> getEncryptedValue = encryptSecurity.encryptDataForSecurity(value);
                value = getEncryptedValue.get(0);
            }

            if (validation == true)
            {
                result.add(validation + "-" + value);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return result;
    }

    public ArrayList<String> getJobType(String pattern)
    {
        ArrayList<String> result = new ArrayList<>();
        try
        {
            System.out.println("Select the type of job.");
            HashMap<Integer, String> getType = new HashMap<>();
            getType.put(1, "Electrician");
            getType.put(2, "Plumber");
            getType.put(3, "Carpenter");
            getType.put(4, "Painter");
            getType.put(5, "Cleaner");

            for (Integer i : getType.keySet())
            {
                System.out.println(i + " " + getType.get(i));
            }

            Scanner sc = new Scanner(System.in);
            String jobType = sc.nextLine();
            boolean validation = validate.isValidString(pattern, jobType);
            if (validation == true)
            {
                Integer intJobType = Integer.valueOf(jobType);
                result.add(validation + "-" + getType.get(intJobType));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
}