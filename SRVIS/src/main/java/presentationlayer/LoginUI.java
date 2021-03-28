package presentationlayer;

import login.LoginService;

import java.util.Scanner;

public class LoginUI {

       private String username;
       private String password;
       private String type;
       LoginService login = new LoginService();

    public void showLoginScreen() throws Exception {
           Scanner sc = new Scanner(System.in);
           System.out.println("Enter your Username:");
           username = sc.nextLine();
           System.out.println("Enter your password:");
           password = sc.nextLine();
           System.out.println("Login as Customer/Service provider");
           type = sc.nextLine();


           login.loginUser(username, password,type);
    }
}
