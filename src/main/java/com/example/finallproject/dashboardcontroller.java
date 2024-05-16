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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dashboardcontroller {

    @FXML
    private Button homepage;

    @FXML
    private Button logout;

    @FXML
    private Button profilepage;

    @FXML
    private Button settings;

    @FXML
    private Label titlefield;

    @FXML
    private Button likebutton2;

    @FXML
    private Button likebutton3;

    @FXML
    private Button likebutton4;

    @FXML
    private Button likebutton5;

    @FXML
    private Button post1like;

    @FXML
    private Text post1;

    @FXML
    private Button post1comment;

    @FXML
    private Text post1datefield;

    @FXML
    private Text post1likesfield;

    @FXML
    private Text post1userfield;

    @FXML
    private Text post2;

    @FXML
    private Button post2comment;

    @FXML
    private Text post2datefield;

    @FXML
    private Text post2likesfield;

    @FXML
    private Text post2userfield;

    @FXML
    private Text post3;

    @FXML
    private Button post3comment;

    @FXML
    private Text post3datefield;

    @FXML
    private Text post3likesfield;

    @FXML
    private Text post3userfield;

    @FXML
    private Text post4;

    @FXML
    private Button post4comment;

    @FXML
    private Text post4datefield;

    @FXML
    private Text post4likesfield;

    @FXML
    private Text post4userfield;

    @FXML
    private Text post5;

    @FXML
    private Button post5comment;

    @FXML
    private Text post5datefield;

    @FXML
    private Text post5likesfield;

    @FXML
    private Text post5userfield;

    static int posttype;

    int postcount = 0;

    List<post> posts;

    int first = 0;
    int second = 1;
    int third = 2;
    int fourth = 3;
    int fifth = 4;

    public void initialize() throws SQLException {
        posts= new ArrayList<>();
        String postcontent = null;
        String postdate = null;
        int likescount = 0;
        String publishername = null;
        int userid = 0;
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT * FROM posts");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            postcontent = rs.getString("post_content");
            likescount = rs.getInt("likes");
            postdate = rs.getString("date");
            publishername = rs.getString("publishername");
            post pp = new post(postcontent,postdate,likescount,publishername);
            posts.add(pp);
            postcount++;
        }

        if(postcount != 0) {
            post1.setText(posts.get(first).getData());
            post1datefield.setText(posts.get(first).getDate());
            post1likesfield.setText(String.valueOf(posts.get(first).getLikescount()));
            post1userfield.setText(posts.get(first).getUsername());
            postcount--;
            if(postcount != 0) {
                post2.setText(posts.get(second).getData());
                post2datefield.setText(posts.get(second).getDate());
                post2likesfield.setText(String.valueOf(posts.get(second).getLikescount()));
                post2userfield.setText(posts.get(second).getUsername());
                postcount--;
                if(postcount != 0) {
                    post3.setText(posts.get(third).getData());
                    post3datefield.setText(posts.get(third).getDate());
                    post3likesfield.setText(String.valueOf(posts.get(third).getLikescount()));
                    post3userfield.setText(posts.get(third).getUsername());
                    postcount--;
                    if(postcount != 0) {
                        post4.setText(posts.get(fourth).getData());
                        post4datefield.setText(posts.get(fourth).getDate());
                        post4likesfield.setText(String.valueOf(posts.get(fourth).getLikescount()));
                        post4userfield.setText(posts.get(fourth).getUsername());
                        postcount--;
                        if(postcount != 0) {
                            post5.setText(posts.get(fifth).getData());
                            post5datefield.setText(posts.get(fifth).getDate());
                            post5likesfield.setText(String.valueOf(posts.get(fifth).getLikescount()));
                            post5userfield.setText(posts.get(fifth).getUsername());
                            postcount--;
                        }
                    }
                }

            }

        }

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
            post1userfield.setText(posts.get(first).getUsername());
            postcount--;
            if(postcount != 0) {
                post2.setText(posts.get(second).getData());
                post2datefield.setText(posts.get(second).getDate());
                post2likesfield.setText(String.valueOf(posts.get(second).getLikescount()));
                post2userfield.setText(posts.get(second).getUsername());
                postcount--;
                if(postcount != 0) {
                    post3.setText(posts.get(third).getData());
                    post3datefield.setText(posts.get(third).getDate());
                    post3likesfield.setText(String.valueOf(posts.get(third).getLikescount()));
                    post3userfield.setText(posts.get(third).getUsername());
                    postcount--;
                    if(postcount != 0) {
                        post4.setText(posts.get(fourth).getData());
                        post4datefield.setText(posts.get(fourth).getDate());
                        post4likesfield.setText(String.valueOf(posts.get(fourth).getLikescount()));
                        post4userfield.setText(posts.get(fourth).getUsername());
                        postcount--;
                        if(postcount != 0) {
                            post5.setText(posts.get(fifth).getData());
                            post5datefield.setText(posts.get(fifth).getDate());
                            post5likesfield.setText(String.valueOf(posts.get(fifth).getLikescount()));
                            post5userfield.setText(posts.get(fifth).getUsername());
                            postcount--;
                        }
                    }

                }

            }

        }
    }

    @FXML
    void switchtologinpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchtoprofile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profilepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchtosettingspage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void likepost1(ActionEvent event) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT likes FROM posts where post_content = ?");
        p.setString(1, post1.getText());
        ResultSet rs = p.executeQuery();
        int likescount = 0;
        while(rs.next()) {
            likescount = rs.getInt("likes");
        }
        likescount++;
        post1likesfield.setText(String.valueOf(likescount));
        PreparedStatement pnew = connection.prepareStatement("UPDATE posts SET likes =  ? WHERE post_content = ?");
        pnew.setInt(1,likescount);
        pnew.setString(2,post1.getText());
        pnew.executeUpdate();
    }

    @FXML
    void likepost2(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT likes FROM posts where post_content = ?");
        p.setString(1, post2.getText());
        ResultSet rs = p.executeQuery();
        int likescount = 0;
        while(rs.next()) {
            likescount = rs.getInt("likes");
        }
        likescount++;
        post2likesfield.setText(String.valueOf(likescount));
        PreparedStatement pnew = connection.prepareStatement("UPDATE posts SET likes =  ? WHERE post_content = ?");
        pnew.setInt(1,likescount);
        pnew.setString(2,post2.getText());
        pnew.executeUpdate();
    }

    @FXML
    void likepost3(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT likes FROM posts where post_content = ?");
        p.setString(1, post3.getText());
        ResultSet rs = p.executeQuery();
        int likescount = 0;
        while(rs.next()) {
            likescount = rs.getInt("likes");
        }
        likescount++;
        post3likesfield.setText(String.valueOf(likescount));
        PreparedStatement pnew = connection.prepareStatement("UPDATE posts SET likes =  ? WHERE post_content = ?");
        pnew.setInt(1,likescount);
        pnew.setString(2,post3.getText());
        pnew.executeUpdate();
    }

    @FXML
    void likepost4(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT likes FROM posts where post_content = ?");
        p.setString(1, post4.getText());
        ResultSet rs = p.executeQuery();
        int likescount = 0;
        while(rs.next()) {
            likescount = rs.getInt("likes");
        }
        likescount++;
        post4likesfield.setText(String.valueOf(likescount));
        PreparedStatement pnew = connection.prepareStatement("UPDATE posts SET likes =  ? WHERE post_content = ?");
        pnew.setInt(1,likescount);
        pnew.setString(2,post4.getText());
        pnew.executeUpdate();
    }

    @FXML
    void likepost5(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT likes FROM posts where post_content = ?");
        p.setString(1, post5.getText());
        ResultSet rs = p.executeQuery();
        int likescount = 0;
        while(rs.next()) {
            likescount = rs.getInt("likes");
        }
        likescount++;
        post5likesfield.setText(String.valueOf(likescount));
        PreparedStatement pnew = connection.prepareStatement("UPDATE posts SET likes =  ? WHERE post_content = ?");
        pnew.setInt(1,likescount);
        pnew.setString(2,post5.getText());
        pnew.executeUpdate();
    }

    @FXML
    void commentpost1(ActionEvent event) throws IOException, SQLException {
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
    void commentpost2(ActionEvent event) throws IOException, SQLException {
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
    void commentpost3(ActionEvent event) throws IOException, SQLException {
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
    void commentpost4(ActionEvent event) throws IOException, SQLException {
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
    void commentpost5(ActionEvent event) throws IOException, SQLException {
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




}
