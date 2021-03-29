package payment;

public class Payment implements IPayment{

    private String paymentID;
    private String serviceRequestID;
    private IPaymentInfo sender;
    private IPaymentInfo receiver;
    private String amount;
    private String date;
    private PaymentStatus status;

    public Payment(String paymentID) {
        this.paymentID = paymentID;
    }

    public boolean isValid() {
        boolean senderIsValid = true;
        boolean receiverIsValid = false;
        boolean amountIsValid = false;

        return senderIsValid && receiverIsValid && amountIsValid;
    }

    public void processPayment() {
        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.write(this);
    }

    public void setSender(IPaymentInfo sender) {
        this.sender = sender;
    }

    public IPaymentInfo getSender() {
        return sender;
    }

    public String getSenderID () {
        return sender.getUserID();
    }

    public void setReceiver(IPaymentInfo receiver) {
        this.receiver = receiver;
    }

    public IPaymentInfo getReceiver() {
        return receiver;
    }

    public String getReceiverID () {
        return receiver.getUserID();
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getStatusString() {
        return status.toString();
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getServiceRequestID() {
        return serviceRequestID;
    }

    public void setServiceRequestID(String serviceRequestID) {
        this.serviceRequestID = serviceRequestID;
    }
}