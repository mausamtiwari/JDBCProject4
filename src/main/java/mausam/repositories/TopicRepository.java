package mausam.repositories;

import mausam.exceptions.TopicException;
import mausam.models.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopicRepository {

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/blogdb",
                "blogger",
                "P@ssw0rd"
        );
        System.out.println("CONNECTION TO DB IS MADE");

        return connection;
    }

    public long create(Topic newTopic) throws SQLException {

        String query = "insert into topic (name,slug) values(?,?)";
        PreparedStatement insertStatement = getConnection().prepareStatement(query);
        insertStatement.setString(1, newTopic.getName());
        insertStatement.setString(2, newTopic.getSlug());
        return insertStatement.executeUpdate();
    }

    public Topic read(long id) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT topicId FROM topic WHERE topicId = " + id;
            ResultSet resultSet = selectStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + " | " +
                        resultSet.getInt("postId") + " | " +
                        resultSet.getInt("userid"));
            }
            return (Topic) resultSet;
        } catch (TopicException topicException) {
            topicException.notFound();
            return null;
        }


    }

    public List<Topic> read(Topic example) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT * FROM topic WHERE topicId = " + example.getTopicId();
            ResultSet resultSet = selectStatement.executeQuery(query);
            List<Topic> topics = new ArrayList<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("topicId");
                String name = resultSet.getString("name");
                String slug = resultSet.getString("slug");
                Topic topic = new Topic();

                topic.setTopicId(postId);
                topic.setName(name);
                topic.setSlug(slug);
                topics.add((Topic) topics);

                System.out.println(resultSet.getRow() + " | " +
                        resultSet.getInt("userId") + " | " +
                        resultSet.getInt("title") + " | " +
                        resultSet.getInt("slug") + " | " +
                        resultSet.getInt("image") + " | " +
                        resultSet.getInt("body") + " | " +
                        resultSet.getInt("published"));
            }
            return topics;
        } catch (TopicException topicException) {
            topicException.notFound();
            return Collections.emptyList();
        }

    }

    public List<Topic> read() throws SQLException {
        Statement selectStatement = getConnection().createStatement();
        List<Topic> topicList = new ArrayList<>();
        String query = "select name,slug from topic";
        ResultSet result = selectStatement.executeQuery(query);
        while (result.next()) {
            Topic topic = new Topic();
            topic.setName(result.getString("name"));
            topic.setSlug(result.getString("slug"));
            topicList.add(topic);
        }

        return topicList;
    }

    public int update(int id, Topic existingTopic) throws SQLException {

        String query = "Update topic Set name=?,slug=? where topicId =?";
        PreparedStatement updateStatement = getConnection().prepareStatement(query);
        updateStatement.setString(1, existingTopic.getName());
        updateStatement.setString(2, existingTopic.getSlug());
        updateStatement.setLong(3, id);
        return updateStatement.executeUpdate();

    }

    public int delete(int id) throws SQLException {

        String query = "Delete from topic where topicId=?";
        PreparedStatement deleteStatement = getConnection().prepareStatement(query);
        deleteStatement.setInt(1, id);
        return deleteStatement.executeUpdate();
    }

}
