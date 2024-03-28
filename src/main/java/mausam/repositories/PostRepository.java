package mausam.repositories;

import mausam.exceptions.PostException;
import mausam.exceptions.PostTopicException;
import mausam.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostRepository {

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/blogdb",
                "blogger",
                "P@ssw0rd"
        );
        System.out.println("CONNECTION TO DB IS MADE");

        return connection;
    }

    public long create(Post newPost) throws SQLException {
//        Statement createStatement = getConnection().createStatement();
//        String query = "INSERT INTO post " +
//                "(user_id, title) " +
//                "VALUES " +
//                "('" + newPost.getUserId() + "', " +
//                "'" + newPost.getTitle() + "')";
////        statement.executeUpdate() -> creates a record, or updates a record.
//        return createStatement.executeUpdate(query);
//        //Statement createStatement = getConnection().createStatement();
        String query = "insert into post (userId, title,slug,image,body,published) values (?, ?,?,?,?,?)";
        PreparedStatement insertStatement = getConnection().prepareStatement(query);

        insertStatement.setLong(1, newPost.getUserId());
        insertStatement.setString(2, newPost.getTitle());
        insertStatement.setString(3, newPost.getSlug());
        insertStatement.setString(4, newPost.getImage());
        insertStatement.setString(5, newPost.getBody());
        insertStatement.setLong(6, newPost.getPublished());

        return insertStatement.executeUpdate();
    }


    public Post read(long id) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT * FROM post WHERE postId = " + id;
            ResultSet resultSet = selectStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getRow() + " | " +
                        resultSet.getInt("userId") + " | " +
                        resultSet.getInt("title") + " | " +
                        resultSet.getInt("slug") + " | " +
                        resultSet.getInt("image") + " | " +
                        resultSet.getInt("body") + " | " +
                        resultSet.getInt("published"));
            }
            return (Post) resultSet;
        } catch (PostException postException) {
            postException.notFound();
            return null;
        }

    }

    public List<Post> read(Post example) throws SQLException {
        try {
            Statement selectStatement = getConnection().createStatement();

            String query = "SELECT postId,userId FROM post WHERE postId = " + example.getPostId();
            ResultSet resultSet = selectStatement.executeQuery(query);
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                int userId = resultSet.getInt("userId");
                Post post = new Post();

                post.setPostId(postId);
                post.setUserId(userId);
                posts.add((Post) posts);

                System.out.println(resultSet.getRow() + " | " +
                        resultSet.getInt("userId") + " | " +
                        resultSet.getInt("title") + " | " +
                        resultSet.getInt("slug") + " | " +
                        resultSet.getInt("image") + " | " +
                        resultSet.getInt("body") + " | " +
                        resultSet.getInt("published"));
            }
            return posts;
        } catch (PostException postException) {
            postException.notFound();
            return Collections.emptyList();
        }
    }

    public List<Post> read() throws SQLException {
        List<Post> postList = new ArrayList<>();
        Statement selectStatement = getConnection().createStatement();

        String query = "SELECT postId,userId,title FROM POST";
        ResultSet result = selectStatement.executeQuery(query);

        while (result.next()) {
            Post post = new Post();
            post.setPostId(result.getLong("id"));
            post.setUserId(result.getLong("userId"));
            post.setTitle(result.getString("title"));
            postList.add(post);
        }
        return postList;
    }


    public boolean update(long id, Post existingPost) throws SQLException {

        Statement updateStatement = getConnection().createStatement();
        String query = "UPDATE post SET postId = " + existingPost.getPostId() +
                ", userId = " + existingPost.getUserId() + " WHERE postId = " + id;

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
        String query = "DELETE FROM post WHERE postId = " + id;
        int rowsAffected = deleteStatement.executeUpdate(query);

        // If atleast one row is affected, return true;.
        // return false if fails to delete.
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

}
