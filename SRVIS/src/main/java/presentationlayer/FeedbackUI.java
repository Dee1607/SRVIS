package presentationlayer;

import feedback.IFeedback;
import feedback.IReview;

public class FeedbackUI {

    private final DisplayToGetUserChoice objGetData;

    public FeedbackUI() {

        objGetData = new DisplayToGetUserChoice();
    }

    public void getReviewDetailsInput(IReview review){
        String comment = objGetData.displayMessageGetStringChoiceFromUser("Enter the review :");
        review.setReviewString(comment);
        String setDate = objGetData.displayMessageGetStringChoiceFromUser("Enter the date :");
        review.setDate(setDate);
        String setReviewee = objGetData.displayMessageGetStringChoiceFromUser("Enter the reviewee ");
        review.setReviewee(setReviewee);
        String setAuthor = objGetData.displayMessageGetStringChoiceFromUser("Enter the name of author : ");
        review.setAuthor(setAuthor);
    }


    public void setFeedback(IFeedback feedback){
        String rating = objGetData.displayMessageGetStringChoiceFromUser("Enter the rating (1-5) :");
        feedback.setRating(rating);
    }
}
