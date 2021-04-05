package presentationlayer;

import registration.IValidation;
import registration.Validation;

import java.util.*;

public class RegistrationPageUI
{
    IValidation validate=null;
    DisplayServiceCategoriesUI displayData;

    public RegistrationPageUI()
    {
        validate=new Validation();
        displayData=new DisplayServiceCategoriesUI();
    }

    public String getUserDetails(String methodName, String pattern) {
        try {
            System.out.println("Enter " + methodName);
            Scanner sc = new Scanner(System.in);
            String value = sc.nextLine();
            boolean validation = validate.isValidString(pattern, value);
            if (validation == true){
                return "Success-" + value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return "fail";
    }

    public String getJobType(String pattern)
    {
        try
        {
            System.out.println("Select the type of job.");
            HashMap<Integer, String> getType = new HashMap<>();
            getType.put(1,"Electrician");
            getType.put(2,"Plumber");
            getType.put(3,"Carpenter");
            getType.put(4,"Painter");
            getType.put(5,"Cleaner");

            displayData.displayServiceCategory(getType);

            Scanner sc = new Scanner(System.in);
            String jobType = sc.nextLine();
            boolean validation = validate.isValidString(pattern, jobType);
            Integer intJobType = Integer.valueOf(jobType);
            if (validation == true){
                return "Success-" + getType.get(intJobType);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 108");
        }
        return "fail";
    }
}