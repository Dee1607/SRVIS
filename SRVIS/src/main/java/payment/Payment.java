package payment;

public class Payment implements IPayment{

    private IPaymentInfo sender;
    private IPaymentInfo receiver;
    private String amount;
    private PaymentStatus status;
    private String PaymentID;

    public Payment(ITransaction transaction) {
        this.sender = transaction.getSender();
        this.receiver = transaction.getReceiver();
        this.amount = transaction.getAmount();
    }

    public boolean validatePayment() {
        boolean senderIsValid = true;
        boolean receiverIsValid = false;
        boolean amountIsValid = false;

        return senderIsValid && receiverIsValid && amountIsValid;
    }

    public boolean processPayment() {
        // Register in database
        return false;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}