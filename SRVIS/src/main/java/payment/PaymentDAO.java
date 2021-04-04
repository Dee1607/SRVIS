package payment;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    private static DatabaseConnection paymentDB = DatabaseConnection.databaseInstance();

    public static void write(IPayment payment) {
        try {
            Connection con = paymentDB.makeConnection();

            String paymentID = payment.getPaymentID();
            String serviceRequestID = payment.getServiceRequestID();
            String senderID = payment.getSenderID();
            String receiverID = payment.getReceiverID();
            String amount = payment.getAmount();
            String date = payment.getDate();
            String status = payment.getStatusString();

            String createTableQuery = "CREATE TABLE `payment` (\n" +
                    "  `payment_id` varchar(45) NOT NULL,\n" +
                    "  `service_request_id` varchar(45) DEFAULT NULL,\n" +
                    "  `sender_id` varchar(45) DEFAULT NULL,\n" +
                    "  `receiver_id` varchar(45) DEFAULT NULL,\n" +
                    "  `amount` varchar(45) DEFAULT NULL,\n" +
                    "  `date` varchar(45) DEFAULT NULL,\n" +
                    "  `status` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`payment_id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";

            String insertQuery = "INSERT INTO `CSCI5308_3_DEVINT`.`payment`\n" +
                    "(`payment_id`,\n" +
                    "`service_request_id`,\n" +
                    "`sender_id`,\n" +
                    "`receiver_id`,\n" +
                    "`amount`,\n" +
                    "`date`,\n" +
                    "`status`)\n" +
                    "VALUES\n" +
                    "(?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?);";

            con.setAutoCommit(false);
            PreparedStatement insertPayment = con.prepareStatement(insertQuery);
            insertPayment.setString(1, paymentID);
            insertPayment.setString(2, serviceRequestID);
            insertPayment.setString(3, senderID);
            insertPayment.setString(4, receiverID);
            insertPayment.setString(5, amount);
            insertPayment.setString(6, date);
            insertPayment.setString(7, status);

            int result = insertPayment.executeUpdate();

            if (result == 1) {
                con.commit();
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
    }

    public static IPayment read(String paymentID) {
        IPayment payment = null;

        try {
            paymentDB = DatabaseConnection.databaseInstance();
            Connection con = paymentDB.makeConnection();

            String selectQuery = "SELECT * FROM CSCI5308_3_DEVINT.payment WHERE payment_id = ? LIMIT 1;";

            con.setAutoCommit(false);
            PreparedStatement insertPayment = con.prepareStatement(selectQuery);
            insertPayment.setString(1, paymentID);
            ResultSet rs = insertPayment.executeQuery();
            rs.next();
            String serviceRequestID = rs.getString("service_request_id");
            String senderID = rs.getString("sender_id");
            String receiverID = rs.getString("receiver_id");
            String amount = rs.getString("amount");
            String date = rs.getString("date");
            String status = rs.getString("status");

            IPaymentInfo sender = PaymentInfoDAO.read(senderID);
            IPaymentInfo receiver = PaymentInfoDAO.read(receiverID);
            payment = new Payment(paymentID);
            payment.setSender(sender);
            payment.setReceiver(receiver);
            payment.setServiceRequestID(serviceRequestID);
            payment.setAmount(amount);
            payment.setDate(date);
            payment.setStatus(PaymentStatus.valueOf(status));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

}
