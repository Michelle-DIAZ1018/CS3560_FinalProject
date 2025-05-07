import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Create instructor and calendar
        Instructor instructor = new Instructor("I123", "Ms. Johnson");
        SchoolCalendar calendar = new SchoolCalendar();

        // Instructor creates a course
        instructor.createCourse("Math 101", "Mathematics", LocalDateTime.of(2025, 5, 15, 10, 0), "Room 12A");
        Course mathCourse = instructor.getCourses().get(0);

        // Create student and enroll
        Student student = new Student("S456", "Alex Carter");
        student.enrollInCourse(mathCourse);

        // Instructor creates an assignment
        instructor.createAssignment(mathCourse, "Algebra Homework", "Math", "2025-05-20", 100);

        // Student views and submits assignment
        student.viewAssignments(mathCourse);
        student.submitAssignment(mathCourse, "Algebra Homework", "Solved all equations.");

        // Instructor grades the submission
        instructor.viewSubmissions(mathCourse, "Algebra Homework");
        instructor.gradeAssignmentSubmission(mathCourse, "Alex Carter", "Algebra Homework", 95, "Excellent work!");

        // Student views feedback
        student.viewFeedback(mathCourse, "Algebra Homework");

        // Attendance actions
        mathCourse.getAttendanceManager().addStudent(student.getId(), student.getName());
        instructor.takeAttendance(mathCourse, "Alex Carter");
        instructor.endAttendance(mathCourse, "Alex Carter", true);
        instructor.finalizeAttendance(mathCourse);
        mathCourse.getAttendanceManager().printDailyAttendance();
        mathCourse.getAttendanceManager().printAbsenceReport();

        // Add and view calendar events
        instructor.addEventToCalendar(calendar, "Midterm Exam", LocalDateTime.of(2025, 5, 25, 9, 0));
        student.viewCalendarEvents(calendar);

        // View dashboards
        instructor.viewDashboard();
        student.viewDashboard();
    }
}
