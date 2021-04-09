package presentationlayer;

public interface IDisplayToGetUserChoice
{
    int displayMessageGetNumberChoiceFromUser(String stringToBeDisplayed);

    String displayMessageGetStringChoiceFromUser(String stringToBeDisplayed);

    void displayMessage(String message);
}
