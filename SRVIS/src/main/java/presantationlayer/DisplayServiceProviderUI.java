package presantationlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayServiceProviderUI {

    public void displaySearchedServiceProviders(ResultSet rs){

        try{
            while (rs.next()) {
                String name = rs.getString("spName");
                String contact = rs.getString("spContact");
                String rate = rs.getString("spHourelyRate");

                System.out.println("--------------------------------------------------------------");
                System.out.println("Name: " + name + "\nContact Number:  " + contact + "\nHourly Rate: " + rate);
                System.out.println("--------------------------------------------------------------");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}