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
import javafx.stage.Stage;

import java.io.IOException;

public class friendscontroller {

    @FXML
    private Button addbutton;

    @FXML
    private Button back5;

    @FXML
    private TextArea friendlist;

    @FXML
    private Label namefield;

    @FXML
    private TextField searchbar;

    @FXML
    private Label titlefield;

    @FXML
    void addfriend(ActionEvent event) {

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
