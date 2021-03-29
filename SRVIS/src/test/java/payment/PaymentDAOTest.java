package payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDAOTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void write() {
        PaymentInfo paymentInfoTestObject = new PaymentInfo();
        PaymentInfoDAO.write(paymentInfoTestObject);
    }

    @Test
    void read() {
    }
}