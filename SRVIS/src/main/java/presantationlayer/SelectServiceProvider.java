package presantationlayer;

import DAOclasses.ServiceProviderInfo;
import java.util.List;
import java.util.Scanner;

public class SelectServiceProvider
{
    public void selectFromProvidedOptions(List<ServiceProviderInfo> selectedServiceProvider)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            for(ServiceProviderInfo info : selectedServiceProvider)
            {
                System.out.println("\n--------------------------------------------------------------");
                System.out.println("Contact Details of Service Provider");
                System.out.println("ID: " +info.getId());
                System.out.println("Name: " +info.getName());
                System.out.println("Contact: " +info.getContact());
                System.out.println("Address:" +info.getAddress());

                System.out.println("\n--------------------------------------------------------------");
                System.out.println("More Information Related to Service Provider");
                System.out.println("Service Category: " +info.getServiceCategory());
                System.out.println("Job Type: " +info.getJobType());
                System.out.println("Certification Details: " +info.getCertification());
                System.out.println("Experience: " +info.getExperience());
                System.out.println("Ratings: " +info.getRating());
                System.out.println("\n--------------------------------------------------------------");
            }
            System.out.println("Do you want to select this service provider [Y/N]: ");
            String choiceToSelect = sc.next();
            if(choiceToSelect.equalsIgnoreCase("Y"))
            {
//                BookServiceProvider objBookProvider = new BookServiceProvider();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}