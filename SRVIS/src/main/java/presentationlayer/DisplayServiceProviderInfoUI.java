package presentationlayer;

import java.util.Map;

public class DisplayServiceProviderInfoUI
{
    public void displayServiceProviderBriefInfo(Map<String, Map<String, String>> mapServiceProviderInfo)
    {
        System.out.format("%1s%20s%20s%20s%20s", "|", "===================" + "|", "===================" + "|", "===================" + "|", "==================" + "|\n");
        System.out.format("%1s%20s%20s%20s%20s", "|", "Provider ID " + "|", "Name " + "|", "Contact Number " + "|", "Hourly Rate ($) " + "|\n");
        System.out.format("%1s%20s%20s%20s%20s", "|", "===================" + "|", "===================" + "|", "===================" + "|", "==================" + "|\n");

        for (String key : mapServiceProviderInfo.keySet())
        {
            System.out.format("%1s%20s%20s%20s%20s", "|", key + " |", mapServiceProviderInfo.get(key).get("firstName") + " " + mapServiceProviderInfo.get(key).get("lastName") + " |", mapServiceProviderInfo.get(key).get("contact") + " |", "$" + mapServiceProviderInfo.get(key).get("hourlyRate") + " |\n");
            System.out.format("%1s%20s%20s%20s%20s", "|", "-------------------" + "|", "-------------------" + "|", "-------------------" + "|", "------------------" + "|\n");
        }
    }

    public void displayServiceProviderAllInfo(String key, Map<String, String> mapOtherValues)
    {
        System.out.format("%1s%20s%1s%55s%1s", "|", "====================", "|", "========================================================", "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " ID ", "| ", key, "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Name ", "| ", mapOtherValues.get("firstName") + " " + mapOtherValues.get("lastName"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Address ", "| ", mapOtherValues.get("address"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Email ", "| ", mapOtherValues.get("email"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Mobile ", "| ", mapOtherValues.get("contact"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Date Of Birth ", "| ", mapOtherValues.get("age"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Service Type ", "| ", mapOtherValues.get("jobType"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Hourly Rate ($) ", "| ", "$" + mapOtherValues.get("hourlyRate"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Experience (years)", "| ", mapOtherValues.get("experience") + "years", "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Rating ", "| ", mapOtherValues.get("ratings"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", "--------------------", "|", "--------------------------------------------------------", "|\n");
    }

    public void displayCustomerAllInfo(Map<String, String> mapOtherValues)
    {
        System.out.format("%1s%20s%1s%55s%1s", "|", "====================", "|", "========================================================", "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Name ", "| ", mapOtherValues.get("first_name") + " " + mapOtherValues.get("last_name"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Mobile ", "| ", mapOtherValues.get("contact"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Address ", "| ", mapOtherValues.get("address"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Email ", "| ", mapOtherValues.get("email"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", " Amount ", "| ", mapOtherValues.get("amount"), "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|", "--------------------", "|", "--------------------------------------------------------", "|\n");
    }
}