import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// SubstituteInstructor class
public class SubstituteInstructor extends TemplateUser implements
        AttendanceManagerInterface,
        CourseInterface,
        SchoolCalendarInterface {

    private Map<String, Boolean> attendance = new HashMap<>();
    private List<String> events = new ArrayList<>();
    private CourseInterface currentCourse;

    public SubstituteInstructor(String id, String name) {
        super(id, name, Role.SUBSTITUTE_INSTRUCTOR);
    }

    // -------------------- TemplateUser --------------------
    @Override
    public void viewDashboard() {
        System.out.println("Welcome to the Substitute Instructor Dashboard, " + name);
    }

    // -------------------- AttendanceManagerInterface --------------------
    @Override
    public void addStudent(String id, String name) {
        attendance.put(name, false);  // Mark student as absent initially
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
        attendance.replaceAll((k, v) -> false);  // Reset attendance for the day
    }

    @Override
    public void printDailyAttendance() {
        System.out.println("Daily Attendance:");
        attendance.forEach((k, v) -> System.out.println(k + ": " + (v ? "Present" : "Absent")));
    }

    @Override
    public void printAbsenceReport() {
        System.out.println("Total Absences: " + getAbsenceCount());
    }

    @Override
    public int getAbsenceCount() {
        return (int) attendance.values().stream().filter(v -> !v).count();
    }

    @Override
    public void incrementAbsence() {
        // Increment absence count logic (if needed)
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
        return this;
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
            System.out.println("You are assigned to: " + getTitle());
        } else {
            System.out.println("You are not assigned to any course.");
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
        // Substitute instructors cannot add events
        System.out.println("You do not have permission to add events.");
    }

    @Override
    public void editEvent(Instructor teacher, String event, String newName, LocalDateTime newDateTime) {
        // Substitute instructors cannot edit events
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
            return SubstituteInstructor.this;
        }

        public void viewCourseDetailsByTitle(String title) {
            // Not used here but could be implemented
        }

        public void displayCourseDetails() {
            // Not used here but could be implemented
        }

        public void viewEnrolledCourses() {
            // Not used here but could be implemented
        }

        public void enrollInCourse() {
            // Not used here but could be implemented
        }

        public void createCourse(String title, String subject, LocalDateTime time, String location) {
            // Not used here but could be implemented
        }

        public void displayCourses() {
            // Not used here but could be implemented
        }
    }
}
