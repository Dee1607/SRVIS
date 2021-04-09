package encryptionservice;

import java.util.ArrayList;

public class EncryptionService implements IEncryptionService
{
    public ArrayList<String> encryptDataForSecurity(String stringToEncrypt)
    {
        ArrayList<String> encryptList = new ArrayList<>();
        try
        {
            StringBuilder encryptedString = new StringBuilder();
            for (int i = 0; i < stringToEncrypt.length(); i++)
            {
                int ascii = (int) stringToEncrypt.charAt(i) + 1;
                encryptedString.append(ascii);
            }
            encryptList.add(encryptedString.toString());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return encryptList;
    }
}