package feedback;

public interface IFeedback {
    void setRating(String rating);
    void setReview(String review);
    void setAuthor(String author);
    void setDate(String date);
    void validateReview();
    String getReview();
    String getRating();
}
