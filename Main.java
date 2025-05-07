import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create users
        Student alice = new Student("S001", "Alice");
        Instructor bob = new Instructor("I001", "Bob");
        SubstituteInstructor carol = new SubstituteInstructor("SI001", "Carol");

        // Dashboards
        alice.viewDashboard();
        bob.viewDashboard();
        carol.viewDashboard();

        // Authorization examples
        Authorization.checkAccess(alice, "submit_assignment");
        Authorization.checkAccess(bob, "create_course");
        Authorization.checkAccess(carol, "submit_assignment");

        // Instructor creates course
        bob.createCourse("C101", "Intro to Java");

        // View courses
        bob.manageCourses();
        carol.viewAllCourses();

        // Assignments
        GradingManager.addAssignment("Project 1", LocalDate.of(2025, 5, 10));
        GradingManager.viewAssignmentStatus();

        // Submission
        List<String> content = List.of("Main.java", "README.md");
        Submission sub = new Submission(alice, "Project 1", content);
        AssignmentManager.submitAssignment(sub);

        // Feedback
        sub.addFeedback("Well done!");
        sub.viewSubmission();

        // Grading
        GradingManager.assignGrade(alice, "Project 1", 92.5);
        GradingManager.viewAllGrades();
        GradingManager.viewToDoList();

        // Calendar
        UserCalendar.addEvent("Lecture 1", LocalDate.of(2025, 5, 6), true, bob);
        UserCalendar.viewEvents(alice);

        // Attendance
        AttendanceRecord record = AttendanceManager.checkIn(alice, "Lecture");
        AttendanceManager.checkOut(record);
        AttendanceManager.viewAttendanceByStudent(alice);
    }
}

