package payment;

import java.util.Objects;

public class Payment implements IPayment
{
    private int paymentID;
    private String serviceRequestID;
    private IPaymentInfo sender;
    private IPaymentInfo receiver;
    private String amount;
    private String date;
    private PaymentStatus status;

    public boolean isValid()
    {
        boolean senderIsValid = (sender != null);
        boolean receiverIsValid = (receiver != null);
        boolean amountIsValid = (amount != null);

        return senderIsValid && receiverIsValid && amountIsValid;
    }

    public void setSender(IPaymentInfo sender)
    {
        this.sender = sender;
    }

    public IPaymentInfo getSender()
    {
        return sender;
    }

    public String getSenderID()
    {
        return sender.getUserID();
    }

    public void setReceiver(IPaymentInfo receiver)
    {
        this.receiver = receiver;
    }

    public IPaymentInfo getReceiver()
    {
        return receiver;
    }

    public String getReceiverID()
    {
        return receiver.getUserID();
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public void setStatus(PaymentStatus status)
    {
        this.status = status;
    }

    public PaymentStatus getStatus()
    {
        return status;
    }

    public String getStatusString()
    {
        return status.name();
    }

    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
    }

    public int getPaymentID()
    {
        return paymentID;
    }

    public String getServiceRequestID()
    {
        return serviceRequestID;
    }

    public void setServiceRequestID(String serviceRequestID)
    {
        this.serviceRequestID = serviceRequestID;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentID, payment.paymentID) && Objects.equals(serviceRequestID, payment.serviceRequestID) && Objects.equals(sender, payment.sender) && Objects.equals(receiver, payment.receiver) && Objects.equals(amount, payment.amount) && Objects.equals(date, payment.date) && status == payment.status;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(paymentID, serviceRequestID, sender, receiver, amount, date, status);
    }
}