package payment;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentInfoDAO {
    private static final Database paymentDB = Database.databaseInstance();
    private static IPaymentInfo paymentInfo = null;

    public static IPaymentInfo read(String userID) {
        try {
            Connection con = paymentDB.makeConnection();
            String selectQuery = "SELECT * FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = ? LIMIT 1;";
            PreparedStatement insertPayment = con.prepareStatement(selectQuery);
            insertPayment.setString(1, userID);
            ResultSet rs = insertPayment.executeQuery();
            String paymentType = rs.getString("payment_type");
            String cardNumber = rs.getString("card_number");
            String fullName = rs.getString("full_name");
            String securityCode = rs.getString("security_code");
            String expiryDate = rs.getString("expiry_date");

            paymentInfo = new PaymentInfo();
            paymentInfo.setPaymentType(PaymentType.valueOf(paymentType));
            paymentInfo.setCardNumber(cardNumber);
            paymentInfo.setFullName(fullName);
            paymentInfo.setSecurityCode(securityCode);
            paymentInfo.setExpiryDate(expiryDate);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentInfo;
    }
    public static void write(IPaymentInfo paymentInfo) {

    }
}
