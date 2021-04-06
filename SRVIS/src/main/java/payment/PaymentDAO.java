package payment;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import java.util.Map;

public class PaymentDAO {

    private IDatabaseConnection db = DatabaseConnection.databaseInstance();

    public IPayment read(int paymentID) {
        String serviceRequestID;
        String senderID;
        String receiverID;
        String amount;
        String date;
        String status;

        IPayment payment = null;
        String readPaymentQuery = String.format("SELECT * FROM CSCI5308_3_DEVINT.payment WHERE payment_id = %s LIMIT 1;", paymentID);
        db.makeConnection();
        Map<String, Map<String, String>> resultMap = db.selectQuery(readPaymentQuery);
        Map<String, String> tempValues;
        for (String str : resultMap.keySet())
        {
            tempValues = resultMap.get(str);
            serviceRequestID = tempValues.get("service_request_id");
            senderID = tempValues.get("sender_id");
            receiverID = tempValues.get("receiver_id");
            amount = tempValues.get("amount");
            date = tempValues.get("date");
            status = tempValues.get("status");
            PaymentInfoDAO paymentInfoDAO = new PaymentInfoDAO();
            IPaymentInfo sender = paymentInfoDAO.read(senderID);
            IPaymentInfo receiver = paymentInfoDAO.read(receiverID);
            payment = new Payment();
            payment.setPaymentID(paymentID);
            payment.setServiceRequestID(serviceRequestID);
            payment.setSender(sender);
            payment.setReceiver(receiver);
            payment.setAmount(amount);
            payment.setDate(date);
            payment.setStatus(PaymentStatus.valueOf(status));
        }
        return payment;
    }

    public boolean write(IPayment payment) {
        boolean result = false;
        String serviceRequestID = payment.getServiceRequestID();
        String senderID = payment.getSenderID();
        String receiverID = payment.getReceiverID();
        String amount = payment.getAmount();
        String date = payment.getDate();
        String status = payment.getStatusString();
        String writePaymentQuery = String.format("INSERT INTO `CSCI5308_3_DEVINT`.`payment`" +
                "(`service_request_id`,`sender_id`,`receiver_id`,`amount`,`date`,`status`)" +
                "VALUES('%s','%s','%s','%s','%s','%s');",
                serviceRequestID, senderID, receiverID, amount, date, status);
        db.makeConnection();
        result = db.insertQuery(writePaymentQuery);
        db.closeConnection();
        return result;
    }

}
