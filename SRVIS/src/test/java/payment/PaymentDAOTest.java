package payment;

import database.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentDAOTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void read() {
        PaymentDAO testPaymentDAO = new PaymentDAO();
        PaymentInfo paymentInfoTestObject1 = new PaymentInfo();
        paymentInfoTestObject1.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject1.setCardNumber("1234567890");
        paymentInfoTestObject1.setUserID("User009");
        paymentInfoTestObject1.setFullName("Johnny Appleseed");
        paymentInfoTestObject1.setExpiryDate("12/12/12");
        paymentInfoTestObject1.setSecurityCode("123");

        PaymentInfo paymentInfoTestObject2 = new PaymentInfo();
        paymentInfoTestObject2.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject2.setCardNumber("1234567890");
        paymentInfoTestObject2.setUserID("User010");
        paymentInfoTestObject2.setFullName("John Doe");
        paymentInfoTestObject2.setExpiryDate("12/12/12");
        paymentInfoTestObject2.setSecurityCode("123");

        IPayment paymentTestObject = new Payment();
        paymentTestObject.setSender(paymentInfoTestObject1);
        paymentTestObject.setReceiver(paymentInfoTestObject2);
        paymentTestObject.setAmount("100");
        paymentTestObject.setStatus(PaymentStatus.PENDING);
        paymentTestObject.setDate("12/12/12");
        paymentTestObject.setServiceRequestID("ServiceID");

        PaymentInfoDAO testPaymentInfoDAO = new PaymentInfoDAO();
        testPaymentInfoDAO.write(paymentInfoTestObject1);
        testPaymentInfoDAO.write(paymentInfoTestObject2);
        testPaymentDAO.write(paymentTestObject);
        IPayment readObject = testPaymentDAO.read(1);
        // assertEquals(readObject, paymentTestObject);

        try {
            DatabaseConnection db = DatabaseConnection.databaseInstance();
            Connection con = db.makeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User009';");
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User010';");
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void write() {
        PaymentDAO testPaymentDAO = new PaymentDAO();
        PaymentInfo paymentInfoTestObject1 = new PaymentInfo();
        paymentInfoTestObject1.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject1.setCardNumber("1234567890");
        paymentInfoTestObject1.setFullName("Johnny Appleseed");
        paymentInfoTestObject1.setExpiryDate("12/12/12");
        paymentInfoTestObject1.setSecurityCode("123");

        PaymentInfo paymentInfoTestObject2 = new PaymentInfo();
        paymentInfoTestObject2.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject2.setCardNumber("1234567890");
        paymentInfoTestObject2.setFullName("John Doe");
        paymentInfoTestObject2.setExpiryDate("12/12/12");
        paymentInfoTestObject2.setSecurityCode("123");

        IPayment paymentTestObject = new Payment();
        paymentTestObject.setSender(paymentInfoTestObject1);
        paymentTestObject.setReceiver(paymentInfoTestObject2);
        paymentTestObject.setAmount("100");
        paymentTestObject.setStatus(PaymentStatus.PENDING);

        assertTrue(testPaymentDAO.write(paymentTestObject));
    }
}