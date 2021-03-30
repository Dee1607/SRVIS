//package feedback;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class FeedbackTest {
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @Test
//    public void setRating() {
//        IFeedback feedback = new Feedback();
//        String rating = "5";
//        feedback.setRating(rating);
//        assertEquals(rating, feedback.getRating());
//    }
//
//    @Test
//    public void setReview() {
//        IFeedback feedback = new Feedback();
//        String reviewString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//        IReview review = new Review();
//        review.setReviewString(reviewString);
//        feedback.setReview(review);
//        assertEquals(review.getReviewString(), feedback.getReview().getReviewString());
//        assertEquals(review, feedback.getReview());
//    }
//
//    @Test
//    public void validateReview() {
//        IFeedback feedback = new Feedback();
//        String reviewString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//        IReview review = new Review();
//        review.setReviewString(reviewString);
//        review.setDate("January 1, 2020");
//        feedback.setReview(review);
//        Assertions.assertTrue(feedback.validateReview());
//    }
//
//    @Test
//    public void publishFeedback() {
//    }
//
//    @Test
//    public void getReview() {
//    }
//
//    @Test
//    public void getRating() {
//    }
//}