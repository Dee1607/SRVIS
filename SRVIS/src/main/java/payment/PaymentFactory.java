package payment;

public class PaymentFactory {
    public IPayment createPayment(ITransaction transaction) {
        return new Payment(transaction);
    }
}
