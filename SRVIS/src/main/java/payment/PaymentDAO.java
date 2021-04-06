package payment;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    private static DatabaseConnection paymentDB = DatabaseConnection.databaseInstance();

    public static boolean write(IPayment payment) {
        Connection con = null;
        try {
            con = paymentDB.makeConnection();

            String paymentID = payment.getPaymentID();
            String serviceRequestID = payment.getServiceRequestID();
            String senderID = payment.getSenderID();
            String receiverID = payment.getReceiverID();
            String amount = payment.getAmount();
            String date = payment.getDate();
            String status = payment.getStatusString();

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
                return true;
            }
            else {
                System.err.println("Error. Transaction is being rolled back");
                con.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                con.close();
                paymentDB.closeConnection();
                paymentDB = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
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
