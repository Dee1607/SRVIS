package feedback;

public interface IFeedbackDAO
{
    IFeedback read(String id);

    boolean write(IFeedback feedback);
}
