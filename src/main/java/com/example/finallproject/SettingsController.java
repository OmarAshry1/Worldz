package com.example.finallproject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SettingsController implements Initializable{
    @FXML
    private ComboBox<String> Edit;
    @FXML
    private TextField edittext;
    @FXML
    private Label mess;
    @FXML
    private Button homepage;
    @FXML
    private Button logout;
    @FXML
    private Button profilepage;
    @FXML
    private Button settings;
    @FXML
    private Button submit;
    @FXML
    private Label titlefield;
    @FXML
    private Text agefield;

    @FXML
    private Text agetitle;

    @FXML
    private Text datefield;

    @FXML
    private Text datetitle;

    @FXML
    private Text genderfield;

    @FXML
    private Text gendertitle;

    @FXML
    private Text mailfield;

    @FXML
    private Text mailtitle;

    @FXML
    private Text passfield;

    @FXML
    private Text passtitle;

    @FXML
    private Text phonefield;

    @FXML
    private Text phonetitle;

    @FXML
    private Text userfield;

    @FXML
    private Text usertitle;

    @FXML
    private Text modifyfield;

    @FXML
    private Text biofield;

    @FXML
    private Text biotitle;

    @FXML
    private Button changephoto;

    @FXML
    private ImageView photo;

    @FXML
    private Text photofield;

    @FXML
    private Text IDfield;

    @FXML
    private Text IDtitle;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Edit.setItems(FXCollections.observableArrayList("Email","Password",
                "Username","Phone number","Bio"));
        mailfield.setText(LoginController.u.getemail());
        passfield.setText(LoginController.u.getpassword());
        userfield.setText(LoginController.u.getname());
        phonefield.setText(LoginController.u.getphonenumber());
        biofield.setText(LoginController.u.getbio());
        datefield.setText(LoginController.u.getbirth());
        agefield.setText(String.valueOf(LoginController.u.getage()));
        genderfield.setText(LoginController.u.getgender());
        IDfield.setText(String.valueOf(LoginController.u.getID()));
        byte[] imageData = loadImage(LoginController.u.getemail());
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            photo.setImage(image);
        }
    }

    @FXML
    void submit (ActionEvent event) throws SQLException {
        String setter = edittext.getText();
        Boolean mailfound = false;
        if(setter.isEmpty()){
            mess.setText("Please fill in the field");
        } else {
            if (Edit.getValue() == "Email") {
                Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
                PreparedStatement p = connection.prepareStatement("SELECT email FROM users");
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    if(Objects.equals(setter, rs.getString("email"))){
                        mailfound = true;
                    }
                }
                if(mailfound == true){
                    mess.setText("email already exist!");
                }
                else {
                    LoginController.u.setemail(setter);
                    mailfield.setText(LoginController.u.getemail());
                    mess.setText("Successfully Edited your" + Edit.getValue() + " .");
                }
            }
            if (Edit.getValue().equals("Password")) {
                if(setter.length()<8){
                    mess.setText("please enter a password longer than 8 chars");
                }
                else if(setter.equals(LoginController.u.getpassword())){
                    mess.setText("please enter a different password than your old one");
                }
                else{
                    LoginController.u.setpassword(setter);
                    passfield.setText(LoginController.u.getpassword());
                    mess.setText("Successfully Edited your" + Edit.getValue() + " .");
                }
            }
            if (Edit.getValue().equals("Username")) {
                if(setter.equals(LoginController.u.getname())){
                    mess.setText("please enter a different username than your old one");
                }
                else {
                    LoginController.u.setname(setter);
                    userfield.setText(LoginController.u.getname());
                    mess.setText("Successfully Edited your" + Edit.getValue() + " .");
                }
            }
            if (Edit.getValue().equals("Phone number")) {
                if(setter.length() != 11 || setter.charAt(0) != '0' || setter.charAt(1) != '1'
                        || setter.charAt(2) != '0' && setter.charAt(2) != '1' && setter.charAt(2) != '2'
                        && setter.charAt(2) != '5'){
                    mess.setText("please enter a valid phone number");
                }
                else if(setter.equals(LoginController.u.getphonenumber())){
                    mess.setText("please enter a different number than your old one");
                }
                else {
                    LoginController.u.setphonenumber(setter);
                    phonefield.setText(LoginController.u.getphonenumber());
                    mess.setText("Successfully Edited your" + Edit.getValue() + " .");
                }
            }
            if (Edit.getValue().equals("Bio")) {
                if(setter.equals(LoginController.u.getbio())){
                    mess.setText("please enter a different bio than your old one");
                }
                else {
                    LoginController.u.setbirth(setter);
                    biofield.setText(LoginController.u.getbio());
                    mess.setText("Successfully Edited your" + Edit.getValue() + " .");
                }
            }
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
    void switchtoprofile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profilepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changePicOnAction(ActionEvent event) {
        // Open a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");
        // Set filters to only allow image files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );


        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (selectedFile != null) {
            try {
                byte[] imageData = new byte[(int) selectedFile.length()];
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                fileInputStream.read(imageData);
                fileInputStream.close();
                saveImage(imageData);
                Image image = new Image(selectedFile.toURI().toString());
                photo.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImage(byte[] imageData) {

        try {
            Connection connection = DriverManager.getConnection(database.path, database.user, database.pass);
            String s = "UPDATE users SET profilepic = ? WHERE email = ?";
            PreparedStatement p = connection.prepareStatement(s);
            p.setBytes(1, imageData);
            p.setString(2, LoginController.u.getemail());
            p.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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



}

