package payment;

public interface IPaymentDAO
{
    IPayment read(int paymentID);

    boolean write(IPayment payment);
}
