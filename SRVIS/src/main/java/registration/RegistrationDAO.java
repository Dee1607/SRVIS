package registration;

import database.DatabaseConnection;
import database.IDatabaseConnection;

import java.util.HashMap;

public class RegistrationDAO implements IRegistrationDAO
{
    private String insertQuery;
    public boolean insertStatus;
    IDatabaseConnection db;

    public RegistrationDAO(){
        db = DatabaseConnection.databaseInstance();
    }
    public boolean getConnection(HashMap<String,String> userInput)
    {
        try
        {
            if(userInput.keySet().size()>7 )
            {
                insertQuery="INSERT INTO service_provider(firstName, lastName, contact, address,  email, password, jobType, experience, certification,  hourlyRate, age)" + " values('" + userInput.get("1") + "','" + userInput.get("2") + "','" + userInput.get("3") + "','" + userInput.get("4") + "','" + userInput.get("5") + "','" + userInput.get("6") + "','" + userInput.get("7") + "','" + userInput.get("8") + "','" + userInput.get("9") + "','" + userInput.get("10") + "','" + userInput.get("11") + "');";
            }
            else
            {
                insertQuery="INSERT INTO customer(first_name,last_name,contact,address,email,password)" + " values(?,?,?,?,?,?);";
            }
            db.makeConnection();
            insertStatus = db.insertRegQuery(insertQuery,userInput);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } finally {
            try{
                db.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return insertStatus;
    }
}