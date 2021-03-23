package feedback;

public class Review implements IReview{
    private String reviewString;
    private String author;
    private String reviewee;
    private String date;

    public void setReviewString(String reviewString) {
        this.reviewString = reviewString;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReviewee(String reviewee) {
        this.reviewee = reviewee;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReviewString() {
        return reviewString;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getReviewee() {
        return reviewee;
    }
}
