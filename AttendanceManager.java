import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManager {

    private final List<Student> students = new ArrayList<>();
    private final List<AttendanceRecord> attendanceToday = new ArrayList<>();

    // === Student Management ===
    public void addStudent(String id, String name) {
        if (getStudentByName(name) == null) {
            students.add(new Student(id, name));
        } else {
            System.out.println("Student already exists: " + name);
        }
    }

    // === Attendance Management ===
    public void checkInStudent(String name) {
        Student student = getStudentByName(name);
        if (student == null) {
            System.out.println("Student not found: " + name);
            return;
        }

        if (getAttendanceRecordByName(name) != null) {
            System.out.println("Student already checked in: " + name);
            return;
        }

        AttendanceRecord record = new AttendanceRecord(student);
        record.checkIn(LocalDateTime.now());
        attendanceToday.add(record);
        System.out.println(name + " checked in at " + record.getCheckInTime());
    }

    public void checkOutStudent(String name, boolean parentApproved) {
        AttendanceRecord record = getAttendanceRecordByName(name);
        if (record != null) {
            record.checkOut(LocalDateTime.now(), parentApproved);
            System.out.println(name + " checked out at " + record.getCheckOutTime() +
                    ". Parent approved: " + parentApproved);
        } else {
            System.out.println("No check-in record for " + name);
        }
    }

    public void markAbsencesForUnmarkedStudents() {
        for (Student student : students) {
            if (getAttendanceRecordByName(student.getName()) == null) {
                student.incrementAbsence();
                AttendanceRecord absentRecord = new AttendanceRecord(student);
                attendanceToday.add(absentRecord);
                System.out.println(student.getName() + " marked absent.");
            }
        }
    }

    public void resetDailyRecords() {
        attendanceToday.clear();
    }

    // === Reporting ===
    public void printDailyAttendance() {
        System.out.println("\n--- Daily Attendance ---");
        for (AttendanceRecord record : attendanceToday) {
            System.out.println("---------------");
            System.out.println(record);
        }
    }

    public void printAbsenceReport() {
        System.out.println("\n=== Absence Report ===");
        for (Student student : students) {
            System.out.println(student.getName() + ": " + student.getAbsenceCount() + " absence(s)");
        }
    }

    // === Helper Methods ===
    private Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    private AttendanceRecord getAttendanceRecordByName(String name) {
        for (AttendanceRecord record : attendanceToday) {
            if (record.getStudent().getName().equalsIgnoreCase(name)) {
                return record;
            }
        }
        return null;
    }
}
