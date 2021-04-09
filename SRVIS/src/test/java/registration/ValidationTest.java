package registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationTest {
    @Test
    @DisplayName("Testing validation method")
    public void checkValidation() throws Exception {
        Validation validation = new Validation();
        boolean actualValue = validation.isValidString("[a-zA-Z]+", "SRVIS");
        boolean expectedValue = true;
        assertEquals(expectedValue, actualValue);
    }
}