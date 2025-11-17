
# Quick Start Guide - GPA Calculator

## How to Run the Application

### Option 1: Using Maven (Recommended)
```bash
cd Akid_2207018_GPACalculator
mvn clean javafx:run
```

### Option 2: Using IDE
1. Open the project in IntelliJ IDEA or Eclipse
2. Navigate to `src/main/java/com/example/gpa_calculator/Launcher.java`
3. Right-click and select "Run 'Launcher.main()'"

### Option 3: Build and Run JAR
```bash
mvn clean package
java -jar target/GPA_Calculator-1.0-SNAPSHOT.jar
```

## Using the Application

### 1. Home Screen
- Click **"Start GPA Calculator"** button

### 2. Adding Courses
For each course, fill in:
- **Course Name**: e.g., "Data Structures and Algorithms"
- **Course Code**: e.g., "CSE 2101"
- **Course Credit**: e.g., "3.0"
- **Teacher 1 Name**: e.g., "Dr. John Smith"
- **Teacher 2 Name**: Optional, e.g., "Prof. Jane Doe"
- **Grade**: Select from dropdown (A+, A, A-, B+, B, B-, C+, C, C-, D+, D, F)

Click **"Add Course"** after filling the form.

### 3. Managing Courses
- **Delete Course**: Click the "Delete" button on any added course
- **Clear Form**: Click "Clear" to reset the input fields
- Watch the credit counter at the top showing progress toward 15.0 credits

### 4. Calculate GPA
- Add courses until total credits = 15.0
- The **"Calculate GPA"** button becomes active
- Click it to view your results

### 5. View Results
- See your GPA displayed prominently
- View performance description
- Review detailed course table with all information
- Navigate back to add more courses or return home

## Example Usage

### Sample Course Entry:
1. **Course 1**:
   - Name: Data Structures
   - Code: CSE 2101
   - Credit: 3.0
   - Teacher 1: Dr. Alice Johnson
   - Grade: A+

2. **Course 2**:
   - Name: Database Systems
   - Code: CSE 2102
   - Credit: 3.0
   - Teacher 1: Prof. Bob Wilson
   - Grade: A

3. **Course 3**:
   - Name: Software Engineering
   - Code: CSE 2103
   - Credit: 3.0
   - Teacher 1: Dr. Carol Smith
   - Grade: A-

4. **Course 4**:
   - Name: Computer Networks
   - Code: CSE 2104
   - Credit: 3.0
   - Teacher 1: Prof. David Lee
   - Grade: B+

5. **Course 5**:
   - Name: Operating Systems
   - Code: CSE 2105
   - Credit: 3.0
   - Teacher 1: Dr. Emma Davis
   - Grade: A

**Result**: GPA ≈ 3.80 (Excellent Performance!)

## Troubleshooting

### Application won't start
- Ensure Java 21 or higher is installed
- Check Maven is installed: `mvn --version`
- Verify JavaFX dependencies are downloaded

### "Calculate GPA" button disabled
- Make sure total credits equal exactly 15.0
- Check the credit counter at the top of the screen

### Error when adding course
- Verify all required fields are filled
- Ensure credit is a valid number (e.g., 3.0, not "three")
- Check you haven't exceeded the 15.0 credit limit

## Features Summary

✅ **User-Friendly Interface**: Clean, intuitive design  
✅ **Input Validation**: Prevents invalid data entry  
✅ **Real-time Feedback**: Credit tracking and alerts  
✅ **Course Management**: Add and delete courses easily  
✅ **Accurate GPA Calculation**: Weighted by credits  
✅ **Professional Results**: Award-style certificate display  
✅ **Navigation**: Easy movement between screens  

## System Requirements

- **Java**: Version 21 or higher
- **Maven**: Version 3.6 or higher
- **JavaFX**: Version 21 (included in dependencies)
- **Operating System**: Windows, macOS, or Linux

## Support

For issues or questions, contact: Akid (Student ID: 2207018)

---
**Note**: This is an academic project for KUET (Khulna University of Engineering & Technology)
