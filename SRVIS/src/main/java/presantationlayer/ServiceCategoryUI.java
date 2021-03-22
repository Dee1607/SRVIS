package presantationlayer;

import database.ConnectionToDB;
import enums.EnumServiceCatagory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiceCategoryUI
{
    public void searchService() {
        Scanner sc = new Scanner(System.in);
        ConnectionToDB objConnect = new ConnectionToDB();

        Map<Integer,String> mapSearchCategories = new HashMap<Integer,String>();

        mapSearchCategories.put(0,"What service are you looking for:");
        mapSearchCategories.put(1,"1. Electrician");
        mapSearchCategories.put(2,"2. Plumber");
        mapSearchCategories.put(3,"3. Carpenter");
        mapSearchCategories.put(4,"4. Painter");
        mapSearchCategories.put(5,"5. Cleaner");

        for(int i : mapSearchCategories.keySet()){
            System.out.println(mapSearchCategories.get(i));
        }

        int userServiceCategoryChoice = sc.nextInt();
        EnumServiceCatagory enumObjectOfChoice = EnumServiceCatagory.values()[userServiceCategoryChoice - 1];

        try {
            Connection conn = objConnect.getConnection();

//            CallableStatement statement = conn.prepareCall("{call get_searched_service_provider(enumObjectOfChoice.toString())}");
//            ResultSet searchedServiceProviders = statement.getResultSet();

            Statement stmt = conn.createStatement();
            String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.service_providers where spJobType = '"
                    + enumObjectOfChoice.toString() + "';";
            ResultSet rs = stmt.executeQuery(sql1);

            DisplayServiceProviderUI objDisplay = new DisplayServiceProviderUI();
            objDisplay.displaySearchedServiceProviders(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                objConnect.closeConnection();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}