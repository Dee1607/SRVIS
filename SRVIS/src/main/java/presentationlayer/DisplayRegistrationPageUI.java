package presentationlayer;

import registration.IValidation;
import registration.Validation;

import java.util.HashMap;
import java.util.Scanner;

public class DisplayRegistrationPageUI
{
    IValidation validate=null;
    DisplayServiceCategoriesUI displayData;

    public DisplayRegistrationPageUI()
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

            for(Integer i: getType.keySet())
            {
                System.out.println (i + " " + getType.get(i));
            }

            Scanner sc = new Scanner(System.in);
            String jobType = sc.nextLine();
            boolean validation = validate.isValidString(pattern, jobType);
            if (validation == true){
                Integer intJobType = Integer.valueOf(jobType);
                return "Success-" + getType.get(intJobType);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "fail";
    }
}