package feedback;

public class FeedbackFactory implements IFeedbackFactory {
    @Override
    public IFeedback createFeedback(String id) {
        return new Feedback(id);
    }

    @Override
    public IReview createReview() {
        return new Review();
    }
}