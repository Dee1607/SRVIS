package encryptionservice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionServiceTest {

    @Test
    void encryptDataForSecurity() {
        EncryptionService encryptionSecurity = new EncryptionService();
        ArrayList<String> getEncryptValue = encryptionSecurity.encryptDataForSecurity("Hello");
        String actualValue = getEncryptValue.get(0);
        assertEquals("73102109109112",actualValue);

    }
}