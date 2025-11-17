package com.example.gpa_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GPAResultController {
    
    @FXML
    private Label gpaLabel;
    
    @FXML
    private Label gpaDescriptionLabel;
    
    @FXML
    private Label totalCoursesLabel;
    
    @FXML
    private Label totalCreditsLabel;
    
    @FXML
    private Label dateLabel;
    
    @FXML
    private VBox coursesDetailList;
    
    private List<Course> courses;
    
    public void setCoursesData(List<Course> courses) {
        this.courses = courses;
        displayResults();
    }
    
    private void displayResults() {
        // Calculate GPA
        double gpa = GPACalculator.calculateGPA(courses);
        
        // Display GPA
        gpaLabel.setText(String.format("%.2f", gpa));
        
        // Set GPA description based on value
        String description = getGPADescription(gpa);
        gpaDescriptionLabel.setText(description);
        
        // Display totals
        totalCoursesLabel.setText(String.valueOf(courses.size()));
        
        double totalCredit = courses.stream()
                .mapToDouble(Course::getCourseCredit)
                .sum();
        totalCreditsLabel.setText(String.format("%.1f", totalCredit));
        
        // Display date
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        dateLabel.setText("Generated on: " + today.format(formatter));
        
        // Display course details
        displayCourseDetails();
    }
    
    private void displayCourseDetails() {
        coursesDetailList.getChildren().clear();
        
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            GridPane courseRow = createCourseRow(course, i);
            coursesDetailList.getChildren().add(courseRow);
        }
    }
    
    private GridPane createCourseRow(Course course, int index) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(12));
        
        // Alternate row colors
        if (index % 2 == 0) {
            gridPane.setStyle("-fx-background-color: #f9f9f9; -fx-background-radius: 5;");
        } else {
            gridPane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        }
        
        // Column constraints (same as header)
        gridPane.getColumnConstraints().addAll(
            createColumnConstraint(15.0, 80.0),
            createColumnConstraint(30.0, 150.0),
            createColumnConstraint(15.0, 100.0),
            createColumnConstraint(20.0, 120.0),
            createColumnConstraint(10.0, 80.0),
            createColumnConstraint(10.0, 80.0)
        );
        
        // Course Code
        Label codeLabel = new Label(course.getCourseCode());
        codeLabel.setFont(Font.font("System Bold", 13));
        codeLabel.setStyle("-fx-text-fill: #2c3e50;");
        gridPane.add(codeLabel, 0, 0);
        
        // Course Name
        Label nameLabel = new Label(course.getCourseName());
        nameLabel.setFont(Font.font(13));
        nameLabel.setStyle("-fx-text-fill: #34495e;");
        nameLabel.setWrapText(true);
        gridPane.add(nameLabel, 1, 0);
        
        // Teacher 1
        Label teacher1Label = new Label(course.getTeacher1Name());
        teacher1Label.setFont(Font.font(12));
        teacher1Label.setStyle("-fx-text-fill: #7f8c8d;");
        teacher1Label.setWrapText(true);
        gridPane.add(teacher1Label, 2, 0);
        
        // Teacher 2
        String teacher2Text = course.getTeacher2Name().isEmpty() ? "-" : course.getTeacher2Name();
        Label teacher2Label = new Label(teacher2Text);
        teacher2Label.setFont(Font.font(12));
        teacher2Label.setStyle("-fx-text-fill: #7f8c8d;");
        teacher2Label.setWrapText(true);
        gridPane.add(teacher2Label, 3, 0);
        
        // Credits
        Label creditLabel = new Label(String.format("%.1f", course.getCourseCredit()));
        creditLabel.setFont(Font.font("System Bold", 13));
        creditLabel.setStyle("-fx-text-fill: #2980b9;");
        gridPane.add(creditLabel, 4, 0);
        
        // Grade
        Label gradeLabel = new Label(course.getGrade());
        gradeLabel.setFont(Font.font("System Bold", 14));
        gradeLabel.setStyle("-fx-text-fill: " + getGradeColor(course.getGrade()) + ";");
        gridPane.add(gradeLabel, 5, 0);
        
        return gridPane;
    }
    
    private javafx.scene.layout.ColumnConstraints createColumnConstraint(double percentWidth, double minWidth) {
        javafx.scene.layout.ColumnConstraints constraint = new javafx.scene.layout.ColumnConstraints();
        constraint.setPercentWidth(percentWidth);
        constraint.setMinWidth(minWidth);
        constraint.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        return constraint;
    }
    
    private String getGPADescription(double gpa) {
        if (gpa >= 3.7) {
            return "Excellent Performance!";
        } else if (gpa >= 3.3) {
            return "Very Good Performance!";
        } else if (gpa >= 3.0) {
            return "Good Performance!";
        } else if (gpa >= 2.7) {
            return "Above Average Performance";
        } else if (gpa >= 2.0) {
            return "Average Performance";
        } else {
            return "Needs Improvement";
        }
    }
    
    private String getGradeColor(String grade) {
        switch (grade.toUpperCase()) {
            case "A+":
            case "A":
                return "#27ae60";  // Green
            case "A-":
            case "B+":
                return "#16a085";  // Teal
            case "B":
            case "B-":
                return "#f39c12";  // Orange
            case "C+":
            case "C":
                return "#e67e22";  // Dark orange
            case "C-":
            case "D+":
            case "D":
                return "#d35400";  // Red-orange
            case "F":
                return "#c0392b";  // Red
            default:
                return "#7f8c8d";  // Gray
        }
    }
    
    @FXML
    private void handleBackToCourseEntry() {
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
    
    @FXML
    private void handleBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Parent root = loader.load();
            
            Stage stage = GPACalculatorApp.getPrimaryStage();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator - Home");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
