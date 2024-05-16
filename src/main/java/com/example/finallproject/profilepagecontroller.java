package com.example.finallproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class profilepagecontroller {

    @FXML
    private Button homepage;

    @FXML
    private Button logout;

    @FXML
    private Button FriendList;

    @FXML
    private Button profilepage;

    @FXML
    private Button settings;

    @FXML
    private Label titlefield;

    @FXML
    private Text username;

    @FXML
    private Text bio;
    @FXML
    private ImageView profilepicture;

    @FXML
    private Button posts;




    public void initialize() {
        username.setText("@" + LoginController.u.getname());
        bio.setText(LoginController.u.getbio());
        byte[] imageData = loadImage(LoginController.u.getemail());
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            profilepicture.setImage(image);
        }
    }

    @FXML
    void switchtohomepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    void switchtosettingspage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openfriendlist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("friends.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private byte[] loadImage(String email) {
        try {
            Connection connection = DriverManager.getConnection(database.path, database.user, database.pass);
            String s = "SELECT profilepic FROM users WHERE email = ?";
            PreparedStatement p = connection.prepareStatement(s);
            p.setString(1, email);
            ResultSet resultSet = p.executeQuery();
            if (resultSet.next()) {

                return resultSet.getBytes("profilepic");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    void openposts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("myposts.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
