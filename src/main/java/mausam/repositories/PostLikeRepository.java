package mausam.repositories;

import mausam.exceptions.PostLikeException;
import mausam.models.PostLike;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostLikeRepository {

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/blogdb",
                "blogger",
                "P@ssw0rd"
        );
        System.out.println("CONNECTION TO DB IS MADE");

        return connection;
    }

    public long create(PostLike newPostLike) throws SQLException {
        Statement createStatement = getConnection().createStatement();
        String insertQuery = "INSERT INTO postlike (postId, userId) VALUES (" +
                newPostLike.getPostId() + ", " + newPostLike.getUserId() + ")";
        int rowsAffected = createStatement.executeUpdate(insertQuery);
        if (rowsAffected > 0) {
            return rowsAffected;
        } else {
            return -1;
        }

    }

    public PostLike read(long id) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT postId,userId FROM postlike WHERE postId = " + id;
            ResultSet resultSet = selectStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + " | " +
                        resultSet.getInt("postId") + " | " +
                        resultSet.getInt("userid"));
            }
            return (PostLike) resultSet;
        } catch (PostLikeException postLikeException) {
            postLikeException.notFound();
            return null;
        }

    }

    public List<PostLike> read(PostLike example) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();

            String query = "SELECT postId,userId FROM postlike WHERE postId = " + example.getPostId();
            ResultSet resultSet = selectStatement.executeQuery(query);
            List<PostLike> postLikes = new ArrayList<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                int userId = resultSet.getInt("userId");
                PostLike postLike = new PostLike();

                postLike.setPostId(postId);
                postLike.setUserId(userId);
                postLikes.add(postLike);

                System.out.println(resultSet.getRow() + " | " +
                        resultSet.getInt("postId") + " | " +
                        resultSet.getInt("userid"));
            }
            return postLikes;
        } catch (PostLikeException postLikeException) {
            postLikeException.notFound();
            return Collections.emptyList();
        }

    }


    public List<PostLike> read() throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String selectQuery = "SELECT * FROM postlike";
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            List<PostLike> postLikes = new ArrayList<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                int userId = resultSet.getInt("userId");
                PostLike postLike = new PostLike();
                postLike.setPostId(postId);
                postLike.setUserId(userId);
                postLikes.add(postLike);
                System.out.println(resultSet.getRow() + " | " + postId + " | " + userId);
            }
            return postLikes;
        } catch (PostLikeException postLikeException) {
            postLikeException.notFound();
            return Collections.emptyList();
        }
    }

    public boolean update(long id, PostLike existingPostLike) throws SQLException {
        Statement updateStatement = getConnection().createStatement();
        String query = "UPDATE postlike SET postId = " + existingPostLike.getPostId() +
                ", userId = " + existingPostLike.getUserId() + " WHERE postId = " + id;
        ResultSet resultSet = updateStatement.executeQuery(query);

        // If at least one row was affected, return true
        if (resultSet.next()) {
            int rowsAffected = resultSet.getInt(1);
            return rowsAffected > 0;
        } else {
            // Return false if the result set is empty;
            return false;
        }

    }


    public boolean delete(long id) throws SQLException {

        Statement deleteStatement = getConnection().createStatement();
        String query = "DELETE FROM postlike WHERE postId = " + id;
        int rowsAffected = deleteStatement.executeUpdate(query);
        // If atleast one row is affected, return true;.
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }

    }

}
