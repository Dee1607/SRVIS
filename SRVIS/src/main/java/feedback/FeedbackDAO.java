package feedback;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import payment.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FeedbackDAO {

    private final IDatabaseConnection db = DatabaseConnection.databaseInstance();

    public IFeedback read(String id) {
        String rating;
        String reviewString;
        String author;
        String reviewee;
        String date;

        IFeedback feedback = null;

        try{
            String readFeedbackQuery = String.format("SELECT `feedback`.`feedback_id`,`feedback`.`rating`,`feedback`.`review`,`feedback`.`author`,`feedback`.`reviewee`,`feedback`.`date`" +
                    "FROM `CSCI5308_3_DEVINT`.`feedback`" +
                    "WHERE `feedback`.`feedback_id` = '%s';", id);
            db.makeConnection();
            Map<String, Map<String, String>> resultMap = db.selectQuery(readFeedbackQuery);
            Map<String, String> tempValues;
            for (String str : resultMap.keySet())
            {
                tempValues = resultMap.get(str);
                rating = tempValues.get("rating");
                reviewString = tempValues.get("review");
                author = tempValues.get("author");
                reviewee = tempValues.get("reviewee");
                date = tempValues.get("date");

                feedback = new Feedback(id);
                feedback.setRating(rating);
                IReview review = new Review();
                review.setAuthor(author);
                review.setReviewee(reviewee);
                review.setDate(date);
                review.setReviewString(reviewString);
                feedback.setReview(review);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                db.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return feedback;
    }

    public boolean write(IFeedback feedback) {
        boolean result = false;
        try{
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
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                db.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }
}
