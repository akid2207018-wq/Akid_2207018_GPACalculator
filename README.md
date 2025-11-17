# GPA Calculator Application

A comprehensive JavaFX-based Student Grading System that allows students to add courses, record their grades, and calculate their overall GPA interactively.

## Features

### 🏠 Home Screen
- Welcome interface with clear instructions
- "Start GPA Calculator" button to begin the process
- User-friendly design with professional styling

### 📝 Course Entry Screen
- Add multiple courses with detailed information:
  - Course Name
  - Course Code
  - Course Credit (decimal values supported)
  - Teacher 1 Name
  - Teacher 2 Name (optional)
  - Grade (A+, A, A-, B+, B, B-, C+, C, C-, D+, D, F)
- Real-time credit tracking
- Visual list of added courses
- Delete functionality for each course
- Clear form button for easy data entry
- Calculate GPA button (enabled when required credits are met)

### 🎓 GPA Result Screen (Award Form Style)
- Professional certificate-style layout
- Displays calculated GPA with color-coded performance description
- Summary statistics (total courses and credits)
- Detailed course table with all information
- Color-coded grades for visual clarity
- Generation date timestamp
- Navigation back to course entry or home

## Technical Implementation

### Architecture
- **Model**: `Course.java` - Data model for course information
- **Utility**: `GPACalculator.java` - GPA calculation logic
- **Views**: FXML files for each screen (Scene Builder compatible)
- **Controllers**: Separate controller for each view
- **Main Application**: `GPACalculatorApp.java` - Application entry point

### Design Patterns
- **MVC Pattern**: Separation of concerns between model, view, and controller
- **Clean Code**: Meaningful variable and method names
- **Modular Design**: Reusable components and utilities

### Layout & Containers Used
- **BorderPane**: Main structure for header, content, and footer
- **GridPane**: Form layouts with proper alignment
- **VBox/HBox**: Grouping and organizing UI elements
- **ScrollPane**: Handling overflow content

### Features Implemented
✅ Three-screen navigation (Home → Course Entry → GPA Result)  
✅ Weighted GPA calculation (credit × grade points)  
✅ Course deletion functionality  
✅ Real-time credit tracking  
✅ Input validation with user-friendly alerts  
✅ Clear form functionality  
✅ Confirmation dialogs for important actions  
✅ Professional award-style result display  
✅ Color-coded grade visualization  
✅ Responsive layout design  
✅ Scene Builder compatible FXML files  

## How to Run

### Prerequisites
- Java 21 or higher
- Maven

### Running the Application

1. **Open the project in your IDE** (IntelliJ IDEA, Eclipse, VS Code)

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   - Run the `Launcher.java` class (recommended)
   - OR run `GPACalculatorApp.java` class
   - OR use Maven:
     ```bash
     mvn javafx:run
     ```

## Usage Guide

### Step 1: Start the Application
- Launch the application to see the welcome screen
- Click "Start GPA Calculator" to proceed

### Step 2: Add Courses
1. Fill in the course details form:
   - Enter Course Name (e.g., "Data Structures")
   - Enter Course Code (e.g., "CSE 2101")
   - Enter Course Credit (e.g., "3.0")
   - Enter Teacher 1 Name
   - Optionally enter Teacher 2 Name
   - Select Grade from dropdown
2. Click "Add Course" button
3. The course will appear in the "Added Courses" list
4. Repeat until total credits reach 15.0

### Step 3: Calculate GPA
- Once total credits equal 15.0, the "Calculate GPA" button becomes active
- Click the button to view your GPA result

### Step 4: View Results
- View your calculated GPA with performance description
- Review the detailed course table
- Navigate back to add more courses or return home

## GPA Calculation

### Grade Points
- A+, A: 4.0
- A-: 3.7
- B+: 3.3
- B: 3.0
- B-: 2.7
- C+: 2.3
- C: 2.0
- C-: 1.7
- D+: 1.3
- D: 1.0
- F: 0.0

### Formula
```
GPA = Σ(Grade Points × Credits) / Σ(Credits)
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/gpa_calculator/
│   │       ├── Course.java                  # Course model
│   │       ├── GPACalculator.java           # Calculation utility
│   │       ├── GPACalculatorApp.java        # Main application
│   │       ├── Launcher.java                # Application launcher
│   │       ├── HomeController.java          # Home screen controller
│   │       ├── CourseEntryController.java   # Course entry controller
│   │       └── GPAResultController.java     # Result screen controller
│   └── resources/
│       └── com/example/gpa_calculator/
│           ├── home-view.fxml               # Home screen FXML
│           ├── course-entry-view.fxml       # Course entry FXML
│           └── gpa-result-view.fxml         # Result screen FXML
```

## Key Features Details

### Input Validation
- Empty field detection
- Numeric validation for credits
- Credit limit checking (max 15.0)
- Required field enforcement
- User-friendly error messages

### User Experience
- Confirmation dialogs for destructive actions
- Success notifications for course additions
- Clear visual feedback for button states
- Intuitive navigation flow
- Professional color-coded interface

### Design Highlights
- No external CSS files (inline styling in FXML)
- Scene Builder compatible
- Responsive layouts
- Color-coded grades and performance levels
- Professional award certificate design for results

## Author
Student: Akid (2207018)

## License
Academic Project for KUET

## Notes
- The application uses JavaFX 21
- All FXML files are compatible with Scene Builder
- No CSS files are used (styling is inline)
- The required total credits is set to 15.0 by default
- Application supports course deletion and form clearing
