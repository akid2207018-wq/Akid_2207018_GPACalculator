package com.example.gpa_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CourseEntryController {
    
    @FXML
    private TextField courseNameField;
    
    @FXML
    private TextField courseCodeField;
    
    @FXML
    private TextField courseCreditField;
    
    @FXML
    private TextField teacher1Field;
    
    @FXML
    private TextField teacher2Field;
    
    @FXML
    private ComboBox<String> gradeComboBox;
    
    @FXML
    private VBox coursesList;
    
    @FXML
    private Label creditInfoLabel;
    
    @FXML
    private Label noCoursesLabel;
    
    @FXML
    private Button calculateGPAButton;
    
    private List<Course> courses = new ArrayList<>();
    private double totalCredits = 0.0;
    private double requiredCredits = 15.0;
    
    @FXML
    public void initialize() {
        // Initialize grade combo box
        gradeComboBox.getItems().addAll(GPACalculator.getGradeOptions());
        gradeComboBox.setValue("A+");
        
        updateCreditInfo();
    }
    
    @FXML
    private void handleSetTotalCredits() {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(requiredCredits));
        dialog.setTitle("Set Total Credits");
        dialog.setHeaderText("Configure Required Total Credits");
        dialog.setContentText("Enter required total credits:");
        
        dialog.showAndWait().ifPresent(input -> {
            try {
                double newTotal = Double.parseDouble(input.trim());
                if (newTotal <= 0) {
                    showAlert("Invalid Input", "Total credits must be greater than 0.", Alert.AlertType.ERROR);
                    return;
                }
                
                if (totalCredits > newTotal) {
                    showAlert("Invalid Input", 
                             "New total credits (" + newTotal + ") cannot be less than current total (" + totalCredits + ").",
                             Alert.AlertType.ERROR);
                    return;
                }
                
                requiredCredits = newTotal;
                updateCreditInfo();
                
                // Update Calculate GPA button state
                if (totalCredits >= requiredCredits) {
                    calculateGPAButton.setDisable(false);
                } else {
                    calculateGPAButton.setDisable(true);
                }
                
                showAlert("Success", "Total credits set to " + requiredCredits, Alert.AlertType.INFORMATION);
                
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid number for total credits.", Alert.AlertType.ERROR);
            }
        });
    }
    
    @FXML
    private void handleAddCourse() {
        // Validate inputs
        if (!validateInputs()) {
            return;
        }
        
        try {
            String courseName = courseNameField.getText().trim();
            String courseCode = courseCodeField.getText().trim();
            double courseCredit = Double.parseDouble(courseCreditField.getText().trim());
            String teacher1 = teacher1Field.getText().trim();
            String teacher2 = teacher2Field.getText().trim();
            String grade = gradeComboBox.getValue();
            
            // Check if adding this course would exceed required credits
            if (totalCredits + courseCredit > requiredCredits) {
                showAlert("Credit Limit Exceeded", 
                         "Adding this course would exceed the required credits of " + requiredCredits + ".\n" +
                         "Current total: " + totalCredits + "\n" +
                         "Course credit: " + courseCredit + "\n" +
                         "Please adjust the course credit.",
                         Alert.AlertType.WARNING);
                return;
            }
            
            // Create and add course
            Course course = new Course(courseName, courseCode, courseCredit, teacher1, teacher2, grade);
            courses.add(course);
            totalCredits += courseCredit;
            
            // Add course to display list
            addCourseToList(course, courses.size() - 1);
            
            // Update UI
            noCoursesLabel.setVisible(false);
            updateCreditInfo();
            clearFields();
            
            // Show success message
            showAlert("Course Added", "Course added successfully!", Alert.AlertType.INFORMATION);
            
            // Enable Calculate GPA button if credits met
            if (totalCredits >= requiredCredits) {
                calculateGPAButton.setDisable(false);
            }
            
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for course credit.", Alert.AlertType.ERROR);
        }
    }
    
    private boolean validateInputs() {
        if (courseNameField.getText().trim().isEmpty()) {
            showAlert("Missing Information", "Please enter course name.", Alert.AlertType.WARNING);
            return false;
        }
        
        if (courseCodeField.getText().trim().isEmpty()) {
            showAlert("Missing Information", "Please enter course code.", Alert.AlertType.WARNING);
            return false;
        }
        
        if (courseCreditField.getText().trim().isEmpty()) {
            showAlert("Missing Information", "Please enter course credit.", Alert.AlertType.WARNING);
            return false;
        }
        
        try {
            double credit = Double.parseDouble(courseCreditField.getText().trim());
            if (credit <= 0) {
                showAlert("Invalid Input", "Course credit must be greater than 0.", Alert.AlertType.WARNING);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for course credit.", Alert.AlertType.ERROR);
            return false;
        }
        
        if (teacher1Field.getText().trim().isEmpty()) {
            showAlert("Missing Information", "Please enter at least Teacher 1 name.", Alert.AlertType.WARNING);
            return false;
        }
        
        if (gradeComboBox.getValue() == null) {
            showAlert("Missing Information", "Please select a grade.", Alert.AlertType.WARNING);
            return false;
        }
        
        return true;
    }
    
    private void addCourseToList(Course course, int index) {
        HBox courseBox = new HBox(15);
        courseBox.setAlignment(Pos.CENTER_LEFT);
        courseBox.setStyle("-fx-background-color: #ecf0f1; -fx-padding: 12; -fx-background-radius: 5;");
        
        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        
        Label nameLabel = new Label(course.getCourseCode() + " - " + course.getCourseName());
        nameLabel.setFont(Font.font("System Bold", 14));
        nameLabel.setStyle("-fx-text-fill: #2c3e50;");
        
        Label detailLabel = new Label("Credits: " + course.getCourseCredit() + 
                                     " | Teachers: " + course.getTeacher1Name() + 
                                     (course.getTeacher2Name().isEmpty() ? "" : ", " + course.getTeacher2Name()) +
                                     " | Grade: " + course.getGrade());
        detailLabel.setFont(Font.font(12));
        detailLabel.setStyle("-fx-text-fill: #7f8c8d;");
        
        infoBox.getChildren().addAll(nameLabel, detailLabel);
        
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-cursor: hand;");
        deleteButton.setOnAction(e -> handleDeleteCourse(index, courseBox));
        
        HBox.setMargin(deleteButton, new Insets(0, 0, 0, 20));
        courseBox.getChildren().addAll(infoBox, deleteButton);
        HBox.setHgrow(infoBox, javafx.scene.layout.Priority.ALWAYS);
        
        coursesList.getChildren().add(courseBox);
    }
    
    private void handleDeleteCourse(int index, HBox courseBox) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Course");
        confirmAlert.setContentText("Are you sure you want to delete this course?");
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Course course = courses.get(index);
                totalCredits -= course.getCourseCredit();
                courses.remove(index);
                coursesList.getChildren().remove(courseBox);
                
                if (courses.isEmpty()) {
                    noCoursesLabel.setVisible(true);
                }
                
                updateCreditInfo();
                
                // Disable Calculate GPA button if credits not met
                if (totalCredits < requiredCredits) {
                    calculateGPAButton.setDisable(true);
                }
            }
        });
    }
    
    private void updateCreditInfo() {
        creditInfoLabel.setText(String.format("Total Credits: %.1f / %.1f", totalCredits, requiredCredits));
    }
    
    @FXML
    private void handleClearFields() {
        clearFields();
    }
    
    private void clearFields() {
        courseNameField.clear();
        courseCodeField.clear();
        courseCreditField.clear();
        teacher1Field.clear();
        teacher2Field.clear();
        gradeComboBox.setValue("A+");
        courseNameField.requestFocus();
    }
    
    @FXML
    private void handleCalculateGPA() {
        if (courses.isEmpty()) {
            showAlert("No Courses", "Please add at least one course before calculating GPA.", Alert.AlertType.WARNING);
            return;
        }
        
        if (totalCredits < requiredCredits) {
            showAlert("Insufficient Credits", 
                     "Total credits (" + totalCredits + ") must equal " + requiredCredits + " to calculate GPA.",
                     Alert.AlertType.WARNING);
            return;
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gpa-result-view.fxml"));
            Parent root = loader.load();
            
            GPAResultController controller = loader.getController();
            controller.setCoursesData(courses);
            
            Stage stage = GPACalculatorApp.getPrimaryStage();
            Scene scene = new Scene(root, 900, 750);
            stage.setScene(scene);
            stage.setTitle("GPA Calculator - Results");
            
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load GPA result page.", Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void handleBackToHome() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Exit");
        confirmAlert.setHeaderText("Return to Home");
        confirmAlert.setContentText("Are you sure? All entered course data will be lost.");
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
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
        });
    }
    
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    // Getter for courses (in case needed by other controllers)
    public List<Course> getCourses() {
        return courses;
    }
}
