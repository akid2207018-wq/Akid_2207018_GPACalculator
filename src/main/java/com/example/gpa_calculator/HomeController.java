package com.example.gpa_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
    
    @FXML
    private void handleStartCalculator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("course-entry-view.fxml"));
            Parent root = loader.load();
            
            Stage stage = GPACalculatorApp.getPrimaryStage();
            Scene scene = new Scene(root, 900, 700);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator - Course Entry");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
