package payment;

public interface IPaymentInfoDAO
{
    IPaymentInfo read(String userID);

    boolean write(IPaymentInfo paymentInfo);
}
