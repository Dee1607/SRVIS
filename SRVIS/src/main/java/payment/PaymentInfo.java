package payment;

public class PaymentInfo implements IPaymentInfo{
    private PaymentType paymentType;
    private String cardNumber;
    private String fullName;
    private String securityCode;
    private String expiryDate;

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public void getExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PaymentType getType() {
        return paymentType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
