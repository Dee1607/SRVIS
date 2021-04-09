package registration;

import org.junit.jupiter.api.Test;
import presentationlayer.DisplayToGetUserChoice;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationMainTest {
    @Test
    void getUserDetailsTest() {
        RegistrationMain registerMain = new RegistrationMain(new DisplayToGetUserChoice());
        assertNotNull(registerMain.getUserDetails());
    }

}