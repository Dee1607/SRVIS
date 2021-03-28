//package payment;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PaymentInfoTest {
//
//    @Test
//    public void setType() {
//        IPaymentInfo paymentInfo = new PaymentInfo();
//        String type = "VISA";
//        paymentInfo.setFullName(type);
//        assertEquals(paymentInfo.getType(), type);
//    }
//
//    @Test
//    public void setCardNumber() {
//        IPaymentInfo paymentInfo = new PaymentInfo();
//        String cardNumber = "1234567890";
//        paymentInfo.setCardNumber(cardNumber);
//        assertEquals(paymentInfo.getCardNumber(), cardNumber);
//    }
//
//    @Test
//    public void setFullName() {
//        IPaymentInfo paymentInfo = new PaymentInfo();
//        String fullName = "Johnny Appleseed";
//        paymentInfo.setFullName(fullName);
//        assertEquals(paymentInfo.getFullName(), fullName);
//    }
//
//    @Test
//    public void setSecurityCode() {
//        IPaymentInfo paymentInfo = new PaymentInfo();
//        String securityCode = "Johnny Appleseed";
//        paymentInfo.setFullName(securityCode);
//        assertEquals(paymentInfo.getFullName(), securityCode);
//    }
//}