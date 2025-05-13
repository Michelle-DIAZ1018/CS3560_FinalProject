import java.time.LocalDate;
import java.time.LocalDateTime;


// The list of all the fixed constants of the program, as our focus is on three main roles: student, teacher, and sub
// These variables will NOT change, as it will help with consistency in services and entities
enum Role {
    STUDENT,
    INSTRUCTOR,
    SUBSTITUTE_INSTRUCTOR
}
//--------------------------------------------------------------------//

// This is a simple interface that we used to get the basic information of each person in the system.
interface User {
    String getId(); // returns the ID of each person (EX: "12345")
    String getName(); // returns the person's name (EX: "Sally")
    Role getRole(); // returns the person's role (EX: "Student")
    void viewDashboard(); // Will display the dashboard according to each person's role
}

//--------------------------------------------------------------------//

interface AssignmentInterface {
    String getTitle();
    String getSubject();
    LocalDate getDeadline();
    int getPointValue();
    CourseInterface getCourse();

    void submit(Student student);
}

//-----------------------------------------------------

interface GradingManager {
    void assignGrade(String studentName, String courseName, String grade);
    void printGradeBook();
}

//-----------------------------------------------------

interface AssignmentSubmissionInterface {
    Student getStudent();
    AssignmentInterface getAssignment();
    String getSubmissionText();
    int getGrade();
    String getFeedback();
    void setGrade(int grade);
    void setFeedback(String feedback);
    void setSubmissionText(String newSubmissionText);
    void createAssignment(String course, String title, String subject, String deadline, int pointValue);
    void listCourseAssignments(String course);
    void viewGrades(String courseForGrade);
    void submitAssignment(String assignmentTitle, String assignmentDesc);
}

//-----------------------------------------------------

interface AttendanceManagerInterface {
    void addStudent(String id, String name);
    void checkInStudent(String name);
    void checkOutStudent(String name, boolean parentApproved);
    void markAbsencesForUnmarkedStudents();
    void resetDailyRecords();
    void printDailyAttendance();
    void printAbsenceReport();
    int getAbsenceCount();
    void incrementAbsence();
}

//----------------------------------------------------

interface CourseInterface {
    String getTitle();
    String getSubject();
    LocalDateTime getTime();
    String getLocation();
    Instructor getInstructor();
    AttendanceManagerInterface getAttendanceManager();
    void viewCourseDetailsByTitle(String title);
    void displayCourseDetails();
    void viewEnrolledCourses();
    void enrollInCourse();
    void createCourse(String title, String subject, LocalDateTime time, String location);
    void displayCourses();
}

//--------------------------------------------------

interface SchoolCalendarInterface {
    void addEvent(Instructor teacher, String name, LocalDateTime dateTime);
    void editEvent(Instructor teacher, String event, String newName, LocalDateTime newDateTime);
    void viewEvents();
}

//-------------------------------------------------

// the implementation of the interface of USER with constructors and methods
abstract class TemplateUser implements User {
    // instead of being private, they are protected so subclasses can access them
    protected String id;
    protected String name;
    protected Role role;

    // initialization of the variables
    public TemplateUser(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public TemplateUser() {

    }

    // the implementations of each of the methods
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Role getRole() {
        return role;
    }
    public abstract void viewDashboard();

}


