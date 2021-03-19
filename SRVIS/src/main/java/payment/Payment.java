package payment;

public class Payment implements IPayment{

    private PaymentInfo info;
    private String amount;

    public Payment(String amount, PaymentInfo info) {
        this.amount = amount;
        this.info = info;
    }

    boolean validatePayment() {
        return false;
    }
}
