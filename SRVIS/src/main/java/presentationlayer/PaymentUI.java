package presentationlayer;

import payment.*;

public class PaymentUI {

    private DisplayToGetUserChoice objGetData=null;

    public PaymentUI(){

        objGetData=new DisplayToGetUserChoice();
    }

    public void getPaymentSenderDetailsInput(IPaymentInfo senderPaymentDetails){

        String paymentType = objGetData.displayMessageGetStringChoiceFromUser("Enter your payment gateway (MASTERCARD/VISA):");
        senderPaymentDetails.setPaymentType(PaymentType.valueOf(paymentType));
        String cardNumber = objGetData.displayMessageGetStringChoiceFromUser("Enter your card number: ");
        senderPaymentDetails.setCardNumber(cardNumber);
        String userId = objGetData.displayMessageGetStringChoiceFromUser("Enter your customer ID : ");
        senderPaymentDetails.setUserID(userId);
        String fullname = objGetData.displayMessageGetStringChoiceFromUser("Enter your full name: ");
        senderPaymentDetails.setFullName(fullname);
        String expirydate = objGetData.displayMessageGetStringChoiceFromUser("Enter your expiry date: ");
        senderPaymentDetails.setExpiryDate(expirydate);
        String securityCode = objGetData.displayMessageGetStringChoiceFromUser("Enter your security code : ");
        senderPaymentDetails.setSecurityCode(securityCode);

    }

    public void getPaymentReceiverDetailsInput(IPaymentInfo receiverPaymentDetails){

        String paymentType = objGetData.displayMessageGetStringChoiceFromUser("Enter your payment gateway (MASTERCARD/VISA):");
        receiverPaymentDetails.setPaymentType(PaymentType.valueOf(paymentType));
        String cardNumber = objGetData.displayMessageGetStringChoiceFromUser("Enter your card number:");
        receiverPaymentDetails.setCardNumber(cardNumber);
        String userId = objGetData.displayMessageGetStringChoiceFromUser("Enter your service provider ID :");
        receiverPaymentDetails.setUserID(userId);
        String fullName = objGetData.displayMessageGetStringChoiceFromUser("Enter your full name:");
        receiverPaymentDetails.setFullName(fullName);
        String expiryDate = objGetData.displayMessageGetStringChoiceFromUser("Enter your expiry date:");
        receiverPaymentDetails.setExpiryDate(expiryDate);
        String securityCode = objGetData.displayMessageGetStringChoiceFromUser("Enter your security code : ");
        receiverPaymentDetails.setSecurityCode(securityCode);

    }

    public void addPaymentProcessInput(IPaymentInfo senderPaymentDetails,IPaymentInfo receiverPaymentDetails){

//        IPayment paymentTestObject = new Payment("PaymentID");
//        paymentTestObject.setSender(senderPaymentDetails);
//        paymentTestObject.setReceiver(receiverPaymentDetails);
//        paymentTestObject.setAmount("100");
//        paymentTestObject.setStatus(PaymentStatus.PENDING);
//        paymentTestObject.setDate("2021/12/12");
//        paymentTestObject.setServiceRequestID("ServiceID");
//        PaymentDAO.write(paymentTestObject);
    }
}
