package presantationlayer;

import DAOclasses.ServiceProviderInfo;
import database.ConnectionToDB;
import enums.EnumServiceCatagory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

            Map<String, List<ServiceProviderInfo>> mapServiceProvider = new HashMap<>();
            ServiceProviderInfo objServiceProvider = null;
            List<ServiceProviderInfo> listServiceProviderDetails = null;

            while(rs.next()){

                objServiceProvider = new ServiceProviderInfo();
                listServiceProviderDetails = new ArrayList<ServiceProviderInfo>();

                objServiceProvider.setId(rs.getInt("service_provider_id"));
                objServiceProvider.setName(rs.getString("spName"));
                objServiceProvider.setAddress(rs.getString("spAddress"));
                objServiceProvider.setContact(rs.getString("spContact"));
                objServiceProvider.setJobType(rs.getString("spJobType"));
                objServiceProvider.setServiceCategory(rs.getString("spServiceCategoryID"));
                objServiceProvider.setHourlyRate(rs.getString("spHourlyRate"));
                objServiceProvider.setExperience(rs.getString("spExperience"));
                objServiceProvider.setCategoryID(rs.getString("spCertification"));
                objServiceProvider.setRating(rs.getString("spRatings"));

                listServiceProviderDetails.add(objServiceProvider);
                mapServiceProvider.put(rs.getString("service_provider_id").toString(),listServiceProviderDetails);
            }

            objDisplay.displaySearchedServiceProviders(mapServiceProvider);


            System.out.println("Enter the Id of the service provider you want to select: ");
            int selectedServiceProviderId = sc.nextInt();

            // Call the next method to show Service Providers details and book
            // For branch: feature-select_service_provider
            
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