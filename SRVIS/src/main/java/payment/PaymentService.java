package payment;

public class PaymentService {

    private final PaymentInfoDAO paymentInfoDAO;
    private final PaymentDAO paymentDAO;

    public PaymentService() {
        paymentDAO = new PaymentDAO();
        paymentInfoDAO = new PaymentInfoDAO();
    }

    public IPaymentInfo getPaymentInfoFromDatabase(String userID) {
        return paymentInfoDAO.read(userID);
    }
    
    public boolean writePaymentInfoToDatabase(IPaymentInfo paymentInfo) {
        return paymentInfoDAO.write(paymentInfo);
    }

    public IPayment getPaymentFromDatabase(int paymentID) {
        PaymentDAO paymentDAO = new PaymentDAO();
        return paymentDAO.read(paymentID);
    }

    public boolean processPayment(IPayment payment) {
        return paymentDAO.write(payment);
    }
}
