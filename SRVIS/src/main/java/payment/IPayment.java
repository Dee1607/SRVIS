package payment;

public interface IPayment {
    boolean validatePayment();
    boolean processPayment();
    PaymentStatus getStatus();
}
