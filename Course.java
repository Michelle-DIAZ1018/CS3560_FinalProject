import java.time.LocalDateTime;

public class Course {
    private String title;
    private String subject;
    private LocalDateTime time;
    private String location;
    private Instructor instructor;

    // Managers
    private AssignmentManager assignmentManager;
    private AttendanceManager attendanceManager;

    public Course(String title, String subject, LocalDateTime time, String location, Instructor instructor) {
        this.title = title;
        this.subject = subject;
        this.time = time;
        this.location = location;
        this.instructor = instructor;

        // Initialize managers
        this.assignmentManager = new AssignmentManager();
        this.attendanceManager = new AttendanceManager();
    }

    // Getter methods
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

    public AssignmentManager getAssignmentManager() {
        return assignmentManager;
    }

    public AttendanceManager getAttendanceManager() {
        return attendanceManager;
    }


    // Display course details
    public void displayCourseDetails() {
        System.out.println("Course Title: " + title);
        System.out.println("Subject: " + subject);
        System.out.println("Instructor: " + instructor.getName());
        System.out.println("Date and Time: " + time);
        System.out.println("Location: " + location);
    }
}
