package presentationlayer;

import DAOclasses.ServiceProviderInfo;
import java.util.List;
import java.util.Map;

public class DisplayServiceProviderUI
{
    public void displaySearchedServiceProviders(Map<String, List<ServiceProviderInfo>> mapServiceProviderDetails)
    {
        try{
            for(String spID : mapServiceProviderDetails.keySet()) {
                for (ServiceProviderInfo listSPDetails : mapServiceProviderDetails.get(spID)) {
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("ID: " +spID);
                    System.out.println("Name: "+listSPDetails.getName());
                    System.out.println("Contact Number: " +listSPDetails.getContact());
                    System.out.println("Hourly Rate: " +listSPDetails.getHourlyRate());
                    System.out.println("--------------------------------------------------------------");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}