package payment;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void validatePayment() {

    }

    @Test
    public void processPayment() {
    }

    @Test
    public void getStatus() {
        IPaymentInfo sender = new PaymentInfo();
        IPaymentInfo receiver = new PaymentInfo();
        String amount = "100.00";
        Payment payment = new Payment(sender, receiver, amount);

        assertEquals(payment.getStatus(), PaymentStatus.PENDING);
    }
}