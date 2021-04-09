package payment;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import java.util.Map;

public class PaymentInfoDAO implements IPaymentInfoDAO
{
    private final IDatabaseConnection db;

    public PaymentInfoDAO()
    {
        db = DatabaseConnection.databaseInstance();
    }

    @Override
    public IPaymentInfo read(String userID)
    {
        IPaymentInfo paymentInfo = null;
        String paymentType;
        String cardNumber;
        String fullName;
        String securityCode;
        String expiryDate;
        String readPaymentQuery = String.format("SELECT * FROM payment_info WHERE user_id = '%s';", userID);
        db.makeConnection();
        Map<String, Map<String, String>> resultMap = db.selectQuery(readPaymentQuery);
        Map<String, String> tempValues;
        if(resultMap==null)
        {
            return null;
        }
        else
        {
            for (String str : resultMap.keySet())
            {
                tempValues = resultMap.get(str);
                paymentType = tempValues.get("payment_type");
                cardNumber = tempValues.get("card_number");
                fullName = tempValues.get("full_name");
                securityCode = tempValues.get("security_code");
                expiryDate = tempValues.get("expiry_date");
                paymentInfo = new PaymentInfo();
                paymentInfo.setUserID(userID);
                paymentInfo.setPaymentType(PaymentType.valueOf(paymentType));
                paymentInfo.setCardNumber(cardNumber);
                paymentInfo.setFullName(fullName);
                paymentInfo.setSecurityCode(securityCode);
                paymentInfo.setExpiryDate(expiryDate);
            }
        }
        db.closeConnection();
        return paymentInfo;
    }

    @Override
    public boolean write(IPaymentInfo paymentInfo)
    {
        boolean result;
        String userID = paymentInfo.getUserID();
        String paymentType = paymentInfo.getPaymentType();
        String cardNumber = paymentInfo.getCardNumber();
        String fullName = paymentInfo.getFullName();
        String securityCode = paymentInfo.getSecurityCode();
        String expiryDate = paymentInfo.getExpiryDate();

        String writePaymentQuery = String.format("INSERT INTO `payment_info`" +
                        "(`user_id`,`payment_type`,`card_number`,`full_name`,`security_code`,`expiry_date`)" +
                        "VALUES('%s','%s','%s','%s','%s','%s');",
                userID, paymentType, cardNumber, fullName, securityCode, expiryDate);

        db.makeConnection();
        result = db.insertQuery(writePaymentQuery);
        db.closeConnection();
        return result;
    }
}