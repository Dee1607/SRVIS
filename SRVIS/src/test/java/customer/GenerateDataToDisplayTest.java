package customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateDataToDisplayTest {

    @Test
    void generateLoginData() {
        GenerateDataToDisplay dataDisplay = new GenerateDataToDisplay();
        assertNotNull(dataDisplay.generateLoginData());
    }
    @Test
    void generateServiceCategoryData() {
        GenerateDataToDisplay dataDisplay = new GenerateDataToDisplay();
        assertNotNull(dataDisplay.generateServiceCategoryData());
    }
}