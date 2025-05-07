import java.time.LocalDateTime;

class AttendanceRecord {
    private Student student;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean parentApprovedCheckout;
    private boolean isPresent;

    public AttendanceRecord(Student student) {
        this.student = student;
        this.isPresent = false;
    }

    public void checkIn(LocalDateTime time) {
        this.checkInTime = time;
        this.isPresent = true;
    }

    public void checkOut(LocalDateTime time, boolean parentApproved) {
        this.checkOutTime = time;
        this.parentApprovedCheckout = parentApproved;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    @Override
    public String toString() {
        return "Student: " + student.getName() +
                "\nChecked In: " + (checkInTime != null ? checkInTime : "No") +
                "\nChecked Out: " + (checkOutTime != null ? checkOutTime : "No") +
                "\nParent Approved Checkout: " + parentApprovedCheckout +
                "\nStatus: " + (isPresent ? "Present" : "Absent");
    }
}
