package payment;

public interface IPaymentService
{
    IPaymentInfo getPaymentInfoFromDatabase(String userID);

    boolean writePaymentInfoToDatabase(IPaymentInfo paymentInfo);

    IPayment getPaymentFromDatabase(int paymentID);

    boolean processPayment(IPayment payment);
}
