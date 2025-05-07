//COMMIT
public class Assignment {
    private String title;
    private String subject;
    private String deadline; // could be changed to LocalDate for better type safety
    private int pointValue;

    public Assignment(String title, String subject, String deadline, int pointValue) {
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.pointValue = pointValue;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getPointValue() {
        return pointValue;
    }

    @Override
    public String toString() {
        return "Assignment Title: " + title +
                "\nSubject: " + subject +
                "\nDeadline: " + deadline +
                "\nPoint Value: " + pointValue;
    }
}
