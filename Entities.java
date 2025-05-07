// ENTITIES

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Entities {
}

class Assignment {
    private final LocalDate dueDate;
    private String id;
    private String title;
    private String description;
    private boolean graded = false;

    public Assignment(String id, String title, String description, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Assignment(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.description = "";
        this.id = "A" + System.currentTimeMillis(); // simple ID generator
    }

    public String getTitle() {
        return title;
    }

    public void markGraded() {
        this.graded = true;
    }

    public boolean isGraded() {
        return graded;
    }

    public void displayStatus() {
        System.out.println("Assignment: " + title + " | Graded: " + (graded ? "Yes" : "No"));
    }
}

//--------------------------------------------------------------------
// Simple Submission class
class Submission {
    private Student student;
    private String assignmentTitle;
    private List<String> uploadedContent = new ArrayList<>();
    private String feedback = "";
    private boolean resubmitted = false;

    public Submission(Student student, String assignmentTitle, List<String> content) {
        this.student = student;
        this.assignmentTitle = assignmentTitle;
        this.uploadedContent.addAll(content);
    }

    public void viewSubmission() {
        System.out.println("Submission by: " + student.getName());
        System.out.println("Assignment: " + assignmentTitle);
        for (String item : uploadedContent) {
            System.out.println(" - " + item);
        }
        System.out.println("Feedback: " + (feedback.isEmpty() ? "No feedback yet." : feedback));
        System.out.println("Resubmitted: " + (resubmitted ? "Yes" : "No"));
    }

    public void addFeedback(String feedback) {
        this.feedback = feedback;
        System.out.println("Feedback added for " + student.getName() + ": " + feedback);
    }

    public void resubmit(List<String> newContent) {
        this.uploadedContent.clear();
        this.uploadedContent.addAll(newContent);
        this.resubmitted = true;
        System.out.println(student.getName() + " resubmitted the assignment.");
    }

    public Student getStudent() {
        return student;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }
}
//--------------------------------------------------------------------
//
class Grade {
    private Student student;
    private String assignmentTitle;
    private double score;
    private String colorCode;

    public Grade(Student student, String assignmentTitle, double score) {
        this.student = student;
        this.assignmentTitle = assignmentTitle;
        this.score = score;
        this.colorCode = assignColor(score);
    }

    private String assignColor(double score) {
        if (score >= 90) return "Green";
        if (score >= 70) return "Yellow";
        return "Red";
    }

    public void display() {
        System.out.println(student.getName() + " - " + assignmentTitle + ": " + score + "% [" + colorCode + "]");
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public Student getStudent() {
        return student;
    }
}

//--------------------------------------------------------------------

class CalendarEvent {
    private String title;
    private LocalDate date;
    private boolean isPublic;
    private User owner; // use interface

    public CalendarEvent(String title, LocalDate date, boolean isPublic, User owner) {
        this.title = title;
        this.date = date;
        this.isPublic = isPublic;
        this.owner = owner;
    }

    public boolean isVisibleTo(User viewer) {
        return isPublic || owner.getId().equals(viewer.getId());
    }

    public String toString() {
        return title + " on " + date + (isPublic ? " [Public]" : " [Private]");
    }

    public LocalDate getDate() {
        return date;
    }

    public User getOwner() {
        return owner;
    }
}

//--------------------------------------------------------------------

class Course {
    private String courseId;
    private String name;
    private User creator;
    private List<Assignment> assignments = new ArrayList<>();

    public Course(String courseId, String name, User creator) {
        this.courseId = courseId;
        this.name = name;
        this.creator = creator;
    }

    public String getTitle() { return name; }
    public String getInstructorName() { return creator.getName(); }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
}

//--------------------------------------------------------------------
class AttendanceRecord {
    private Student student;
    private String type;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public AttendanceRecord(Student student, String type) {
        this.student = student;
        this.type = type;
        this.checkInTime = LocalDateTime.now();
    }

    public void checkOut() {
        this.checkOutTime = LocalDateTime.now();
    }

    public Student getStudent() {
        return student;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    @Override
    public String toString() {
        return student.getName() + " | " + type + " | In: " + checkInTime +
                " | Out: " + (checkOutTime != null ? checkOutTime : "Still Checked In");
    }
}
