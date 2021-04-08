package Encryption;

import java.util.ArrayList;

public class Encryption implements IEncryption {
    public ArrayList<String> encryptString(String getData) {
        ArrayList<String> encryptList = new ArrayList<>();
        try {
            String encryptedString = "";
            for (int i = 0; i < getData.length(); i++) {
                int ascii = (int) getData.charAt(i) + 1;
                encryptedString = encryptedString + ascii;
            }
            encryptList.add(encryptedString);
            //System.out.println(encryptList.get(0));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return encryptList;
    }
}
