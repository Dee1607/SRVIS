package payment;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import java.util.Map;

public class PaymentDAO implements IPaymentDAO
{
    private final IDatabaseConnection db;

    public PaymentDAO()
    {
        db = DatabaseConnection.databaseInstance();
    }

    @Override
    public IPayment read(int paymentID)
    {
        IPayment payment = null;
        String readPaymentQuery = String.format("SELECT * FROM payment WHERE payment_id = %s LIMIT 1;", paymentID);
        db.makeConnection();
        Map<String, Map<String, String>> resultMap = db.selectQuery(readPaymentQuery);
        Map<String, String> tempValues;
        for (String str : resultMap.keySet())
        {
            tempValues = resultMap.get(str);
            String serviceRequestID = tempValues.get("service_request_id");
            String senderID = tempValues.get("sender_id");
            String receiverID = tempValues.get("receiver_id");
            String amount = tempValues.get("amount");
            String date = tempValues.get("date");
            String status = tempValues.get("status");
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
        db.closeConnection();
        return payment;
    }

    @Override
    public boolean write(IPayment payment)
    {
        boolean result;
        String serviceRequestID = payment.getServiceRequestID();
        String senderID = payment.getSenderID();
        String receiverID = payment.getReceiverID();
        String amount = payment.getAmount();
        String date = payment.getDate();
        String status = PaymentStatus.PROCESSED.name();

        String writePaymentQuery = String.format("INSERT INTO `payment`" +
                        "(`service_request_id`,`sender_id`,`receiver_id`,`amount`,`date`,`status`)" +
                        "VALUES('%s','%s','%s','%s','%s','%s');",
                serviceRequestID, senderID, receiverID, amount, date, status);

        db.makeConnection();
        result = db.insertQuery(writePaymentQuery);
        db.closeConnection();
        return result;
    }
}