package feedback;

public interface IFeedback {
    void setRating(String rating);
    void setReview(IReview review);
    boolean validateReview();
    void publishFeedback();
    String getID();
    IReview getReview();
    String getRating();
}