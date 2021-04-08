package Encryption;

import Encryption.Encryption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {
    @Test
    @DisplayName("Testing validation method")
    public void encryptStringTest(){
        Encryption encrypt = new Encryption();
        ArrayList<String> getEncryptedValue = encrypt.encryptString("Hello");
        String actualValue = getEncryptedValue.get(0);
        assertEquals("73102109109112",actualValue);
    }
}