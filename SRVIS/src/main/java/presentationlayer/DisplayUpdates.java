package presentationlayer;

public class DisplayUpdates implements IDisplayUpdates {

    @Override
    public void displayMessage(String message)
    {
        System.out.println("=================================================================");
        System.out.println(message);
        System.out.println("=================================================================");
    }
}
