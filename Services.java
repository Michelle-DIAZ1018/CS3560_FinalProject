import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Services {
}

class Authorization {
    public static void checkAccess(User user, String action) {
        switch (user.getRole()) {
            case STUDENT:
                if (action.equals("submit_assignment") || action.equals("view_course")) {
                    System.out.println(user.getName() + " is authorized to " + action);
                } else {
                    System.out.println(user.getName() + " is NOT authorized to " + action);
                }
                break;
            case INSTRUCTOR:
                System.out.println(user.getName() + " is authorized to " + action);
                break;
            case SUBSTITUTE_INSTRUCTOR:
                if (action.equals("view_course") || action.equals("teach_course")) {
                    System.out.println(user.getName() + " is authorized to " + action);
                } else {
                    System.out.println(user.getName() + " is NOT authorized to " + action);
                }
                break;
        }
    }
}

//--------------------------------------------------------------------

// Course Manager
class CourseManager {
    private static List<Course> courses = new ArrayList<>();

    public static void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course \"" + course.getTitle() + "\" created by " + course.getInstructorName());
    }

    public static List<Course> getAllCourses() {
        return courses;
    }

    public static List<Course> getCoursesByInstructor(String instructorName) {
        List<Course> filtered = new ArrayList<>();
        for (Course c : courses) {
            if (c.getInstructorName().equals(instructorName)) {
                filtered.add(c);
            }
        }
        return filtered;
    }
}

//--------------------------------------------------------------------

class AssignmentManager {
    private static List<Submission> submissions = new ArrayList<>();

    public static void submitAssignment(Submission submission) {
        submissions.add(submission);
        System.out.println("Submission received from " + submission.getStudent().getName() +
                " for " + submission.getAssignmentTitle());
    }

    public static Submission getSubmission(Student student, String assignmentTitle) {
        for (Submission s : submissions) {
            if (s.getStudent().equals(student) && s.getAssignmentTitle().equals(assignmentTitle)) {
                return s;
            }
        }
        return null;
    }

    public static void viewAllSubmissions() {
        for (Submission s : submissions) {
            s.viewSubmission();
            System.out.println();
        }
    }
}
//--------------------------------------------------------------------

class GradingManager {
    private static List<Grade> grades = new ArrayList<>();
    private static List<Assignment> assignments = new ArrayList<>();

    public static void addAssignment(String title, LocalDate dueDate) {
        assignments.add(new Assignment(title, dueDate));
        System.out.println("Added assignment: " + title + " (Due: " + dueDate + ")");
    }

    public static void assignGrade(Student student, String assignmentTitle, double score) {
        grades.add(new Grade(student, assignmentTitle, score));
        for (Assignment a : assignments) {
            if (a.getTitle().equals(assignmentTitle)) {
                a.markGraded();
            }
        }
        System.out.println("Grade assigned to " + student.getName() + " for " + assignmentTitle);
    }

    public static void viewAllGrades() {
        System.out.println("\n📊 Grade Report:");
        for (Grade g : grades) {
            g.display();
        }
    }

    public static void viewToDoList() {
        System.out.println("\n📝 Grading To-Do List:");
        for (Assignment a : assignments) {
            if (!a.isGraded()) {
                a.displayStatus();
            }
        }
    }

    public static void viewAssignmentStatus() {
        System.out.println("\n📅 All Assignments:");
        for (Assignment a : assignments) {
            a.displayStatus();
        }
    }
}

//--------------------------------------------------------------------

class UserCalendar {
    private static List<CalendarEvent> events = new ArrayList<>();

    public static void addEvent(String title, LocalDate date, boolean isPublic, User owner) {
        events.add(new CalendarEvent(title, date, isPublic, owner));
        System.out.println("Event added: " + title + " | Owner: " + owner.getName());
    }

    public static void viewEvents(User viewer) {
        System.out.println("\n📆 Events for " + viewer.getName() + " (" + viewer.getRole() + "):");
        for (CalendarEvent e : events) {
            if (e.isVisibleTo(viewer)) {
                System.out.println(" - " + e);
            }
        }
    }
}

//--------------------------------------------------------------------
class AttendanceManager {
    private static List<AttendanceRecord> records = new ArrayList<>();

    public static AttendanceRecord checkIn(Student student, String type) {
        AttendanceRecord record = new AttendanceRecord(student, type);
        records.add(record);
        System.out.println(student.getName() + " checked IN for " + type);
        return record;
    }

    public static void checkOut(AttendanceRecord record) {
        record.checkOut();
        System.out.println(record.getStudent().getName() + " checked OUT for " + record.getType());
    }

    public static void viewAllAttendance() {
        System.out.println("\n📋 All Attendance Records:");
        for (AttendanceRecord record : records) {
            System.out.println(" - " + record);
        }
    }

    public static void viewAttendanceByStudent(Student student) {
        System.out.println("\n📅 Attendance for " + student.getName() + ":");
        for (AttendanceRecord record : records) {
            if (record.getStudent().equals(student)) {
                System.out.println(" - " + record);
            }
        }
    }
}

//--------------------------------------------------------------------

class ScheduleManager {
    private static List<Course> rotationSchedule = new ArrayList<>();

    public static void setRotation(List<Course> courses) {
        rotationSchedule = courses;
    }

    public static void showTodaySchedule() {
        int index = LocalDate.now().getDayOfYear() % rotationSchedule.size();
        Course today = rotationSchedule.get(index);
        System.out.println("📅 Today's course: " + today.getTitle());
    }
}





