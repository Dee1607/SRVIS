package payment;

public interface IPaymentService {

    public IPaymentInfo getPaymentInfoFromDatabase(String userID);
    public boolean writePaymentInfoToDatabase(IPaymentInfo paymentInfo);
    public IPayment getPaymentFromDatabase(int paymentID);
    public boolean processPayment(IPayment payment);

}
