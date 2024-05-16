module com.example.finallproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.example.finallproject to javafx.fxml;
    exports com.example.finallproject;
}