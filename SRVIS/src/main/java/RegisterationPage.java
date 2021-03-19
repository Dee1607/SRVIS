import java.io.IOException;
import java.util.Scanner;

public class RegisterationPage {
        public void registerUser(){
            try{
                Scanner sc = new Scanner(System.in);
                Validations validations = new Validations();

                boolean validName;
                do{
                    System.out.println("Enter First Name");
                    String firstname = sc.nextLine();
                    validName = validations.isValidFirstName(firstname);
                    if(validName){
                        boolean validLName;
                        do{
                            System.out.println("Enter Last Name");
                            String lName = sc.nextLine();
                            validLName = validations.isValidLastName(lName);
                            if(validLName){
                                boolean checkValidAddress;
                                do{
                                    System.out.println("Enter Address");
                                    String address = sc.nextLine();
                                    checkValidAddress =  validations.isValidAddress(address);
                                    if(checkValidAddress){
                                        boolean checkEmail;
                                        do{
                                            System.out.println("Enter email ID");
                                            String emailId = sc.nextLine();
                                            checkEmail = validations.isValidEmail(emailId);
                                            if(checkEmail){
                                                boolean checkPassword;
                                                do{
                                                    System.out.println("Enter new password.");
                                                    String password = sc.nextLine();
                                                    checkPassword = validations.isValidPassword(password);
                                                    if(checkPassword){
                                                        System.out.println("Enter` the professional category." + "\n" + "1. Service Provider"+ "\n" + "2. Customer" + "\n" + "Please enter the number");
                                                        int category = sc.nextInt();
                                                        switch (category){
                                                            case 1:
                                                                boolean checkExperience;
                                                                do{
                                                                    System.out.println("Enter years of experience in the field");
                                                                    String experience = sc.nextLine();
                                                                    checkExperience = validations.isValidExperience(experience);
                                                                    if(checkExperience){
                                                                        boolean checkDescription;
                                                                        do{
                                                                            System.out.println("Enter brief description related to profile");
                                                                            String description = sc.nextLine();
                                                                            checkDescription = validations.isValidDescription(description);
                                                                            if(checkDescription){
                                                                                System.out.println("Thank you for registering with us.");
                                                                            }
                                                                            else {
                                                                                System.out.println("Please enter valid description." + "\n" +"Error code- 105");
                                                                            }
                                                                        }while (checkDescription == false);
                                                                    }
                                                                    else {
                                                                        System.out.println("Please enter valid experience." + "\n" +"Error code- 104");
                                                                    }
                                                                }while (checkExperience == false);
                                                                break;

                                                            case 2:
                                                                System.out.println("Thank you for registering with us.");
                                                                break;

                                                            default:
                                                                System.out.println("Please restart the application.");
                                                                System.exit(0);
                                                                break;
                                                        }
                                                    }
                                                }while (checkPassword == false);
                                            }
                                            else {
                                                System.out.println("Please enter valid email ID." + "\n" +"Error code- 103");
                                            }
                                        }while(checkEmail == false);
                                    }
                                    else {
                                        System.out.println("Please enter valid address." + "\n" +"Error code- 102");
                                    }
                                }while (checkValidAddress == false);
                            }
                            else {
                                System.out.println("Please enter valid last name." + "\n" +"Error code- 101");
                            }
                        }while (validLName == false);
                    }
                    else{
                        System.out.println("Please enter valid first name." + "\n" +"Error code- 100");
                    }
                }while (validName == false);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
}
