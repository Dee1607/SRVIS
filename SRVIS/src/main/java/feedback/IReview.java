package feedback;

public interface IReview
{
    void setReviewString(String reviewString);

    void setAuthor(String author);

    void setReviewee(String reviewee);

    void setDate(String date);

    String getReviewString();

    String getAuthor();

    String getDate();

    String getReviewee();
}
