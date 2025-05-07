public class AssignmentSubmission {
    private Student student;
    private Assignment assignment;
    private String submissionText;
    private boolean isSubmitted;
    private int grade; // Grade out of the assignment's point value
    private String feedback;

    public AssignmentSubmission(Student student, Assignment assignment, String submissionText) {
        this.student = student;
        this.assignment = assignment;
        this.submissionText = submissionText;
        this.isSubmitted = true; // Indicating that the assignment has been submitted
        this.grade = -1; // Grade will be initially ungraded
        this.feedback = ""; // Feedback will be initially empty
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSubmissionText(String newSubmissionText) {
        this.submissionText = newSubmissionText;
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public String getSubmissionText() {
        return submissionText;
    }

    public int getGrade() {
        return grade;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return "Student: " + student.getName() +
                "\nAssignment: " + assignment.getTitle() +
                "\nSubmission: " + submissionText +
                "\nSubmitted: " + (isSubmitted ? "Yes" : "No") +
                "\nGrade: " + (grade == -1 ? "Not graded" : grade) +
                "\nFeedback: " + (feedback.isEmpty() ? "No feedback" : feedback);
    }
}
