package mausam.repositories;

import mausam.exceptions.PostTopicException;
import mausam.models.PostTopic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostTopicRepository {

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blogdb", "blogger", "P@ssw0rd");
        System.out.println("CONNECTION TO DB IS MADE");

        return connection;
    }

    public long create(PostTopic newPostTopic) throws SQLException {
        Statement createStatement = getConnection().createStatement();
        String insertQuery = "INSERT INTO postTopic (postId, topicId ) VALUES (" + newPostTopic.getPostId() + ", " + newPostTopic.getTopicId() + ")";
        int rowsAffected = createStatement.executeUpdate(insertQuery);
        if (rowsAffected > 0) {
            return rowsAffected;
        } else {
            return -1;
        }


    }

    public PostTopic read(long id) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT postId,topicId FROM postTopic WHERE postId = " + id;
            ResultSet resultSet = selectStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + " | " + resultSet.getInt("postId") + " | " + resultSet.getInt("topicId"));
            }
            return (PostTopic) resultSet;
        } catch (PostTopicException postTopicException) {
            postTopicException.notFound();
            return null;
        }
    }

    public List<PostTopic> read(PostTopic example) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT postId,topicId FROM postTopic WHERE postId = " + example.getPostId();
            ResultSet resultSet = selectStatement.executeQuery(query);
            List<PostTopic> postTopics = new ArrayList<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                int topicId = resultSet.getInt("topicId");
                PostTopic postTopic = new PostTopic();

                postTopic.setPostId(postId);
                postTopic.setTopicId(topicId);
                postTopics.add(postTopic);

                System.out.println(resultSet.getRow() + " | " + resultSet.getInt("postId") + " | " + resultSet.getInt("topicId"));
            }
            return postTopics;
            // return empty collection if fails
        } catch (PostTopicException postTopicException) {
            postTopicException.notFound();
            return Collections.emptyList();
        }
    }

    public List<PostTopic> read() throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String selectQuery = "SELECT * FROM postlike";
            ResultSet resultSet = selectStatement.executeQuery(selectQuery);
            List<PostTopic> postTopics = new ArrayList<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                int topicId = resultSet.getInt("userId");
                PostTopic postTopic = new PostTopic();
                postTopic.setPostId(postId);
                postTopic.setTopicId(topicId);
                postTopics.add(postTopic);
                System.out.println(resultSet.getRow() + " | " + postId + " | " + topicId);
            }
            return postTopics;
        } catch (PostTopicException postTopicException) {
            postTopicException.notFound();
            return Collections.emptyList();
        }

    }

    public boolean update(long id, PostTopic existingPostTopic) throws SQLException {
        Statement updateStatement = getConnection().createStatement();
        String query = "UPDATE postlike SET postId = " + existingPostTopic.getPostId() + ", topicId = " + existingPostTopic.getTopicId() + " WHERE postId = " + id;

        ResultSet resultSet = updateStatement.executeQuery(query);

        // If at least one row was affected, return true
        if (resultSet.next()) {
            int rowsAffected = resultSet.getInt(1);
            return rowsAffected > 0;
        } else {
            return false;
        }

    }

    public boolean delete(long id) throws SQLException {
        Statement deleteStatement = getConnection().createStatement();
        String query = "DELETE FROM postTopic WHERE postId = " + id;
        int rowsAffected = deleteStatement.executeUpdate(query);

        // If atleast one row is affected, return true;.
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

}


