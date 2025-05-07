import java.util.ArrayList;
import java.util.List;

public class AssignmentManager {
    private List<Assignment> assignments = new ArrayList<>();
    private List<AssignmentSubmission> submissions = new ArrayList<>();

    // Create a new assignment
    public void createAssignment(String title, String subject, String deadline, int pointValue) {
        assignments.add(new Assignment(title, subject, deadline, pointValue));
        System.out.println("Assignment \"" + title + "\" created.");
    }

    // Get all assignments for a course
    public List<Assignment> getAssignments() {
        return assignments;
    }

    // Get all submissions for a particular assignment
    public List<AssignmentSubmission> getSubmissionsForAssignment(String assignmentTitle) {
        List<AssignmentSubmission> result = new ArrayList<>();
        for (AssignmentSubmission submission : submissions) {
            if (submission.getAssignment().getTitle().equals(assignmentTitle)) {
                result.add(submission);
            }
        }
        return result;
    }

    // Add a new submission
    public void addSubmission(AssignmentSubmission submission) {
        submissions.add(submission);
    }

    // List all assignments
    public void listAllAssignments() {
        for (Assignment assignment : assignments) {
            System.out.println(assignment);
        }
    }
}
