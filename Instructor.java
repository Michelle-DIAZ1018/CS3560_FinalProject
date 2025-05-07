import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends TemplateUser {
    private List<Course> courses;

    public Instructor(String id, String name) {
        super(id, name, Role.INSTRUCTOR);
        this.courses = new ArrayList<>();
    }

    // === DASHBOARD ACCESS ===
    @Override
    public void viewDashboard() {
        System.out.println("Instructor Dashboard:");
        System.out.println("(1) Manage assignments");
        System.out.println("(2) Grade and provide feedback");
        System.out.println("(3) Manage calendar events");
        System.out.println("(4) Take attendance");
        System.out.println("(5) View taught courses");
    }

    // === COURSE MANAGEMENT ===

    // Create and add a new course
    public void createCourse(String title, String subject, LocalDateTime time, String location) {
        Course newCourse = new Course(title, subject, time, location, this);
        courses.add(newCourse);
        System.out.println("Course created: " + newCourse.getTitle() + " by " + this.getName());
    }

    // Display all courses taught by this instructor
    public void displayCourses() {
        System.out.println("Courses taught by " + this.getName() + ":");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : courses) {
                course.displayCourseDetails();
                System.out.println("-----------------------------");
            }
        }
    }

    // Display course details by title
    public void displayCourseDetailsByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                course.displayCourseDetails();
                return;
            }
        }
        System.out.println("Course not found.");
    }

    // === ASSIGNMENT MANAGEMENT ===

    public void createAssignment(Course course, String title, String subject, String deadline, int pointValue) {
        course.getAssignmentManager().createAssignment(title, subject, deadline, pointValue);
    }

    public void listCourseAssignments(Course course) {
        System.out.println("Assignments for course: " + course.getTitle());
        course.getAssignmentManager().listAllAssignments();
    }

    public void viewSubmissions(Course course, String assignmentTitle) {
        System.out.println("Submissions for assignment: " + assignmentTitle);
        for (AssignmentSubmission submission : course.getAssignmentManager().getSubmissionsForAssignment(assignmentTitle)) {
            System.out.println(submission);
        }
    }

    public void gradeAssignmentSubmission(Course course, String studentName, String assignmentTitle, int grade, String feedback) {
        AssignmentSubmission submission = findSubmissionByStudentAndAssignment(course, studentName, assignmentTitle);
        if (submission != null) {
            submission.setGrade(grade);
            submission.setFeedback(feedback);
            System.out.println("Graded \"" + assignmentTitle + "\" for " + studentName + ". Grade: " + grade);
        } else {
            System.out.println("No submission found for " + studentName + " on assignment: " + assignmentTitle);
        }
    }

    private AssignmentSubmission findSubmissionByStudentAndAssignment(Course course, String studentName, String assignmentTitle) {
        for (AssignmentSubmission submission : course.getAssignmentManager().getSubmissionsForAssignment(assignmentTitle)) {
            if (submission.getStudent().getName().equalsIgnoreCase(studentName)) {
                return submission;
            }
        }
        return null;
    }

    // === ATTENDANCE MANAGEMENT ===

    public void takeAttendance(Course course, String studentName) {
        course.getAttendanceManager().checkInStudent(studentName);
    }

    public void endAttendance(Course course, String studentName, boolean parentApproved) {
        course.getAttendanceManager().checkOutStudent(studentName, parentApproved);
    }

    public void finalizeAttendance(Course course) {
        course.getAttendanceManager().markAbsencesForUnmarkedStudents();
    }

    // === CALENDAR MANAGEMENT ===

    public void addEventToCalendar(SchoolCalendar calendar, String name, LocalDateTime dateTime) {
        calendar.addEvent(this, name, dateTime);
    }

    public void editEventOnCalendar(SchoolCalendar calendar, Event event, String newName, LocalDateTime newDateTime) {
        calendar.editEvent(this, event, newName, newDateTime);
    }

    // === ACCESSORS ===

    public List<Course> getCourses() {
        return courses;
    }
}
