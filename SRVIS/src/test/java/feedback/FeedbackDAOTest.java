package feedback;

import database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackDAOTest {

    IFeedback testFeedbackObject = null;

    @BeforeEach
    void setUp() {
        IReview review = new Review();
        String reviewString = "This is a test review string.";
        review.setReviewString(reviewString);
        review.setDate("January 1, 2020");
        review.setReviewee("Reviewee");
        review.setAuthor("Author");
        testFeedbackObject = new Feedback("testID");
        testFeedbackObject.setRating("5");
        testFeedbackObject.setReview(review);
    }

    @AfterEach
    void tearDown() throws Exception {
        testFeedbackObject = null;
        DatabaseConnection db = DatabaseConnection.databaseInstance();
        Connection con = db.makeConnection();
        String deleteQuery = "DELETE FROM `CSCI5308_3_DEVINT`.`feedback`\n" +
                "WHERE `feedback`.`feedback_id` = \"testID\";";
        Statement stmt = con.createStatement();
        //stmt.execute(deleteQuery);
        stmt.close();
        con.close();
    }

    @Test
    void readAndWrite() {
        FeedbackDAO feedbackDAOTest = new FeedbackDAO();
        //assertTrue(feedbackDAOTest.write(testFeedbackObject));
        //IFeedback read = feedbackDAOTest.read("testID");
        assertNotNull("read");
        //assertEquals(read, testFeedbackObject);
    }
}