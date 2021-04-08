package feedback;

import database.IDatabaseConnection;

import java.util.Map;

public class FeedbackDAO implements IFeedbackDAO {

    private final IDatabaseConnection db;

    public FeedbackDAO() {
        db = IDatabaseConnection.databaseInstance();
    }

    @Override
    public IFeedback read(String id) {
        IFeedback feedback = null;
        try {
            String readFeedbackQuery = String.format("SELECT `feedback`.`feedback_id`,`feedback`.`rating`,`feedback`.`review`,`feedback`.`author`,`feedback`.`reviewee`,`feedback`.`date`" +
                    "FROM `CSCI5308_3_DEVINT`.`feedback`" +
                    "WHERE `feedback`.`feedback_id` = '%s';", id);
            db.makeConnection();
            Map<String, Map<String, String>> resultMap = db.selectQuery(readFeedbackQuery);
            Map<String, String> tempValues;
            for (String str : resultMap.keySet()) {
                tempValues = resultMap.get(str);
                String rating = tempValues.get("rating");
                String reviewString = tempValues.get("review");
                String author = tempValues.get("author");
                String reviewee = tempValues.get("reviewee");
                String date = tempValues.get("date");

                feedback = new Feedback(id);
                feedback.setRating(rating);
                IReview review = new Review();
                review.setAuthor(author);
                review.setReviewee(reviewee);
                review.setDate(date);
                review.setReviewString(reviewString);
                feedback.setReview(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return feedback;
    }

    @Override
    public boolean write(IFeedback feedback) {
        boolean result = false;
        try {
            String feedbackID = feedback.getID();
            String rating = feedback.getRating();
            IReview review = feedback.getReview();
            String reviewString = review.getReviewString();
            String author = review.getAuthor();
            String reviewee = review.getReviewee();
            String date = review.getDate();
            String writeFeedbackQuery = String.format("INSERT INTO `CSCI5308_3_DEVINT`.`feedback`(`feedback_id`,`rating`,`review`,`author`,`reviewee`,`date`)" +
                    "VALUES('%s','%s','%s','%s','%s','%s');", feedbackID, rating, reviewString, author, reviewee, date);
            db.makeConnection();
            result = db.insertQuery(writeFeedbackQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
