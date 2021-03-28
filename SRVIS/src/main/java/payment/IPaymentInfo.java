package payment;

public interface IPaymentInfo {
    void setPaymentType(PaymentType paymentType);
    void setCardNumber(String cardNumber);
    void setFullName(String fullName);
    void setSecurityCode(String securityCode);
    void getExpiryDate(String expiryDate);
    PaymentType getType();
    String getCardNumber();
    String getFullName();
    String getSecurityCode();
    String getExpiryDate();
}
