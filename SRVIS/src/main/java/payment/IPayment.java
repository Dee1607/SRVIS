package payment;

public interface IPayment {
    boolean isValid();
    void processPayment();
    PaymentStatus getStatus();
}
