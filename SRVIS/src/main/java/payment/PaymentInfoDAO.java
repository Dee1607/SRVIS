package payment;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentInfoDAO {
    private static DatabaseConnection paymentDB = DatabaseConnection.databaseInstance();

    public static IPaymentInfo read(String userID) {
        IPaymentInfo paymentInfo = null;
        try {
            paymentDB = DatabaseConnection.databaseInstance();
            Connection con = paymentDB.makeConnection();
            String selectQuery = "SELECT * FROM CSCI5308_3_DEVINT.payment_info WHERE user_id = ? LIMIT 1;";
            PreparedStatement insertPayment = con.prepareStatement(selectQuery);
            insertPayment.setString(1, userID);
            ResultSet rs = insertPayment.executeQuery();
            rs.next();
            String paymentType = rs.getString("payment_type");
            String cardNumber = rs.getString("card_number");
            String fullName = rs.getString("full_name");
            String securityCode = rs.getString("security_code");
            String expiryDate = rs.getString("expiry_date");

            paymentInfo = new PaymentInfo();
            paymentInfo.setUserID(userID);
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
    public static boolean write(IPaymentInfo paymentInfo) {
        boolean writeSuccessful = false;
        try {
            paymentDB = DatabaseConnection.databaseInstance();
            Connection con = paymentDB.makeConnection();

            String userID = paymentInfo.getUserID();
            String paymentType = paymentInfo.getPaymentType();
            String cardNumber = paymentInfo.getCardNumber();
            String fullName = paymentInfo.getFullName();
            String securityCode = paymentInfo.getSecurityCode();
            String expiryDate = paymentInfo.getExpiryDate();

            String insertQuery = "INSERT INTO `CSCI5308_3_DEVINT`.`payment_info`\n" +
                    "(`user_id`,\n" +
                    "`payment_type`,\n" +
                    "`card_number`,\n" +
                    "`full_name`,\n" +
                    "`security_code`,\n" +
                    "`expiry_date`)\n" +
                    "VALUES\n" +
                    "(?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?);\n";

            con.setAutoCommit(false);
            PreparedStatement insertPaymentInfo = con.prepareStatement(insertQuery);
            insertPaymentInfo.setString(1, userID);
            insertPaymentInfo.setString(2, paymentType);
            insertPaymentInfo.setString(3, cardNumber);
            insertPaymentInfo.setString(4, fullName);
            insertPaymentInfo.setString(5, securityCode);
            insertPaymentInfo.setString(6, expiryDate);

            int result = insertPaymentInfo.executeUpdate();

            if (result == 1) {
                con.commit();
                writeSuccessful = true;
            }
            else {
                System.err.println("Error. Transaction is being rolled back");
                con.rollback();
            }
            con.close();
            paymentDB.closeConnection();
            paymentDB = null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writeSuccessful;
    }
}
