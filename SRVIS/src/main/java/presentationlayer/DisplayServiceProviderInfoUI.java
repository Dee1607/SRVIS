package presentationlayer;

import java.util.Map;

public class DisplayServiceProviderInfoUI
{
    public void displayServiceProviderBriefInfo(Map<String, Map<String,String>> mapServiceProviderInfo)
    {
        System.out.format("%1s%20s%20s%20s%20s", "|","==================="+"|", "==================="+"|", "==================="+"|","=================="+"|\n");
        System.out.format("%1s%20s%20s%20s%20s", "|","Provider ID "+"|", "Name "+"|", "Contact Number "+"|","Hourly Rate ($) "+"|\n");
        System.out.format("%1s%20s%20s%20s%20s", "|","==================="+"|", "==================="+"|", "==================="+"|","=================="+"|\n");

        for(String key : mapServiceProviderInfo.keySet())
        {
            Map<String,String> mapOtherValues = mapServiceProviderInfo.get(key);
            System.out.format("%1s%20s%20s%20s%20s", "|",key+" |", mapOtherValues.get("firstName")+" "+ mapOtherValues.get("lastName")+" |", mapOtherValues.get("spContact")+" |","$"+mapOtherValues.get("spHourlyRate")+" |\n");
            System.out.format("%1s%20s%20s%20s%20s", "|","-------------------"+"|", "-------------------"+"|", "-------------------"+"|","------------------"+"|\n");
        }
    }

    public void displayServiceProviderAllInfo(String key, Map<String,String> mapOtherValues)
    {
        System.out.format("%1s%20s%1s%55s%1s", "|","====================","|", "========================================================","|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," ID ","| ", key ,"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Name ","| ", mapOtherValues.get("firstName")+" "+ mapOtherValues.get("lastName"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Address ","| ", mapOtherValues.get("spAddress"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Email ","| ", mapOtherValues.get("Email"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Mobile ","| ", mapOtherValues.get("spContact"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Date Of Birth ","| ", mapOtherValues.get("spAge"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Service Type ","| ", mapOtherValues.get("spJobType"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Hourly Rate ($) ","| ", "$" + mapOtherValues.get("spHourlyRate"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Experience (years)","| ", mapOtherValues.get("spExperience") + "years" , "|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|"," Rating ","| ", mapOtherValues.get("spRatings"),"|\n");
        System.out.format("%1s%-20s%1s%-55s%1s", "|","--------------------","|", "--------------------------------------------------------","|\n");
    }
}