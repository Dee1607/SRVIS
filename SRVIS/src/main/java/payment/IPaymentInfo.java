package payment;

public interface IPaymentInfo {
    void setType(String type);
    void setCardNumber(String cardNumber);
    void setFullName(String fullName);
    void setSecurityCode(String securityCode);
    String getType();
    String getCardNumber();
    String getFullName();
    String getSecurityCode();
}
