package payment;

public class PaymentFactory {
    public IPayment createPayment(IPaymentInfo sender, IPaymentInfo receiver, String amount) {
        return new Payment(sender, receiver, amount);
    }
}
