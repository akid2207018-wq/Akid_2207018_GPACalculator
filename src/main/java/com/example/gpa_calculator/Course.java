package com.example.gpa_calculator;

public class Course {
    private String courseName;
    private String courseCode;
    private double courseCredit;
    private String teacher1Name;
    private String teacher2Name;
    private String grade;

    public Course(String courseName, String courseCode, double courseCredit, 
                  String teacher1Name, String teacher2Name, String grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.teacher1Name = teacher1Name;
        this.teacher2Name = teacher2Name;
        this.grade = grade;
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public double getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(double courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getTeacher1Name() {
        return teacher1Name;
    }

    public void setTeacher1Name(String teacher1Name) {
        this.teacher1Name = teacher1Name;
    }

    public String getTeacher2Name() {
        return teacher2Name;
    }

    public void setTeacher2Name(String teacher2Name) {
        this.teacher2Name = teacher2Name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + courseCredit + " credits) - Grade: " + grade;
    }
}
