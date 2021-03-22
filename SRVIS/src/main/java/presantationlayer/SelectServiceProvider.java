package presantationlayer;

import database.ConnectionToDB;
import enums.EnumServiceCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SelectServiceProvider {
    public void selectFromProvidedOptions(){
        Connection conn = null;
        try{
            conn = new ConnectionToDB().getConnection();
            Statement stmt = conn.createStatement();
            String sql1 = "SELECT * FROM CSCI5308_3_DEVINT.service_providers where spJobType = '"
                    + EnumServiceCategory.Electrician.toString() + "';";
            ResultSet rs = stmt.executeQuery(sql1);

            Map<Integer,String> mapServiceProviders = new HashMap<Integer,String>();
            int countOfServiceProvider = 0;
            while(rs.next()){
                mapServiceProviders.put(countOfServiceProvider++, rs);
            }
        }catch (Exception e){

        }
    }
}