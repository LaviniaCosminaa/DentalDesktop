package com.example.demodental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;

public class InstrumentsApplication  extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientsApplication.class.getResource("instruments.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 650);
        stage.setTitle("DENTALFS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}
