package payment;

public interface ITransaction {
    void setSender(IPaymentInfo sender);
    void setReceiver(IPaymentInfo receiver);
    void setAmount(String amount);
    IPaymentInfo getSender();
    IPaymentInfo getReceiver();
    String getAmount();
}
