package com.example.finallproject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finallproject.database.calculateAge;

public class RegisterController implements Initializable{

    @FXML
    private ComboBox<String> gender = new ComboBox<String>();
    @FXML
    private TextField bio;

    @FXML
    private TextField birthday;

    @FXML
    private TextField email;

    @FXML
    private Button login2;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone_number;

    @FXML
    private Text reg;

    @FXML
    private Button register2;

    @FXML
    private TextField username;

    @FXML
    void register(ActionEvent event) throws SQLException {

        Boolean mailfound = false;
        Boolean falsenum = false;
        Boolean wrongdate = false;
        Boolean undage = false;
        Boolean shortpass = false;
        String mail = email.getText();
        Connection connection = DriverManager.getConnection(database.path,database.user,database.pass);
        PreparedStatement p = connection.prepareStatement("SELECT email FROM users");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            if(Objects.equals(mail, rs.getString("email"))){
                mailfound = true;
            }
        }
        String user = username.getText();
        String pass = password.getText();
        if(pass.length()<8){
            shortpass = true;
        }
        String phone = phone_number.getText();
        if(phone.length() != 11 || phone.charAt(0) != '0' || phone.charAt(1) != '1'
                || phone.charAt(2) != '0' && phone.charAt(2) != '1' && phone.charAt(2) != '2' && phone.charAt(2) != '5'){
            falsenum = true;
        }
        String gen = gender.getValue();
        String birth = birthday.getText();
        int age=0;
        try {
            LocalDate dateOfBirth = LocalDate.parse(birth);
            age = database.calculateAge(dateOfBirth);
        }
        catch(DateTimeParseException e){
            wrongdate = true;
        }
        if(age<18){
            undage = true;
        }
        String b = bio.getText();
        if(mail.isEmpty() || user.isEmpty() || pass.isEmpty() || phone.isEmpty() || birth.isEmpty()){
            reg.setText("please enter the missing fields");
        }
        else if(mailfound == true){
            reg.setText("email already exist!");
        } else if (shortpass) {
            reg.setText("please enter a password longer than 8 chars");
        } else if(falsenum == true){
            reg.setText("please enter a valid phone number");
        }
        else if(wrongdate == true){
            reg.setText("please enter a valid date format");
        }
        else if(undage == true){
            reg.setText("you must be above 18 years to register");
        }
        else {
            reg.setText("Registration complete login to enjoy!");
            database.Register(mail, pass, user, phone, gen, birth, b);
        }
    }
    @FXML
    void switchtologin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> gen = FXCollections.observableArrayList("M","F");
        gender.setItems(gen);
    }
}