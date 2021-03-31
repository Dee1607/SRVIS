package payment;

import database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDAOTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void read() {
        PaymentInfo paymentInfoTestObject1 = new PaymentInfo();
        paymentInfoTestObject1.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject1.setCardNumber("1234567890");
        paymentInfoTestObject1.setUserID("User1");
        paymentInfoTestObject1.setFullName("Johnny Appleseed");
        paymentInfoTestObject1.setExpiryDate("12/12/12");
        paymentInfoTestObject1.setSecurityCode("123");

        PaymentInfo paymentInfoTestObject2 = new PaymentInfo();
        paymentInfoTestObject2.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject2.setCardNumber("1234567890");
        paymentInfoTestObject2.setUserID("User2");
        paymentInfoTestObject2.setFullName("John Doe");
        paymentInfoTestObject2.setExpiryDate("12/12/12");
        paymentInfoTestObject2.setSecurityCode("123");

        IPayment paymentTestObject = new Payment("ReadTestPaymentID");
        paymentTestObject.setSender(paymentInfoTestObject1);
        paymentTestObject.setReceiver(paymentInfoTestObject2);
        paymentTestObject.setAmount("100");
        paymentTestObject.setStatus(PaymentStatus.PENDING);
        paymentTestObject.setDate("12/12/12");
        paymentTestObject.setServiceRequestID("ServiceID");

        PaymentInfoDAO.write(paymentInfoTestObject1);
        PaymentInfoDAO.write(paymentInfoTestObject2);
        PaymentDAO.write(paymentTestObject);
        IPayment readObject = PaymentDAO.read("ReadTestPaymentID");
        assertEquals(readObject, paymentTestObject);

        try {
            Database db = Database.databaseInstance();
            Connection con = db.makeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User1';");
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User2';");
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void write() {
        PaymentInfo paymentInfoTestObject1 = new PaymentInfo();
        paymentInfoTestObject1.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject1.setCardNumber("1234567890");
        paymentInfoTestObject1.setUserID("User1");
        paymentInfoTestObject1.setFullName("Johnny Appleseed");
        paymentInfoTestObject1.setExpiryDate("12/12/12");
        paymentInfoTestObject1.setSecurityCode("123");

        PaymentInfo paymentInfoTestObject2 = new PaymentInfo();
        paymentInfoTestObject2.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject2.setCardNumber("1234567890");
        paymentInfoTestObject2.setUserID("User2");
        paymentInfoTestObject2.setFullName("John Doe");
        paymentInfoTestObject2.setExpiryDate("12/12/12");
        paymentInfoTestObject2.setSecurityCode("123");

        IPayment paymentTestObject = new Payment("TestPaymentID");
        paymentTestObject.setSender(paymentInfoTestObject1);
        paymentTestObject.setReceiver(paymentInfoTestObject2);
        paymentTestObject.setAmount("100");
        paymentTestObject.setStatus(PaymentStatus.PENDING);

        PaymentDAO.write(paymentTestObject);
    }
}