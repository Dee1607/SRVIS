package registration;

import customer.GenerateDataToDisplay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationMethodsTest {
    @Test
    void addMethodsTest() {
        RegistrationMethods registerMethods = new RegistrationMethods();
        assertNotNull(registerMethods.addMethods("Customer"));
    }
}