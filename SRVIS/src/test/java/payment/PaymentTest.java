//package payment;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class PaymentTest {
//
//    @Test
//    public void validatePayment() {
//
//    }
//
//    @Test
//    public void processPayment() {
//    }
//
//    @Test
//    public void getStatus() {
//        IPaymentInfo sender = new PaymentInfo();
//        IPaymentInfo receiver = new PaymentInfo();
//        String amount = "100.00";
//        Transaction transaction = new Transaction();
//        transaction.setSender(sender);
//        transaction.setReceiver(receiver);
//        transaction.setAmount(amount);
//        Payment payment = new Payment(transaction);
//
//        Assertions.assertEquals(payment.getStatus(), PaymentStatus.PENDING);
//    }
//}