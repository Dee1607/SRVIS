package payment;

public interface IPaymentInfo
{
    void setUserID(String userID);

    void setPaymentType(PaymentType paymentType);

    void setCardNumber(String cardNumber);

    void setFullName(String fullName);

    void setSecurityCode(String securityCode);

    void setExpiryDate(String expiryDate);

    String getUserID();

    String getPaymentType();

    String getCardNumber();

    String getFullName();

    String getSecurityCode();

    String getExpiryDate();
}