package com.example.finallproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class commentcontroller {

    @FXML
    private Label Comment1;

    @FXML
    private Label Comment2;

    @FXML
    private Label Comment3;

    @FXML
    private Label Comment4;

    @FXML
    private Button back3;

    @FXML
    private Button newcomment;

    @FXML
    private TextArea newcommentfield;

    @FXML
    private Text post1datefield;

    @FXML
    private Text post1likesfield;

    @FXML
    private Text postdateincomment;

    @FXML
    private Text postincomment;

    @FXML
    private Text postlikesincomment;

    @FXML
    private Label titlefield;

    @FXML
    private Button refreshcomments;

    @FXML
    private Button back4;


    int commentscount;

    int commentcount = 0;

    List<comment> comments;

    int first = 0;
    int second = 1;
    int third = 2;
    int fourth = 3;

    public void initialize() throws SQLException {
        comments= new ArrayList<>();
        String postdata = null;
        String postdate = null;
        int postlikes = 0;
        int postid = dashboardcontroller.posttype;
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement post = connection.prepareStatement("SELECT * FROM posts WHERE post_id=?");
        post.setInt(1, postid);
        ResultSet rpost = post.executeQuery();
        while (rpost.next()) {
            postdata = rpost.getString("post_content");
            postdate = rpost.getString("date");
            postlikes = rpost.getInt("likes");
        }
        postincomment.setText(postdata);
        postdateincomment.setText(postdate);
        postlikesincomment.setText(Integer.toString(postlikes));
        PreparedStatement p = connection.prepareStatement("SELECT post_id FROM comments");
        ResultSet rs = p.executeQuery();
        int id;
        while(rs.next()){
            id=rs.getInt("post_id");
            if(id == postid){
                PreparedStatement pnew = connection.prepareStatement("SELECT * FROM comments where post_id = ?");
                pnew.setInt(1, postid);
                ResultSet rsnew = pnew.executeQuery();
                String commentdata = null;
                String commentor = null;
                while (rsnew.next()) {
                    commentdata = rsnew.getString("commentdata");
                    commentor = rsnew.getString("commentorname");
                    comment c = new comment(commentdata,commentor);
                    comments.add(c);
                    commentcount++;
                }

            }
        }

        if(commentcount != 0) {
            Comment1.setText(comments.get(first).getCommentorname() + " : " +comments.get(first).getData());
            commentcount--;
            if(commentcount != 0) {
                Comment2.setText(comments.get(second).getCommentorname() + " : " +comments.get(second).getData());
                commentcount--;
                if(commentcount != 0) {
                    Comment3.setText(comments.get(third).getCommentorname() + " : " +comments.get(third).getData());
                    commentcount--;
                    if(commentcount != 0) {
                        Comment4.setText(comments.get(fourth).getCommentorname() + " : " +comments.get(fourth).getData());
                        commentcount--;
                    }
                }

            }

        }

    }

    @FXML
    void publishnewcomment(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("INSERT INTO comments(post_id,commentdata,commentorname) VALUES (?,?,?)");
        p.setInt(1, dashboardcontroller.posttype);
        p.setString(2, newcommentfield.getText());
        p.setString(3, LoginController.u.getname());
        p.executeUpdate();
        newcommentfield.clear();
        connection.close();
        Parent root = FXMLLoader.load(getClass().getResource("comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchtodashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchtoprofileposts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("myposts.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void refreshnewcomments(ActionEvent event) {
        first = first + 4;
        second = second + 4;
        third = third + 4;
        fourth = fourth + 4;
        if(commentcount != 0) {
            Comment1.setText(comments.get(first).getCommentorname() + " : " +comments.get(first).getData());
            commentcount--;
            if(commentcount != 0) {
                Comment2.setText(comments.get(second).getCommentorname() + " : " +comments.get(second).getData());
                commentcount--;
                if(commentcount != 0) {
                    Comment3.setText(comments.get(third).getCommentorname() + " : " +comments.get(third).getData());
                    commentcount--;
                    if(commentcount != 0) {
                        Comment4.setText(comments.get(fourth).getCommentorname() + " : " +comments.get(fourth).getData());
                        commentcount--;
                    }
                }

            }

        }
    }


}
