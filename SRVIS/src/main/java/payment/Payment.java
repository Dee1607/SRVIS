package payment;

public class Payment implements IPayment{

    private IPaymentInfo sender;
    private IPaymentInfo receiver;
    private String amount;
    private PaymentStatus status;

    public Payment(IPaymentInfo sender, IPaymentInfo receiver, String amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public boolean validatePayment() {
        boolean senderIsValid;
        boolean receiverIsValid;
        boolean amountIsValid;
        return senderIsValid && receiverIsValid && amountIsValid;
    }

    public boolean processPayment() {
        return false;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}