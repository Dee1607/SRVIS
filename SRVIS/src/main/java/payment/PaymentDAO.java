package payment;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class PaymentDAO {
    Database paymentDB = Database.databaseInstance();

    public Map<String, Map<String,String>> AppLogin(String user, String password, String type) throws Exception {
        Connection conn = paymentDB.makeConnection();

        String sql1 = "SELECT * FROM CSCI5308_3_DEVINT."+type+" where Email='" + user + "' and Password='" + password + "'";
        Map<String,Map<String,String>> queryResult= paymentDB.selectQuery(sql1);
        paymentDB.closeConnection();
        return queryResult;
    }

    public boolean write(Payment payment) {
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

            if (result == 7) {
                con.commit();
                con.close();
                paymentDB.closeConnection();
                paymentDB = null;
                return true;
            }
            else {
                System.err.print("Error. Transaction is being rolled back");
                con.rollback();
                con.close();
                paymentDB.closeConnection();
                paymentDB = null;
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Payment read() {

        return null;
    }

}
