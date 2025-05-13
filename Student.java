import java.util.ArrayList;
import java.util.List;

public class Student extends TemplateUser {
    private int absenceCount;
    private List<AssignmentSubmission> submissions;
    private List<Course> enrolledCourses;

    public Student(String id, String name) {
        super(id, name, Role.STUDENT);
        this.absenceCount = 0;
        this.submissions = new ArrayList<>();
        this.enrolledCourses = new ArrayList<>();
    }

    // Add a course to student's enrolled list
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    // View all enrolled courses
    public void viewEnrolledCourses() {
        System.out.println("Courses enrolled by " + this.getName() + ":");
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (Course course : enrolledCourses) {
                System.out.println("- " + course.getTitle());
            }
        }
    }

    // View details for a specific enrolled course
    public void viewCourseDetailsByTitle(String title) {
        for (Course course : enrolledCourses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                course.displayCourseDetails();  // assuming this method is safe for students
                return;
            }
        }
        System.out.println("Course not found.");
    }

    // Dashboard view for students
    @Override
    public void viewDashboard() {
        System.out.println("Student Dashboard:");
        System.out.println("(1) View assignments and submissions");
        System.out.println("(2) View grades");
        System.out.println("(3) View calendar");
        System.out.println("(4) View enrolled courses");
    }

    // Absence tracking
    public int getAbsenceCount() {
        return absenceCount;
    }

    public void incrementAbsence() {
        absenceCount++;
    }

    // Assignment management
    public void viewAssignments(Course course) {
        System.out.println("Assignments for " + course.getTitle() + ":");
        for (Assignment a : course.getAssignmentManager().getAssignments()) {
            System.out.println(a);
        }
    }

    public void submitAssignment(Course course, String assignmentTitle, String submissionText) {
        Assignment assignment = findAssignmentByTitle(course, assignmentTitle);
        if (assignment != null) {
            AssignmentSubmission submission = new AssignmentSubmission(this, assignment, submissionText);
            submissions.add(submission);
            System.out.println("Submission for \"" + assignmentTitle + "\" has been submitted.");
        } else {
            System.out.println("Assignment not found.");
        }
    }

    public void resubmitAssignment(Course course, String assignmentTitle, String newSubmissionText) {
        AssignmentSubmission submission = findSubmissionByAssignment(course, assignmentTitle);
        if (submission != null) {
            submission.setSubmissionText(newSubmissionText);
            System.out.println("Resubmission for \"" + assignmentTitle + "\" has been made.");
        } else {
            System.out.println("No submission found for assignment: " + assignmentTitle);
        }
    }

    public void viewFeedback(Course course, String assignmentTitle) {
        AssignmentSubmission submission = findSubmissionByAssignment(course, assignmentTitle);
        if (submission != null) {
            System.out.println("Feedback for \"" + assignmentTitle + "\":");
            System.out.println("Grade: " + submission.getGrade());
            System.out.println("Feedback: " + submission.getFeedback());
        } else {
            System.out.println("No submission found for assignment: " + assignmentTitle);
        }
    }

    public void viewCalendarEvents(SchoolCalendar calendar) {
        calendar.viewEvents();
    }

    public List<AssignmentSubmission> getSubmissions() {
        return submissions;
    }
}

