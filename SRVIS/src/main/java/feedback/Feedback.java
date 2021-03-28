package feedback;

public class Feedback implements IFeedback{
    private IReview review;
    private String rating;

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReview(IReview review) {
        this.review = review;
    }

    public boolean validateReview() {
        final int MAX_REVIEW_LENGTH = 500;
        if (review != null) {
            boolean wordCountIsValid = (review.getReviewString().length() <= MAX_REVIEW_LENGTH);
            boolean dateIsValid = (review.getDate() != null);
            return wordCountIsValid && dateIsValid;
        }
        return false;
    }

    public void publishFeedback() {
        // Store feedback to database
    }

    public IReview getReview() {
        return review;
    }

    public String getRating() {
        return rating;
    }
}
