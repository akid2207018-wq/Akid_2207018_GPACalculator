# Testing Guide - GPA Calculator Application

## 🧪 Complete Testing Checklist

### Pre-Testing Setup
1. Ensure Java 21+ is installed: `java -version`
2. Ensure Maven is installed: `mvn --version`
3. Navigate to project directory
4. Build the project: `mvn clean install`

---

## Test Suite 1: Application Startup

### Test 1.1: Launch Application
**Steps:**
1. Run: `mvn javafx:run` OR run `Launcher.java`
2. Verify home screen appears

**Expected Result:**
- ✅ Window opens with title "GPA Calculator - Home"
- ✅ Welcome message displays
- ✅ "Start GPA Calculator" button is visible
- ✅ Window size is 800x600

---

## Test Suite 2: Home Screen

### Test 2.1: UI Elements
**Steps:**
1. Check all text elements are readable
2. Check button styling

**Expected Result:**
- ✅ Title: "Welcome to GPA Calculator"
- ✅ Subtitle text present
- ✅ Button has blue background
- ✅ Layout is centered

### Test 2.2: Navigation
**Steps:**
1. Click "Start GPA Calculator" button

**Expected Result:**
- ✅ Course Entry screen loads
- ✅ Window title changes to "GPA Calculator - Course Entry"
- ✅ Window size changes to 900x700

---

## Test Suite 3: Course Entry Screen - Basic Functionality

### Test 3.1: Initial State
**Steps:**
1. Verify initial screen state

**Expected Result:**
- ✅ All input fields are empty
- ✅ Grade dropdown shows "A+" as default
- ✅ Credit info shows "Total Credits: 0.0 / 15.0"
- ✅ "No courses added yet" message visible
- ✅ "Calculate GPA" button is disabled
- ✅ "Back to Home" button is enabled

### Test 3.2: Add Valid Course
**Steps:**
1. Enter Course Name: "Data Structures"
2. Enter Course Code: "CSE 2101"
3. Enter Course Credit: "3.0"
4. Enter Teacher 1: "Dr. John Smith"
5. Leave Teacher 2 empty
6. Select Grade: "A+"
7. Click "Add Course"

**Expected Result:**
- ✅ Success alert appears: "Course added successfully!"
- ✅ Course appears in the list below
- ✅ Credit counter updates: "3.0 / 15.0"
- ✅ All fields clear after adding
- ✅ "No courses added yet" message disappears

---

## Test Suite 4: Input Validation

### Test 4.1: Empty Course Name
**Steps:**
1. Leave Course Name empty
2. Fill other fields
3. Click "Add Course"

**Expected Result:**
- ⚠️ Alert: "Missing Information - Please enter course name."
- ❌ Course NOT added

### Test 4.2: Empty Course Code
**Steps:**
1. Enter Course Name only
2. Leave Course Code empty
3. Click "Add Course"

**Expected Result:**
- ⚠️ Alert: "Missing Information - Please enter course code."
- ❌ Course NOT added

### Test 4.3: Invalid Credit (Non-numeric)
**Steps:**
1. Fill all fields
2. Enter Credit: "abc"
3. Click "Add Course"

**Expected Result:**
- ❌ Alert: "Invalid Input - Please enter a valid number for course credit."
- ❌ Course NOT added

### Test 4.4: Invalid Credit (Zero or Negative)
**Steps:**
1. Fill all fields
2. Enter Credit: "0" or "-1"
3. Click "Add Course"

**Expected Result:**
- ⚠️ Alert: "Invalid Input - Course credit must be greater than 0."
- ❌ Course NOT added

### Test 4.5: Empty Teacher 1
**Steps:**
1. Fill all fields except Teacher 1
2. Click "Add Course"

**Expected Result:**
- ⚠️ Alert: "Missing Information - Please enter at least Teacher 1 name."
- ❌ Course NOT added

### Test 4.6: No Grade Selected
**Steps:**
1. Fill all fields
2. Clear grade selection (if possible)
3. Click "Add Course"

**Expected Result:**
- ⚠️ Alert: "Missing Information - Please select a grade."
- ❌ Course NOT added

### Test 4.7: Exceeding Credit Limit
**Steps:**
1. Add courses totaling 13.0 credits
2. Try to add a course with 3.0 credits

**Expected Result:**
- ⚠️ Alert: "Credit Limit Exceeded - Adding this course would exceed..."
- ❌ Course NOT added
- ✅ Credit remains at 13.0

---

## Test Suite 5: Course Management

### Test 5.1: Add Multiple Courses
**Steps:**
1. Add Course 1: "Data Structures", "CSE 2101", 3.0, "Dr. Smith", "", "A+"
2. Add Course 2: "Database Systems", "CSE 2102", 3.0, "Prof. Wilson", "", "A"
3. Add Course 3: "Software Engineering", "CSE 2103", 3.0, "Dr. Brown", "", "A-"
4. Add Course 4: "Computer Networks", "CSE 2104", 3.0, "Prof. Davis", "", "B+"
5. Add Course 5: "Operating Systems", "CSE 2105", 3.0, "Dr. Johnson", "", "A"

**Expected Result:**
- ✅ All 5 courses appear in list
- ✅ Credit counter shows "15.0 / 15.0"
- ✅ "Calculate GPA" button becomes enabled
- ✅ Each course has a delete button

### Test 5.2: Delete Course
**Steps:**
1. Add a course
2. Click "Delete" button on the course

**Expected Result:**
- ✅ Confirmation dialog appears
- ✅ If "OK": Course removed, credits updated
- ✅ If "Cancel": Course remains

### Test 5.3: Clear Form
**Steps:**
1. Fill all fields
2. Click "Clear" button

**Expected Result:**
- ✅ All fields reset
- ✅ Grade returns to "A+"
- ✅ Focus returns to Course Name field

### Test 5.4: Calculate GPA Button State
**Steps:**
1. Add courses with credits < 15.0

**Expected Result:**
- ✅ Button remains disabled

**Steps:**
2. Add more courses until credits = 15.0

**Expected Result:**
- ✅ Button becomes enabled

**Steps:**
3. Delete a course (credits < 15.0)

**Expected Result:**
- ✅ Button becomes disabled again

---

## Test Suite 6: Navigation & Confirmations

### Test 6.1: Back to Home (With Data)
**Steps:**
1. Add some courses
2. Click "Back to Home"

**Expected Result:**
- ⚠️ Confirmation dialog: "Return to Home - All entered course data will be lost."
- ✅ If "OK": Returns to home screen
- ✅ If "Cancel": Stays on course entry screen

### Test 6.2: Back to Home (No Data)
**Steps:**
1. Don't add any courses
2. Click "Back to Home"

**Expected Result:**
- ⚠️ Confirmation dialog still appears
- ✅ Can navigate back

---

## Test Suite 7: GPA Calculation & Results

### Test 7.1: Calculate GPA
**Steps:**
1. Add exactly 15.0 credits worth of courses
2. Click "Calculate GPA"

**Expected Result:**
- ✅ Result screen loads
- ✅ Window title: "GPA Calculator - Results"
- ✅ GPA displays as a number (e.g., "3.80")
- ✅ Performance description shows
- ✅ Total courses count is correct
- ✅ Total credits shows "15.0"
- ✅ All courses appear in table
- ✅ Date shows current date

### Test 7.2: Verify GPA Calculation
**Sample Data:**
- Course 1: 3.0 credits, A+ (4.0)
- Course 2: 3.0 credits, A (4.0)
- Course 3: 3.0 credits, A- (3.7)
- Course 4: 3.0 credits, B+ (3.3)
- Course 5: 3.0 credits, A (4.0)

**Manual Calculation:**
```
Total = (3.0×4.0 + 3.0×4.0 + 3.0×3.7 + 3.0×3.3 + 3.0×4.0) / 15.0
     = (12.0 + 12.0 + 11.1 + 9.9 + 12.0) / 15.0
     = 57.0 / 15.0
     = 3.80
```

**Expected Result:**
- ✅ Displayed GPA: "3.80"

### Test 7.3: Performance Descriptions
Test with different GPAs:

| GPA Range | Expected Description |
|-----------|---------------------|
| 3.7 - 4.0 | "Excellent Performance!" |
| 3.3 - 3.69 | "Very Good Performance!" |
| 3.0 - 3.29 | "Good Performance!" |
| 2.7 - 2.99 | "Above Average Performance" |
| 2.0 - 2.69 | "Average Performance" |
| 0.0 - 1.99 | "Needs Improvement" |

### Test 7.4: Grade Colors
**Expected Color Coding:**
- A+/A: Green
- A-/B+: Teal
- B/B-: Orange
- C+/C: Dark Orange
- C-/D+/D: Red-Orange
- F: Red

### Test 7.5: Course Table Display
**Expected:**
- ✅ All course codes displayed
- ✅ All course names displayed
- ✅ Teacher 1 names shown
- ✅ Teacher 2 names shown (or "-" if empty)
- ✅ Credits shown with 1 decimal
- ✅ Grades shown in color
- ✅ Alternating row colors

---

## Test Suite 8: Result Screen Navigation

### Test 8.1: Back to Course Entry
**Steps:**
1. From result screen, click "Back to Courses"

**Expected Result:**
- ✅ Returns to course entry screen
- ⚠️ Fresh start (previous courses cleared)

### Test 8.2: Back to Home
**Steps:**
1. From result screen, click "Back to Home"

**Expected Result:**
- ✅ Returns to home screen
- ✅ Window size returns to 800x600

---

## Test Suite 9: Edge Cases

### Test 9.1: Decimal Credits
**Steps:**
1. Add course with credit: "2.5"
2. Add course with credit: "3.5"
3. Continue until reaching 15.0

**Expected Result:**
- ✅ Accepts decimal values
- ✅ Calculates correctly

### Test 9.2: All F Grades
**Steps:**
1. Add 5 courses (3.0 credits each)
2. All grades = "F"
3. Calculate GPA

**Expected Result:**
- ✅ GPA displays "0.00"
- ✅ Description: "Needs Improvement"

### Test 9.3: All A+ Grades
**Steps:**
1. Add 5 courses (3.0 credits each)
2. All grades = "A+"
3. Calculate GPA

**Expected Result:**
- ✅ GPA displays "4.00"
- ✅ Description: "Excellent Performance!"

### Test 9.4: Long Course/Teacher Names
**Steps:**
1. Enter very long names (50+ characters)
2. Add course

**Expected Result:**
- ✅ Text wraps properly
- ✅ No UI breaking
- ✅ Displays correctly in result table

### Test 9.5: Special Characters
**Steps:**
1. Enter names with special characters (é, ñ, etc.)
2. Add course

**Expected Result:**
- ✅ Characters display correctly
- ✅ No encoding issues

---

## Test Suite 10: UI/UX Testing

### Test 10.1: Window Resizing
**Steps:**
1. Resize window to larger size
2. Resize window to smaller size (min 750x550)

**Expected Result:**
- ✅ Layout adjusts properly
- ✅ No overlapping elements
- ✅ Scrollbars appear when needed

### Test 10.2: Scrolling
**Steps:**
1. Add many courses
2. Scroll through course list

**Expected Result:**
- ✅ Smooth scrolling
- ✅ All courses visible

### Test 10.3: Tab Navigation
**Steps:**
1. Use Tab key to navigate through form fields

**Expected Result:**
- ✅ Logical tab order
- ✅ All fields accessible

### Test 10.4: Button Hover
**Steps:**
1. Hover over buttons

**Expected Result:**
- ✅ Visual feedback (if cursor change defined)
- ✅ Buttons remain clickable

---

## Test Suite 11: Scene Builder Compatibility

### Test 11.1: Open FXML Files
**Steps:**
1. Open Scene Builder
2. Open `home-view.fxml`
3. Open `course-entry-view.fxml`
4. Open `gpa-result-view.fxml`

**Expected Result:**
- ✅ All files open without errors
- ✅ Controllers are recognized
- ✅ fx:id elements are bound
- ✅ Layouts display correctly

---

## Performance Testing

### Test 12.1: Application Startup Time
**Expected:** < 3 seconds

### Test 12.2: Scene Transition Time
**Expected:** < 500ms

### Test 12.3: Form Validation Response
**Expected:** Instant (< 100ms)

### Test 12.4: GPA Calculation Time
**Expected:** Instant (< 50ms)

---

## Final Verification Checklist

- [ ] Application starts without errors
- [ ] All three screens accessible
- [ ] All input validations work
- [ ] Courses can be added and deleted
- [ ] Credit tracking is accurate
- [ ] GPA calculation is correct
- [ ] Result screen displays properly
- [ ] All buttons functional
- [ ] All alerts appear
- [ ] Navigation works smoothly
- [ ] No compile errors
- [ ] No runtime errors
- [ ] Scene Builder compatible
- [ ] Professional appearance
- [ ] User-friendly interface

---

## Bug Report Template

If you find any issues, document them:

**Bug ID:** [Number]  
**Severity:** [Critical/Major/Minor]  
**Screen:** [Home/Course Entry/Result]  
**Steps to Reproduce:**
1. 
2. 
3. 

**Expected Result:**  
**Actual Result:**  
**Screenshots:** [If applicable]

---

## Test Summary Report Template

**Date:** [Date]  
**Tester:** [Name]  
**Total Tests:** [Number]  
**Passed:** [Number]  
**Failed:** [Number]  
**Pass Rate:** [Percentage]  

**Issues Found:**
- 

**Recommendations:**
- 

---

**Happy Testing! 🧪✅**
