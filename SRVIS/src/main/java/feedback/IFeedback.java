package feedback;

public interface IFeedback
{
    void setRating(String rating);

    void setReview(IReview review);

    void setID(String id);

    String getID();

    IReview getReview();

    String getRating();
}
