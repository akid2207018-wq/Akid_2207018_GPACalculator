package com.example.gpa_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GPACalculatorApp extends Application {
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(GPACalculatorApp.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("GPA Calculator - Home");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(750);
        stage.setMinHeight(550);
        stage.show();
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
