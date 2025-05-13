import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// Instructor class
public class Instructor extends TemplateUser implements
        AssignmentSubmissionInterface,
        AttendanceManagerInterface,
        CourseInterface,
        SchoolCalendarInterface {

    private List<AssignmentInterface> assignments = new ArrayList<>();
    private List<String> events = new ArrayList<>();
    private Map<String, Boolean> attendance = new HashMap<>();
    private Map<String, Map<String, String>> gradeBook = new HashMap<>();
    private int absenceCount = 0;
    private String submissionText;
    private int grade;
    private String feedback;
    private AssignmentInterface currentAssignment;
    private CourseInterface currentCourse;

    public Instructor(String id, String name) {
        super(id, name, Role.INSTRUCTOR);
    }

    // -------------------- TemplateUser --------------------
    @Override
    public void viewDashboard() {
        System.out.println("Welcome to the Instructor Dashboard, " + name);
    }

    // -------------------- AssignmentSubmissionInterface --------------------
    @Override
    public Student getStudent() {
        // Instructors don't submit assignments as students, so return null or throw exception
        return null;
    }

    @Override
    public AssignmentInterface getAssignment() {
        return currentAssignment;
    }

    @Override
    public String getSubmissionText() {
        return submissionText;
    }

    @Override
    public int getGrade() {
        return grade;
    }

    @Override
    public String getFeedback() {
        return feedback;
    }

    @Override
    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public void setSubmissionText(String newSubmissionText) {
        this.submissionText = newSubmissionText;
    }

    @Override
    public void createAssignment(String course, String title, String subject, String deadline, int pointValue) {
        currentAssignment = new Assignment(title, subject, LocalDate.parse(deadline), pointValue, this);
        assignments.add(currentAssignment);
        System.out.println("Assignment '" + title + "' created for course: " + course);
    }

    @Override
    public void listCourseAssignments(String course) {
        System.out.println("Assignments for course: " + course);
        for (AssignmentInterface a : assignments) {
            System.out.println(" - " + a.getTitle());
        }
    }

    @Override
    public void viewGrades(String courseForGrade) {

    }

    @Override
    public void submitAssignment(String assignmentTitle, String assignmentDesc) {

    }


    public void assignGrade(String studentName, String courseName, String grade) {
        gradeBook.putIfAbsent(studentName, new HashMap<>());
        gradeBook.get(studentName).put(courseName, grade);
        System.out.println("Grade assigned to " + studentName + " for " + courseName + ": " + grade);
    }


    public void printGradeBook() {
        System.out.println("Grade Book:");
        for (Map.Entry<String, Map<String, String>> studentEntry : gradeBook.entrySet()) {
            String studentName = studentEntry.getKey();
            Map<String, String> courses = studentEntry.getValue();
            for (Map.Entry<String, String> courseEntry : courses.entrySet()) {
                System.out.println(studentName + " --- " + courseEntry.getKey() + ": " + courseEntry.getValue());
            }
        }
    }

    // -------------------- AttendanceManagerInterface --------------------
    @Override
    public void addStudent(String id, String name) {
        attendance.put(name, false);
    }

    @Override
    public void checkInStudent(String name) {
        attendance.put(name, true);
        System.out.println(name + " checked in.");
    }

    @Override
    public void checkOutStudent(String name, boolean parentApproved) {
        if (parentApproved && attendance.containsKey(name)) {
            attendance.put(name, false);
            System.out.println(name + " checked out.");
        }
    }

    @Override
    public void markAbsencesForUnmarkedStudents() {
        for (Map.Entry<String, Boolean> entry : attendance.entrySet()) {
            if (!entry.getValue()) {
                incrementAbsence();
            }
        }
    }

    @Override
    public void resetDailyRecords() {
        attendance.replaceAll((k, v) -> false);
    }

    @Override
    public void printDailyAttendance() {
        System.out.println("Daily Attendance:");
        attendance.forEach((k, v) -> System.out.println(k + ": " + (v ? "Checked-in" : "Checked-out")));
    }

    @Override
    public void printAbsenceReport() {
    }

    @Override
    public int getAbsenceCount() {
        return absenceCount;
    }

    @Override
    public void incrementAbsence() {
        absenceCount++;
    }

    // -------------------- CourseInterface --------------------
    @Override
    public String getTitle() {
        return currentCourse != null ? currentCourse.getTitle() : "No Course";
    }

    @Override
    public String getSubject() {
        return currentCourse != null ? currentCourse.getSubject() : "No Subject";
    }

    @Override
    public LocalDateTime getTime() {
        return currentCourse != null ? currentCourse.getTime() : LocalDateTime.now();
    }

    @Override
    public String getLocation() {
        return currentCourse != null ? currentCourse.getLocation() : "Unknown";
    }

    @Override
    public Instructor getInstructor() {
        return this;
    }

    @Override
    public AttendanceManagerInterface getAttendanceManager() {
        return this;
    }

    @Override
    public void viewCourseDetailsByTitle(String title) {
        System.out.println("Viewing course details for: " + title);
    }

    @Override
    public void displayCourseDetails() {
        System.out.println("Course: " + getTitle() + " | Subject: " + getSubject());
    }

    @Override
    public void viewEnrolledCourses() {
        System.out.println("Instructor's active course: " + getTitle());
    }

    @Override
    public void enrollInCourse() {
        System.out.println("Instructors typically do not enroll in courses.");
    }

    @Override
    public void createCourse(String title, String subject, LocalDateTime time, String location) {
        currentCourse = new Course(title, subject, time, location, this);
        System.out.println("Course '" + title + "' created.");
    }

    @Override
    public void displayCourses() {
        System.out.println("Displaying courses for instructor: " + name);
    }

    // -------------------- SchoolCalendarInterface --------------------
    @Override
    public void addEvent(Instructor teacher, String name, LocalDateTime dateTime) {
        events.add(name + " at " + dateTime.toString());
        System.out.println("Event added: " + name);
    }

    @Override
    public void editEvent(Instructor teacher, String event, String newName, LocalDateTime newDateTime) {
        events.removeIf(e -> e.startsWith(event));
        events.add(newName + " at " + newDateTime.toString());
        System.out.println("Event updated: " + newName);
    }

    @Override
    public void viewEvents() {
        System.out.println("Events:");
        for (String event : events) {
            System.out.println(event);
        }
    }

    public void saveCourseToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Course Title: " + getTitle() + "\n");
            writer.write("Subject: " + getSubject() + "\n");
            writer.write("Location: " + getLocation() + "\n");
            writer.write("Time: " + getTime() + "\n");
            writer.write("Instructor: " + name + "\n");
            writer.write("-----------------------------\n");
            System.out.println("Course saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving course: " + e.getMessage());
        }
    }

    public void saveAttendanceToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Attendance Record:\n");
            for (Map.Entry<String, Boolean> entry : attendance.entrySet()) {
                writer.write(entry.getKey() + ": " + (entry.getValue() ? "Present" : "Absent") + "\n");
            }
            writer.write("Total Absences: " + absenceCount + "\n");
            writer.write("-----------------------------\n");
            System.out.println("Attendance saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving attendance: " + e.getMessage());
        }
    }

    public void saveGradeToFile(String fileName, String studentName, String courseName, String grade) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Grade Record:\n");
            writer.write("Student: " + studentName + "\n");
            writer.write("Course: " + courseName + "\n");
            writer.write("Grade: " + grade + "\n");
            writer.write("-----------------------------\n");
            System.out.println("Grade saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving grade: " + e.getMessage());
        }
    }

    public void createCourse(String courseNameForInstructor, String subjectForInstructor, String meetingDays, String meetingTime, String roomForInstructor) {

    }


    // -------------------- Supporting Inner Classes --------------------
    private class Assignment implements AssignmentInterface {
        private String title;
        private String subject;
        private LocalDate deadline;
        private int pointValue;
        private CourseInterface course;

        public Assignment(String title, String subject, LocalDate deadline, int pointValue, CourseInterface course) {
            this.title = title;
            this.subject = subject;
            this.deadline = deadline;
            this.pointValue = pointValue;
            this.course = course;
        }

        public String getTitle() { return title; }
        public String getSubject() { return subject; }
        public LocalDate getDeadline() { return deadline; }
        public int getPointValue() { return pointValue; }
        public CourseInterface getCourse() { return course; }

        @Override
        public void submit(Student student) {

        }
    }

    private class Course implements CourseInterface {
        private String title;
        private String subject;
        private LocalDateTime time;
        private String location;
        private Instructor instructor;

        public Course(String title, String subject, LocalDateTime time, String location, Instructor instructor) {
            this.title = title;
            this.subject = subject;
            this.time = time;
            this.location = location;
            this.instructor = instructor;
        }

        public String getTitle() { return title; }
        public String getSubject() { return subject; }
        public LocalDateTime getTime() { return time; }
        public String getLocation() { return location; }
        public Instructor getInstructor() { return instructor; }
        public AttendanceManagerInterface getAttendanceManager() { return Instructor.this; }
        public void viewCourseDetailsByTitle(String title) {}
        public void displayCourseDetails() {}
        public void viewEnrolledCourses() {}
        public void enrollInCourse() {}
        public void createCourse(String title, String subject, LocalDateTime time, String location) {}
        public void displayCourses() {}
    }
}
