package presentationlayer;

import payment.*;

public class PaymentUI {

    private DisplayToGetUserChoice objGetData = null;

    public PaymentUI(){

        objGetData = new DisplayToGetUserChoice();
    }

    public void getPaymentDetailsInput(IPaymentInfo PaymentDetails){
        String paymentType = objGetData.displayMessageGetStringChoiceFromUser("Enter your payment type (DEBIT/MASTERCARD/VISA):");
        PaymentDetails.setPaymentType(PaymentType.valueOf(paymentType));
        String cardNumber = objGetData.displayMessageGetStringChoiceFromUser("Enter your card number: ");
        PaymentDetails.setCardNumber(cardNumber);
        String userId = objGetData.displayMessageGetStringChoiceFromUser("Enter your customer ID : ");
        PaymentDetails.setUserID(userId);
        String fullName = objGetData.displayMessageGetStringChoiceFromUser("Enter your full name: ");
        PaymentDetails.setFullName(fullName);
        String expiryDate = objGetData.displayMessageGetStringChoiceFromUser("Enter your expiry date: ");
        PaymentDetails.setExpiryDate(expiryDate);
        String securityCode = objGetData.displayMessageGetStringChoiceFromUser("Enter your security code : ");
        PaymentDetails.setSecurityCode(securityCode);
    }

}
