package feedback;

public interface IFeedbackFactory {
    IFeedback createFeedback(String id);

    IReview createReview();
}
