package payment;

public class Transaction implements ITransaction{
    private IPaymentInfo sender;
    private IPaymentInfo receiver;
    private String amount;

    public void setSender(IPaymentInfo sender) {
        this.sender = sender;
    }

    public void setReceiver(IPaymentInfo receiver) {
        this.receiver = receiver;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public IPaymentInfo getSender() {
        return sender;
    }

    public IPaymentInfo getReceiver() {
        return receiver;
    }

    public String getAmount() {
        return amount;
    }
}