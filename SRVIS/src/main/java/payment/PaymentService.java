package payment;

public class PaymentService implements IPaymentService
{
    private final IPaymentInfoDAO paymentInfoDAO;
    private final IPaymentDAO paymentDAO;

    public PaymentService()
    {
        paymentDAO = new PaymentDAO();
        paymentInfoDAO = new PaymentInfoDAO();
    }

    public IPaymentInfo getPaymentInfoFromDatabase(String userID)
    {
        return paymentInfoDAO.read(userID);
    }

    public boolean writePaymentInfoToDatabase(IPaymentInfo paymentInfo)
    {
        return paymentInfoDAO.write(paymentInfo);
    }

    public IPayment getPaymentFromDatabase(int paymentID)
    {
        PaymentDAO paymentDAO = new PaymentDAO();
        return paymentDAO.read(paymentID);
    }

    public boolean processPayment(IPayment payment)
    {
        return paymentDAO.write(payment);
    }
}