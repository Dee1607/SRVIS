package payment;

import java.util.Objects;

public class PaymentInfo implements IPaymentInfo
{
    private String userID;
    private PaymentType paymentType;
    private String cardNumber;
    private String fullName;
    private String securityCode;
    private String expiryDate;

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public void setPaymentType(PaymentType paymentType)
    {
        this.paymentType = paymentType;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public void setSecurityCode(String securityCode)
    {
        this.securityCode = securityCode;
    }

    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    public String getUserID()
    {
        return userID;
    }

    public String getPaymentType()
    {
        return paymentType.name();
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getSecurityCode()
    {
        return securityCode;
    }

    public String getExpiryDate()
    {
        return expiryDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentInfo that = (PaymentInfo) o;
        return Objects.equals(userID, that.userID) && paymentType == that.paymentType && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(fullName, that.fullName) && Objects.equals(securityCode, that.securityCode) && Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userID, paymentType, cardNumber, fullName, securityCode, expiryDate);
    }
}