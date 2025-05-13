import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Student class
public class Student extends TemplateUser implements
        AssignmentSubmissionInterface,
        CourseInterface,
        SchoolCalendarInterface {

    private List<AssignmentInterface> assignments = new ArrayList<>();
    private List<String> events = new ArrayList<>();
    private String submissionText;
    private int grade;
    private String feedback;
    private AssignmentInterface currentAssignment;
    private CourseInterface currentCourse;

    public Student(String id, String name) {
        super(id, name, Role.STUDENT);
    }

    // -------------------- TemplateUser --------------------
    @Override
    public void viewDashboard() {
        System.out.println("Welcome to the Student Dashboard, " + name);
    }

    // -------------------- AssignmentSubmissionInterface --------------------
    @Override
    public Student getStudent() {
        return this;
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
        if (currentAssignment != null) {
            System.out.println("Submitting assignment: " + assignmentTitle);
            setSubmissionText(assignmentDesc);
            currentAssignment.submit(this);
            System.out.println("Assignment submitted.");
        } else {
            System.out.println("No assignment found to submit.");
        }
    }


    public void resubmitAssignment(String assignmentTitle, String newAssignmentDesc) {
        if (currentAssignment != null) {
            setSubmissionText(newAssignmentDesc);
            currentAssignment.submit(this);
            System.out.println("Assignment resubmitted.");
        } else {
            System.out.println("No assignment found to resubmit.");
        }
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
        return currentCourse != null ? currentCourse.getInstructor() : null;
    }

    @Override
    public AttendanceManagerInterface getAttendanceManager() {
        return null;  // Not needed for Student
    }

    @Override
    public void viewCourseDetailsByTitle(String title) {
        if (currentCourse != null && currentCourse.getTitle().equals(title)) {
            displayCourseDetails();
        } else {
            System.out.println("Course not found.");
        }
    }

    @Override
    public void displayCourseDetails() {
        if (currentCourse != null) {
            System.out.println("Course Title: " + getTitle());
            System.out.println("Course Subject: " + getSubject());
            System.out.println("Course Time: " + getTime());
            System.out.println("Course Location: " + getLocation());
            System.out.println("Instructor: " + getInstructor().getName());
        }
    }

    @Override
    public void viewEnrolledCourses() {
        if (currentCourse != null) {
            System.out.println("You are enrolled in: " + getTitle());
        } else {
            System.out.println("You are not enrolled in any course.");
        }
    }

    @Override
    public void enrollInCourse() {

    }

    @Override
    public void createCourse(String title, String subject, LocalDateTime time, String location) {

    }

    @Override
    public void displayCourses() {

    }

    // -------------------- SchoolCalendarInterface --------------------
    @Override
    public void addEvent(Instructor teacher, String name, LocalDateTime dateTime) {
        // Students don't add events
        System.out.println("You do not have permission to add events.");
    }

    @Override
    public void editEvent(Instructor teacher, String event, String newName, LocalDateTime newDateTime) {
        // Students don't edit events
        System.out.println("You do not have permission to edit events.");
    }

    @Override
    public void viewEvents() {
        System.out.println("School Events:");
        for (String event : events) {
            System.out.println(event);
        }
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
            // Submission logic for student
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

        public String getTitle() {
            return title;
        }
        public String getSubject() {
            return subject;
        }
        public LocalDateTime getTime() {
            return time;
        }
        public String getLocation() {
            return location;
        }
        public Instructor getInstructor() {
            return instructor;
        }
        public AttendanceManagerInterface getAttendanceManager() {
            return null; // Not needed for Student
        }

        @Override
        public void viewCourseDetailsByTitle(String title) {

        }

        @Override
        public void displayCourseDetails() {

        }

        @Override
        public void viewEnrolledCourses() {

        }

        @Override
        public void enrollInCourse() {

        }

        @Override
        public void createCourse(String title, String subject, LocalDateTime time, String location) {

        }

        @Override
        public void displayCourses() {

        }
    }
}
