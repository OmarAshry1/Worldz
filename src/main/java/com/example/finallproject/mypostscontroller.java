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
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class mypostscontroller {

    @FXML
    private Button back;

    @FXML
    private Label namefield;

    @FXML
    private Button newpost;

    @FXML
    private Button post1comments;

    @FXML
    private Text post1datefield;

    @FXML
    private Text post1datefield1;

    @FXML
    private Text post1datetitle;

    @FXML
    private Text post1;

    @FXML
    private Text post1likesfield;

    @FXML
    private Text post1likestitle;

    @FXML
    private Text post2;

    @FXML
    private Button post2comments;

    @FXML
    private Text post2datetitle;

    @FXML
    private Text post2datefield;

    @FXML
    private Text post2likesfield;

    @FXML
    private Text post2likestitle;

    @FXML
    private Text post3;

    @FXML
    private Button post3comments;

    @FXML
    private Text post3datefield;

    @FXML
    private Text post3datetitle;

    @FXML
    private Text post3likesfield;

    @FXML
    private Text post4;

    @FXML
    private Button post4comments;

    @FXML
    private Text post4datefield;

    @FXML
    private Text post4datetitle;

    @FXML
    private Text post4likesfield;

    @FXML
    private Text post4likestitle;

    @FXML
    private Text post5;

    @FXML
    private Button post5comments;

    @FXML
    private Text post5datefield;

    @FXML
    private Text post5datetitle;

    @FXML
    private Text post5likesfield;

    @FXML
    private Text post5likestitle;

    @FXML
    private Button refresh;

    @FXML
    private Label titlefield;

    @FXML
    private TextArea newpostfield;

    int posttype = 0;

    int postcount = 0;

    List<post> posts;

    int first = 0;
    int second = 1;
    int third = 2;
    int fourth = 3;
    int fifth = 4;

    public void initialize() throws SQLException {
        posts= new ArrayList<>();
        int userid = LoginController.u.getID();
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT id FROM posts");
        ResultSet rs = p.executeQuery();
        int id;
        while(rs.next()){
            id=rs.getInt("id");
            if(id == userid){
                PreparedStatement pnew = connection.prepareStatement("SELECT * FROM posts where id = ?");
                pnew.setInt(1, userid);
                ResultSet rsnew = pnew.executeQuery();
                String postcontent = null;
                String postdate = null;
                int likescount = 0;
                while (rsnew.next()) {
                    postcontent = rsnew.getString("post_content");
                    likescount = rsnew.getInt("likes");
                    postdate = rsnew.getString("date");
                    post pp = new post(postcontent,postdate,likescount);
                    posts.add(pp);
                    postcount++;
                }

            }
        }

        if(postcount != 0) {
            post1.setText(posts.get(first).getData());
            post1datefield.setText(posts.get(first).getDate());
            post1likesfield.setText(String.valueOf(posts.get(first).getLikescount()));
            postcount--;
            if(postcount != 0) {
                post2.setText(posts.get(second).getData());
                post2datefield.setText(posts.get(second).getDate());
                post2likesfield.setText(String.valueOf(posts.get(second).getLikescount()));
                postcount--;
                if(postcount != 0) {
                    post3.setText(posts.get(third).getData());
                    post3datefield.setText(posts.get(third).getDate());
                    post3likesfield.setText(String.valueOf(posts.get(third).getLikescount()));
                    postcount--;
                    if(postcount != 0) {
                        post4.setText(posts.get(fourth).getData());
                        post4datefield.setText(posts.get(fourth).getDate());
                        post4likesfield.setText(String.valueOf(posts.get(fourth).getLikescount()));
                        postcount--;
                        if(postcount != 0) {
                            post5.setText(posts.get(fifth).getData());
                            post5datefield.setText(posts.get(fifth).getDate());
                            post5likesfield.setText(String.valueOf(posts.get(fifth).getLikescount()));
                            postcount--;
                        }
                    }
                }

            }

        }

    }


    @FXML
    void createnewpost(ActionEvent event) throws SQLException, IOException {
        String post = newpostfield.getText();
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("INSERT INTO posts(post_content,id,likes,date,publishername) VALUES (?,?,?,?,?)");
        p.setString(1,post);
        p.setInt(2, LoginController.u.getID());
        p.setInt(3, 0 );
        p.setString(4, LocalDate.now().toString());
        p.setString(5,LoginController.u.getname());
        p.executeUpdate();
        connection.close();
        newpostfield.clear();
        Parent root = FXMLLoader.load(getClass().getResource("myposts.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openpost1comments(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT post_id FROM posts where post_content = ?");
        p.setString(1, post1.getText());
        ResultSet rs = p.executeQuery();
        while(rs.next()) {
            posttype = rs.getInt("post_id");
        }
        Parent root = FXMLLoader.load(getClass().getResource("comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openpost2comments(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT post_id FROM posts where post_content = ?");
        p.setString(1, post2.getText());
        ResultSet rs = p.executeQuery();
        while(rs.next()) {
            posttype = rs.getInt("post_id");
        }
        Parent root = FXMLLoader.load(getClass().getResource("comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openpost3comments(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT post_id FROM posts where post_content = ?");
        p.setString(1, post3.getText());
        ResultSet rs = p.executeQuery();
        while(rs.next()) {
            posttype = rs.getInt("post_id");
        }
        Parent root = FXMLLoader.load(getClass().getResource("comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openpost4comments(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT post_id FROM posts where post_content = ?");
        p.setString(1, post4.getText());
        ResultSet rs = p.executeQuery();
        while(rs.next()) {
            posttype = rs.getInt("post_id");
        }
        Parent root = FXMLLoader.load(getClass().getResource("comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openpost5comments(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT post_id FROM posts where post_content = ?");
        p.setString(1, post5.getText());
        ResultSet rs = p.executeQuery();
        while(rs.next()) {
            posttype = rs.getInt("post_id");
        }
        Parent root = FXMLLoader.load(getClass().getResource("comment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void refreshnewposts(ActionEvent event) {
        first = first + 5;
        second = second + 5;
        third = third + 5;
        fourth = fourth + 5;
        fifth = fifth + 5;
        if(postcount != 0) {
            post1.setText(posts.get(first).getData());
            post1datefield.setText(posts.get(first).getDate());
            post1likesfield.setText(String.valueOf(posts.get(first).getLikescount()));
            postcount--;
            if(postcount != 0) {
                post2.setText(posts.get(second).getData());
                post2datefield.setText(posts.get(second).getDate());
                post2likesfield.setText(String.valueOf(posts.get(second).getLikescount()));
                postcount--;
                if(postcount != 0) {
                    post3.setText(posts.get(third).getData());
                    post3datefield.setText(posts.get(third).getDate());
                    post3likesfield.setText(String.valueOf(posts.get(third).getLikescount()));
                    postcount--;
                    if(postcount != 0) {
                        post4.setText(posts.get(fourth).getData());
                        post4datefield.setText(posts.get(fourth).getDate());
                        post4likesfield.setText(String.valueOf(posts.get(fourth).getLikescount()));
                        postcount--;
                        if(postcount != 0) {
                            post5.setText(posts.get(fifth).getData());
                            post5datefield.setText(posts.get(fifth).getDate());
                            post5likesfield.setText(String.valueOf(posts.get(fifth).getLikescount()));
                            postcount--;
                        }
                    }

                }

            }

        }
    }

    @FXML
    void switchtoprofilepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profilepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
