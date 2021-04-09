package feedback;

import java.util.Objects;

public class Feedback implements IFeedback
{
    private String id;
    private IReview review;
    private String rating;

    public Feedback(String id)
    {
        this.id = id;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public void setReview(IReview review)
    {
        this.review = review;
    }

    public void setID(String id)
    {
        this.id = id;
    }

    public String getID()
    {
        return id;
    }

    public IReview getReview()
    {
        return review;
    }

    public String getRating()
    {
        return rating;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(review, feedback.review) && Objects.equals(rating, feedback.rating);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, review, rating);
    }
}