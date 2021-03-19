import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ServiceCategoryUI
{
    public void searchService(){
        Scanner sc = new Scanner(System.in);
        ConnectionToDB objConnect = new ConnectionToDB();

        System.out.println("What service are you looking for:");
        System.out.println("1. Electrician");
        System.out.println("2. Plumber");
        System.out.println("4. Carpenter");
        System.out.println("5. Painter");
        System.out.println("6. Cleaner");
        int userServiceCatagoryChoice = sc.nextInt();

        EnumServiceCatagory enumObjectOfChoice = EnumServiceCatagory.values()[userServiceCatagoryChoice];

        try {
            Connection conn = objConnect.getConnection();

            Statement stmt = conn.createStatement();
            String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.service_providers where spJobType = '"
                    + enumObjectOfChoice.toString() + "';";
            ResultSet rs = stmt.executeQuery(sql1);


            while (rs.next()) {
                String name = rs.getString("spName");
                String contact = rs.getString("spContact");
                String rate = rs.getString("spHourelyRate");

                System.out.println("--------------------------------------------------------------");
                System.out.println("Name: " + name + "\nContact Number:  " + contact + "\nHourly Rate: " + rate);
                System.out.println("--------------------------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
