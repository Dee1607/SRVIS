package registration;

import java.sql.SQLException;
import java.util.*;

public class RegistrationPage {
    Validations validations = new Validations();
    List<Integer> errorCodes = new ArrayList<Integer>();
    HashMap<String, String> userDetails = new HashMap<String, String>();
    String firstname;
    String lName;

    public void getFirstName() {
        System.out.println("Enter First Name");
        Scanner sc = new Scanner(System.in);
        firstname = sc.nextLine();
        boolean validationResult = validations.isValidName(firstname);
        if (validationResult == false){
            Integer integer = Integer.valueOf(100);
            errorCodes.remove(integer);
            errorCodes.add(100);
        }
        else{
            userDetails.put("firstName",firstname);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(100);
                errorCodes.remove(integer);
            }
        }
    }
    public void getLastName() {
        System.out.println("Enter Last Name");
        Scanner sc = new Scanner(System.in);
        lName = sc.nextLine();
        boolean validationResult = validations.isValidName(lName);
        if (validationResult == false){
            Integer integer = Integer.valueOf(101);
            errorCodes.remove(integer);
            errorCodes.add(101);
        }
        else{
            userDetails.put("lastName",lName);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(101);
                errorCodes.remove(integer);
            }
        }
    }

    public void getAddress() {
        System.out.println("Enter Address");
        Scanner sc = new Scanner(System.in);
        String address = sc.nextLine();
        boolean validationResult = validations.isValidAddress(address);
        if (validationResult == false){
            Integer integer = Integer.valueOf(102);
            errorCodes.remove(integer);
            errorCodes.add(102);
        }
        else{
            userDetails.put("address",address);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(102);
                errorCodes.remove(integer);
            }
        }
    }

    public void getContact() {
        System.out.println("Enter contact number");
        Scanner sc = new Scanner(System.in);
        String contact = sc.nextLine();
        boolean validationResult = validations.isValidContact(contact);
        if (validationResult == false){
            Integer integer = Integer.valueOf(103);
            errorCodes.remove(integer);
            errorCodes.add(103);
        }
        else{
            userDetails.put("contact",contact);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(103);
                errorCodes.remove(integer);
            }
        }
    }

    public void getEmail() {
        System.out.println("Enter email ID");
        Scanner sc = new Scanner(System.in);
        String emailId = sc.nextLine();
        boolean validationResult = validations.isValidEmail(emailId);
        if (validationResult == false){
            Integer integer = Integer.valueOf(104);
            errorCodes.remove(integer);
            errorCodes.add(104);
        }
        else{
            userDetails.put("email",emailId);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(104);
                errorCodes.remove(integer);
            }
        }
    }

    public void getPassword() {
        System.out.println("Enter new password.");
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        boolean validationResult = validations.isValidPassword(password);
        if (validationResult == false){
            Integer integer = Integer.valueOf(105);
            errorCodes.remove(integer);
            errorCodes.add(105);
        }
        else{
            userDetails.put("password",password);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(105);
                errorCodes.remove(integer);
            }
        }
    }

    public void getExperience() {
        System.out.println("Enter years of experience in the field");
        Scanner sc = new Scanner(System.in);
        String experience = sc.nextLine();
        boolean validationResult = validations.isValidExperience(experience);
        if (validationResult == false){
            Integer integer = Integer.valueOf(106);
            errorCodes.remove(integer);
            errorCodes.add(106);
        }
        else{
            userDetails.put("experience",experience);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(106);
                errorCodes.remove(integer);
            }
        }
    }

    public void getDescription() {
        System.out.println("Enter brief description related to profile");
        Scanner sc = new Scanner(System.in);
        String description = sc.nextLine();
        boolean validationResult = validations.isValidDescription(description);
        if (validationResult == false){
            Integer integer = Integer.valueOf(107);
            errorCodes.remove(integer);
            errorCodes.add(107);
        }
        else{
            userDetails.put("description",description);
            if(errorCodes.isEmpty()==false){
                Integer integer = Integer.valueOf(107);
                errorCodes.remove(integer);
            }
        }
    }

    public void getProfessionalCategoryDetails() throws SQLException {
        System.out.println("Enter the professional category." + "\n" + "1. Service Provider" + "\n" + "2. Customer" + "\n" + "Please enter the number");
        Scanner sc = new Scanner(System.in);
        int category = sc.nextInt();
        HashMap<Integer, String> getCategory = new HashMap<>();
        getCategory.put(1, "Service Provider");
        getCategory.put(2, "Customer");

        String value = getCategory.get(category);

        if (value == "Service Provider") {
            getExperience();
            getDescription();
        }

        if(errorCodes.isEmpty() == true){
            ConnectDatabase database = new ConnectDatabase();
            database.getConnection(userDetails);
        }
    }

    public void checkErrors(){
        Map<Integer, Runnable> map = new HashMap<Integer, Runnable>();
        map.put(100, () -> getFirstName());
        map.put(101, () -> getLastName());
        map.put(102, () -> getAddress());
        map.put(103, () -> getContact());
        map.put(104, () -> getEmail());
        map.put(105, () -> getPassword());
        map.put(106, () -> getExperience());
        map.put(107, () -> getDescription());

        if(errorCodes.isEmpty()){
            return;
        }
        else{
            System.out.println("Invalid input.");
            while(errorCodes.isEmpty() == false){
                for (int i = 0; i<errorCodes.size(); i++){
                    map.get(errorCodes.get(i)).run();
                }
            }
            ConnectDatabase database = new ConnectDatabase();
            database.getConnection(userDetails);
        }
    }
}
