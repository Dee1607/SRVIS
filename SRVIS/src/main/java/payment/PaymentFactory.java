package payment;

public class PaymentFactory
{
    public IPayment createPayment(String paymentID) {
        return new Payment();
    }
}
