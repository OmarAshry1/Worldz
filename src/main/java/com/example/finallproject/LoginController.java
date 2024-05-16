package com.example.finallproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginController  {
        public static user u;
        @FXML
        private TextField email;

        @FXML
        private Button login;

        @FXML
        private Text nomail;

        @FXML
        private PasswordField password;

        @FXML
        private Button register;

        @FXML
        void login(ActionEvent event) throws SQLException, IOException {
            String mail = email.getText();
            String pass = password.getText();
            database.login(mail, pass);
            if(mail.isEmpty() || pass.isEmpty()){
                nomail.setText("Please enter the missing fields");
            }
            else if (database.verpass == null) {
                nomail.setText("Email doesn't exist");
            }
            else if (!pass.equals(database.verpass)) {
                nomail.setText("Wrong password!");
            }
            else{
                Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
                PreparedStatement p = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
                p.setString(1,mail);
                ResultSet rs = p.executeQuery();
                int id = 0;
                String user = null;
                String phone = null;
                String gender = null;
                String birth = null;
                int age = 0;
                String bio = null;
                if (rs.next()) {
                    id = rs.getInt("id");
                    user = rs.getString("username");
                    phone = rs.getString("phone_number");
                    gender = rs.getString("gender");
                    birth = rs.getString("birthday");
                    age = rs.getInt("age");
                    bio = rs.getString("bio");
                }
                u = new user(id,mail,pass,user,phone,gender,birth,age,bio);
                switchtohomepage(event);
            }
        }

        public void switchtohomepage(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @FXML
        void switchtoregister(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }















