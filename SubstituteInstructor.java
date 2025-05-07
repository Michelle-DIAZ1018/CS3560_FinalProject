import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubstituteInstructor extends TemplateUser {

    public SubstituteInstructor(String id, String name) {
        super(id, name, Role.SUBSTITUTE_INSTRUCTOR);
    }
    private List<Course> courses = new ArrayList<>();


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

    public void viewCalendarEvents(SchoolCalendar calendar) {
        calendar.viewEvents();
    }

    public List<Course> getCourses() {
        return courses;
    }
}
