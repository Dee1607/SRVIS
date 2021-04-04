package presentationlayer;

import RegistrationDAO.ConnectDatabase;
import registration.Validations;
import java.util.*;

public class RegistrationPageUI
{
    Validations validations = new Validations();
    List<Integer> errorCodes = new ArrayList<>();
    HashMap<String, String> userDetails = new HashMap<>();

    public void getUserDetails(String str)
    {
        try
        {
            System.out.println(str);
            Scanner sc = new Scanner(System.in);
            String value = sc.nextLine();
            boolean validationResult = validations.isValidName(value);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void getFirstName()
    {
        try
        {
            System.out.println("Enter First Name");
            Scanner sc = new Scanner(System.in);
            String firstname = sc.nextLine();
            boolean validationResult = validations.isValidName(firstname);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(100);
                errorCodes.remove(integer);
                errorCodes.add(100);
            }
            else
            {
                userDetails.put("firstName",firstname);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(100);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 100");
        }
    }

    public void getLastName()
    {
        try
        {
            System.out.println("Enter Last Name");
            Scanner sc = new Scanner(System.in);
            String lName = sc.nextLine();
            boolean validationResult = validations.isValidName(lName);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(101);
                errorCodes.remove(integer);
                errorCodes.add(101);
            }
            else
            {
                userDetails.put("lastName",lName);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(101);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 101");
        }
    }

    public void getAddress()
    {
        try{
            System.out.println("Enter Address");
            Scanner sc = new Scanner(System.in);
            String address = sc.nextLine();
            boolean validationResult = validations.isValidAddress(address);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(102);
                errorCodes.remove(integer);
                errorCodes.add(102);
            }
            else
            {
                userDetails.put("address",address);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(102);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 102");
        }
    }

    public void getContact()
    {
        try
        {
            System.out.println("Enter contact number");
            Scanner sc = new Scanner(System.in);
            String contact = sc.nextLine();
            boolean validationResult = validations.isValidContact(contact);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(103);
                errorCodes.remove(integer);
                errorCodes.add(103);
            }
            else
            {
                userDetails.put("contact",contact);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(103);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 103");
        }
    }

    public void getEmail()
    {
        try
        {
            System.out.println("Enter email ID");
            Scanner sc = new Scanner(System.in);
            String emailId = sc.nextLine();
            boolean validationResult = validations.isValidEmail(emailId);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(104);
                errorCodes.remove(integer);
                errorCodes.add(104);
            }
            else
            {
                userDetails.put("email",emailId);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(104);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 104");
        }
    }

    public void getPassword()
    {
        try
        {
            System.out.println("Enter new password.");
            Scanner sc = new Scanner(System.in);
            String password = sc.nextLine();
            boolean validationResult = validations.isValidPassword(password);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(105);
                errorCodes.remove(integer);
                errorCodes.add(105);
            }
            else
            {
                userDetails.put("password",password);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(105);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 105");
        }
    }

    public void getExperience()
    {
        try
        {
            System.out.println("Enter years of experience in the field");
            Scanner sc = new Scanner(System.in);
            String experience = sc.nextLine();
            boolean validationResult = validations.isValidExperience(experience);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(106);
                errorCodes.remove(integer);
                errorCodes.add(106);
            }
            else
            {
                userDetails.put("experience",experience);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(106);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 106");
        }
    }

    public void getDescription()
    {
        try
        {
            System.out.println("Enter brief description related to profile");
            Scanner sc = new Scanner(System.in);
            String description = sc.nextLine();
            boolean validationResult = validations.isValidDescription(description);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(107);
                errorCodes.remove(integer);
                errorCodes.add(107);
            }
            else
            {
                userDetails.put("description",description);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(107);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 107");
        }
    }

    public void getJobType()
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

            for(int i : getType.keySet())
            {
                System.out.println(i + " " + getType.get(i));
            }
            Scanner sc = new Scanner(System.in);
            String jobType = sc.nextLine();
            boolean validationResult = validations.isValidJobType(jobType);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(108);
                errorCodes.remove(integer);
                errorCodes.add(108);
            }
            else
            {
                Integer integer;
                integer = Integer.valueOf(jobType);
                userDetails.put("jobType",getType.get(integer));
                if(errorCodes.isEmpty()==false)
                {
                    integer = Integer.valueOf(108);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 108");
        }
    }

    public void getHourlyRate()
    {
        try
        {
            System.out.println("Enter hourly rate");
            Scanner sc = new Scanner(System.in);
            String hourlyRate = sc.nextLine();
            boolean validationResult = validations.isValidHourlyRate(hourlyRate);
            if (validationResult == false)
            {
                Integer integer = Integer.valueOf(109);
                errorCodes.remove(integer);
                errorCodes.add(109);
            }
            else
            {
                userDetails.put("hourlyRate",hourlyRate);
                if(errorCodes.isEmpty()==false)
                {
                    Integer integer = Integer.valueOf(109);
                    errorCodes.remove(integer);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 109");
        }
    }

    public void getProfessionalCategoryDetails()
    {
        try
        {
            System.out.println("Enter the professional category." + "\n" + "1. Service Provider" + "\n" + "2. Customer" + "\n" + "Please enter the number");
            Scanner sc = new Scanner(System.in);
            int category = sc.nextInt();
            HashMap<Integer, String> getCategory = new HashMap<>();
            getCategory.put(1, "Service Provider");
            getCategory.put(2, "Customer");

            String value = getCategory.get(category);

            if (value == "Service Provider")
            {
                getExperience();
                getDescription();
                getJobType();
                getHourlyRate();
            }

            if(errorCodes.isEmpty() == true)
            {
                ConnectDatabase database = new ConnectDatabase();
                database.getConnection(userDetails);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 110");
        }
    }

    public void checkErrors()
    {
        try
        {
            Map<Integer, Runnable> map = new HashMap<Integer, Runnable>();
            map.put(100, () -> getFirstName());
            map.put(101, () -> getLastName());
            map.put(102, () -> getAddress());
            map.put(103, () -> getContact());
            map.put(104, () -> getEmail());
            map.put(105, () -> getPassword());
            map.put(106, () -> getExperience());
            map.put(107, () -> getDescription());
            map.put(108, () -> getJobType());
            map.put(109, () -> getHourlyRate());

            if(errorCodes.isEmpty())
            {
                return;
            }
            else
            {
                System.out.println("Invalid input.");
                while(errorCodes.isEmpty() == false)
                {
                    for (int i = 0; i<errorCodes.size(); i++)
                    {
                        map.get(errorCodes.get(i)).run();
                    }
                }
                ConnectDatabase database = new ConnectDatabase();
                database.getConnection(userDetails);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + "Error code- 111");
        }
    }
}