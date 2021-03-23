package feedback;

public interface IFeedback {
    void setRating(String rating);
    void setReview(IReview review);
    boolean validateReview();
    void publishFeedback();
    IReview getReview();
    String getRating();
}
