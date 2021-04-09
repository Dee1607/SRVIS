package payment;

public interface IPayment
{
    boolean isValid();

    void setSender(IPaymentInfo sender);

    IPaymentInfo getSender();

    String getSenderID();

    void setReceiver(IPaymentInfo receiver);

    IPaymentInfo getReceiver();

    String getReceiverID();

    void setAmount(String amount);

    String getAmount();

    void setDate(String date);

    String getDate();

    void setStatus(PaymentStatus status);

    PaymentStatus getStatus();

    String getStatusString();

    void setPaymentID(int paymentID);

    int getPaymentID();

    String getServiceRequestID();

    void setServiceRequestID(String serviceRequestID);
}