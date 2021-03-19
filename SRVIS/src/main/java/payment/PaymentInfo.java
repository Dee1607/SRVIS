package payment;

public class PaymentInfo implements IPaymentInfo{
    private String type;
    private String cardNumber;
    private String fullName;
    private String securityCode;

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
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
}
