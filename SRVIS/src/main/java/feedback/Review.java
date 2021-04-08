package feedback;

import java.util.Objects;

public class Review implements IReview {
    private String reviewString;
    private String author;
    private String reviewee;
    private String date;

    @Override
    public void setReviewString(String reviewString) {
        this.reviewString = reviewString;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void setReviewee(String reviewee) {
        this.reviewee = reviewee;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getReviewString() {
        return reviewString;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getReviewee() {
        return reviewee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(reviewString, review.reviewString) && Objects.equals(author, review.author) && Objects.equals(reviewee, review.reviewee) && Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewString, author, reviewee, date);
    }
}
