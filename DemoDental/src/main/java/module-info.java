module com.example.demodental {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.demodental to javafx.fxml;
    exports com.example.demodental;
}