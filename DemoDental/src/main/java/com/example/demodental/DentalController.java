package com.example.demodental;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DentalController {
    @FXML void setData(ActionEvent event) throws InterruptedException, SQLException {
        Stage stage = new Stage();
        TextField cabinetName = new TextField();
        TextField doctorName = new TextField();
        Button btn = new Button("Add");
        cabinetName.setPromptText("Cabinet name");
        doctorName.setPromptText("Doctor name");
        Label label = new Label("INSERT DATA");
        VBox vBox = new VBox(label, cabinetName, doctorName, btn);
        vBox.setPadding(new Insets(10, 50, 50, 50));
        vBox.setSpacing(10);
        EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                ConnectionClass connection1 = new ConnectionClass();
                Connection con = connection1.getConnection();
                String sql = "INSERT INTO `cabinet`(`CABINET NAME`, `DOCTOR NAME`) VALUES ('"+ cabinetName.getText() +"','"+ doctorName.getText() +"')";
                System.out.println(sql);
                Statement statementC = null;
                try {
                    statementC = con.createStatement();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    statementC.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        };
        // when button is pressed
        btn.setOnAction(ev);
        Scene scene = new Scene(vBox);
        stage.setTitle("DENTALFS");
        stage.setResizable(false);
        stage.setWidth(600);
        stage.setHeight(180);
        stage.setX(380);
        stage.setY(300);
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void Appointments(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppointmentsApplication.class.getResource("appointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 650);
        Stage stage = new Stage();
        stage.setTitle("DENTALFS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void Patients(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientsApplication.class.getResource("patients.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 650);
        Stage stage = new Stage();
        stage.setTitle("DENTALFS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void Instruments(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InstrumentsApplication.class.getResource("instruments.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 650);
        Stage stage = new Stage();
        stage.setTitle("DENTALFS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}