package payment;

import database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInfoDAOTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void read() {
        IPaymentInfo paymentInfoTestWriteObject = new PaymentInfo();
        paymentInfoTestWriteObject.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestWriteObject.setCardNumber("1234567890");
        paymentInfoTestWriteObject.setUserID("User1");
        paymentInfoTestWriteObject.setFullName("Johnny Appleseed");
        paymentInfoTestWriteObject.setExpiryDate("12/12/12");
        paymentInfoTestWriteObject.setSecurityCode("123");

        assertTrue(PaymentInfoDAO.write(paymentInfoTestWriteObject));

        IPaymentInfo paymentInfoReadTestObject = PaymentInfoDAO.read("User1");
        assertEquals(paymentInfoReadTestObject, paymentInfoTestWriteObject);

        try {
            Database db = Database.databaseInstance();
            Connection con = db.makeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User1';");
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void write() {
        IPaymentInfo paymentInfoTestObject1 = new PaymentInfo();
        paymentInfoTestObject1.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject1.setCardNumber("1234567890");
        paymentInfoTestObject1.setUserID("User1");
        paymentInfoTestObject1.setFullName("Johnny Appleseed");
        paymentInfoTestObject1.setExpiryDate("12/12/12");
        paymentInfoTestObject1.setSecurityCode("123");

        assertTrue(PaymentInfoDAO.write(paymentInfoTestObject1));

        try {
            Database db = Database.databaseInstance();
            Connection con = db.makeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User1';");
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}