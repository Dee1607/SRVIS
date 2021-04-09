package payment;

import database.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentInfoDAOTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void read() {
        PaymentInfoDAO testPaymentInfoDAO = new PaymentInfoDAO();
        IPaymentInfo paymentInfoTestWriteObject = new PaymentInfo();
        paymentInfoTestWriteObject.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestWriteObject.setCardNumber("1234567890");
        paymentInfoTestWriteObject.setUserID("User123");
        paymentInfoTestWriteObject.setFullName("Johnny Appleseed");
        paymentInfoTestWriteObject.setExpiryDate("12/12/12");
        paymentInfoTestWriteObject.setSecurityCode("123");

        //assertTrue(testPaymentInfoDAO.write(paymentInfoTestWriteObject));

        IPaymentInfo paymentInfoReadTestObject = testPaymentInfoDAO.read("User123");
        assertEquals(paymentInfoReadTestObject, paymentInfoTestWriteObject);

        try {
            DatabaseConnection db = DatabaseConnection.databaseInstance();
            Connection con = db.makeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User123';");
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void write() {
        PaymentInfoDAO testPaymentInfoDAO = new PaymentInfoDAO();
        IPaymentInfo paymentInfoTestObject1 = new PaymentInfo();
        paymentInfoTestObject1.setPaymentType(PaymentType.valueOf("VISA"));
        paymentInfoTestObject1.setCardNumber("1234567890");
        paymentInfoTestObject1.setUserID("User321");
        paymentInfoTestObject1.setFullName("Johnny Appleseed");
        paymentInfoTestObject1.setExpiryDate("12/12/12");
        paymentInfoTestObject1.setSecurityCode("123");

        //assertTrue(testPaymentInfoDAO.write(paymentInfoTestObject1));

        try {
            DatabaseConnection db = DatabaseConnection.databaseInstance();
            Connection con = db.makeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = 'User321';");
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}