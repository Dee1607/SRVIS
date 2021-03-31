package feedback;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackDAO {

    public static IFeedback read(String id) {
        IFeedback feedback = null;
        try {
            Database db = Database.databaseInstance();
            Connection con = db.makeConnection();

            String selectQuery = "SELECT `feedback`.`feedback_id`,\n" +
                    "    `feedback`.`rating`,\n" +
                    "    `feedback`.`review`,\n" +
                    "    `feedback`.`author`,\n" +
                    "    `feedback`.`reviewee`,\n" +
                    "    `feedback`.`date`\n" +
                    "FROM `CSCI5308_3_DEVINT`.`feedback`\n" +
                    "WHERE `feedback`.`feedback_id` = ?;";
            PreparedStatement readStatement = con.prepareStatement(selectQuery);
            readStatement.setString(1, id);
            ResultSet rs = readStatement.executeQuery();
            if (rs.next()) {
                String rating = rs.getString("rating");
                String reviewString = rs.getString("review");
                String author = rs.getString("author");
                String reviewee = rs.getString("reviewee");
                String date = rs.getString("date");

                IReview review = new Review();
                review.setReviewString(reviewString);
                review.setAuthor(author);
                review.setReviewee(reviewee);
                review.setDate(date);
                feedback = new Feedback(id);
                feedback.setRating(rating);
                feedback.setReview(review);
            }
            con.close();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public static void write(IFeedback feedback) {
        try {
            Database db = Database.databaseInstance();
            Connection con = db.makeConnection();
            con.setAutoCommit(false);

            String id = feedback.getID();
            String rating = feedback.getRating();
            IReview review = feedback.getReview();
            String reviewString = review.getReviewString();
            String author = review.getAuthor();
            String reviewee = review.getReviewee();
            String date = review.getDate();

            String insertQuery = "INSERT INTO `CSCI5308_3_DEVINT`.`feedback`\n" +
                    "(`feedback_id`,\n" +
                    "`rating`,\n" +
                    "`review`,\n" +
                    "`author`,\n" +
                    "`reviewee`,\n" +
                    "`date`)\n" +
                    "VALUES\n" +
                    "(?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?);";

            PreparedStatement insertFeedback = con.prepareStatement(insertQuery);
            insertFeedback.setString(1, id);
            insertFeedback.setString(2, rating);
            insertFeedback.setString(3, reviewString);
            insertFeedback.setString(4, author);
            insertFeedback.setString(5, reviewee);
            insertFeedback.setString(6, date);

            if (insertFeedback.executeUpdate() == 1) {
                con.commit();
            }
            else {
                System.err.println("Error. Transaction is being rolled back");
                con.rollback();
            }
            con.close();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
