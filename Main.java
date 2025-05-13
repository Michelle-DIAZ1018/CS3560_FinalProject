import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the School Management System");
        System.out.println("Choose your role: 1 - Student, 2 - Instructor, 3 - Substitute Instructor");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                // --- Student Section ---
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                System.out.print("Enter student name: ");
                String studentName = scanner.nextLine();
                Student student = new Student(studentId, studentName);
                System.out.println("\nLogged in as student: " + studentName);

                boolean studentRunning = true;
                while (studentRunning) {
                    System.out.println("\n--- Student Dashboard ---");
                    System.out.println("1. Course Details");
                    System.out.println("2. Assignments");
                    System.out.println("3. Grades");
                    System.out.println("4. Calendar");
                    System.out.println("5. Exit");
                    System.out.print("Select an option: ");
                    int studentOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (studentOption) {
                        case 1:
                            System.out.print("Enter course name: ");
                            String courseName = scanner.nextLine();
                            System.out.print("Enter subject: ");
                            String subject = scanner.nextLine();
                            System.out.print("Enter date and time: ");
                            String dateTimeStr = scanner.nextLine();
                            LocalDateTime courseTime = LocalDateTime.parse(dateTimeStr.replace(" ", "T"));
                            System.out.print("Enter room: ");
                            String room = scanner.nextLine();

                            student.createCourse(courseName, subject, courseTime, room);
                            student.enrollInCourse();
                            student.viewEnrolledCourses();
                            break;

                        case 2:
                            System.out.print("Enter assignment title: ");
                            String assignmentTitle = scanner.nextLine();
                            System.out.print("Enter assignment description: ");
                            String assignmentDesc = scanner.nextLine();
                            student.submitAssignment(assignmentTitle, assignmentDesc);
                            break;

                        case 3:
                            System.out.print("Enter course name to view grade: ");
                            String courseForGrade = scanner.nextLine();
                            student.viewGrades(courseForGrade);
                            break;

                        case 4:
                            student.viewEvents();
                            break;

                        case 5:
                            studentRunning = false;
                            System.out.println("Exiting student dashboard...");
                            break;

                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                }
                break;

            case 2:
                // --- Instructor Section ---
                System.out.print("Enter instructor ID: ");
                String instructorId = scanner.nextLine();
                System.out.print("Enter instructor name: ");
                String instructorName = scanner.nextLine();
                Instructor instructor = new Instructor(instructorId, instructorName);
                System.out.println("\nLogged in as instructor: " + instructorName);

                boolean instructorRunning = true;
                while (instructorRunning) {
                    System.out.println("\n--- Instructor Dashboard ---");
                    System.out.println("1. Courses");
                    System.out.println("2. Attendance");
                    System.out.println("3. Grade Book");
                    System.out.println("4. Calendar");
                    System.out.println("5. Save Course");
                    System.out.println("6. Exit");
                    System.out.print("Select an option: ");
                    int instructorOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (instructorOption) {
                        case 1:
                            boolean courseMenu = true;
                            while (courseMenu) {
                                System.out.println("\n--- Course Management Menu ---");
                                System.out.println("1. Create Course");
                                System.out.println("2. View All Courses");
                                System.out.println("3. Delete Course");
                                System.out.println("4. Back");
                                System.out.print("Select an option: ");
                                int courseOption = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                switch (courseOption) {
                                    case 1:
                                        System.out.println("\n--- Create New Course ---");

                                        System.out.print("Enter course name: ");
                                        String courseName = scanner.nextLine();

                                        System.out.print("Enter subject: ");
                                        String subject = scanner.nextLine();

                                        System.out.print("Enter meeting days (e.g. Monday, Wednesday): ");
                                        String meetingDays = scanner.nextLine();

                                        System.out.print("Enter meeting time (e.g. 2:00 PM – 3:30 PM): ");
                                        String meetingTime = scanner.nextLine();

                                        System.out.print("Enter room: ");
                                        String room = scanner.nextLine();

                                        instructor.createCourse(courseName, subject, meetingDays, meetingTime, room);

                                        System.out.println("\nCourse created successfully. Summary:");
                                        System.out.println("Course Name   : " + courseName);
                                        System.out.println("Subject       : " + subject);
                                        System.out.println("Meeting Days  : " + meetingDays);
                                        System.out.println("Meeting Time  : " + meetingTime);
                                        System.out.println("Room          : " + room);
                                        break;

                                    default:
                                        System.out.println("Invalid option. Try again.");
                                }
                            }
                            break;



                        case 2:
                            boolean attendanceMenu = true;
                            while (attendanceMenu) {
                                System.out.println("\n--- Attendance Menu ---");
                                System.out.println("1. Check-in Student");
                                System.out.println("2. Check-out Student");
                                System.out.println("3. Attendance Records");
                                System.out.println("4. Back");
                                System.out.print("Select an option: ");
                                int attendanceOption = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                switch (attendanceOption) {
                                    case 1:
                                        System.out.print("Enter student name to check-in: ");
                                        String checkInName = scanner.nextLine();
                                        LocalDateTime checkInTime = LocalDateTime.now();
                                        instructor.checkInStudent(checkInName);
                                        System.out.println("Checked in " + checkInName + " at " + checkInTime);
                                        break;


                                    case 2:
                                        System.out.print("Enter student name to check-out: ");
                                        String checkOutName = scanner.nextLine();
                                        LocalDateTime checkOutTime = LocalDateTime.now();
                                        System.out.print("Is check-out parent approved? (true/false): ");
                                        boolean approved = Boolean.parseBoolean(scanner.nextLine());
                                        instructor.checkOutStudent(checkOutName, approved);
                                        System.out.println("Checked Out " + checkOutName + " at " + checkOutTime);
                                        break;

                                    case 3:
                                        System.out.println("\n--- Attendance Records ---");
                                        instructor.printDailyAttendance();
                                        instructor.printAbsenceReport();
                                        break;

                                    case 4:
                                        attendanceMenu = false; // Exit sub-menu
                                        break;

                                    default:
                                        System.out.println("Invalid option. Try again.");
                                }
                            }
                            break;


                        case 3:
                            boolean gradeMenuRunning = true;
                            while (gradeMenuRunning) {
                                System.out.println("\n--- Grade Book ---");
                                System.out.println("1. Assign Grade");
                                System.out.println("2. View Grade Book");
                                System.out.println("3. Back");
                                System.out.print("Choose option: ");
                                int gradeOption = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                switch (gradeOption) {
                                    case 1:
                                        System.out.print("Enter student name to assign grade: ");
                                        String studentForGrade = scanner.nextLine();
                                        System.out.print("Enter course name: ");
                                        String courseForGrade = scanner.nextLine();
                                        System.out.print("Enter grade: ");
                                        String grade = scanner.nextLine();
                                        instructor.assignGrade(studentForGrade, courseForGrade, grade);
                                        break;

                                    case 2:
                                        instructor.printGradeBook();
                                        break;

                                    case 3:
                                        gradeMenuRunning = false; // Exit the gradebook menu
                                        break;

                                    default:
                                        System.out.println("Invalid option.");
                                }
                            }
                            break;


                        case 4:
                            instructor.viewEvents();
                            break;

                        case 5:
                            System.out.print("Enter course title to save: ");
                            String courseTitle = scanner.nextLine();
                            System.out.print("Enter file name to save: ");
                            String fileName = scanner.nextLine();
                            // Save the course details to a file (You can implement this functionality based on your file-handling logic)
                            System.out.println("Course saved as " + fileName);
                            break;

                        case 6:
                            instructorRunning = false;
                            System.out.println("Exiting instructor dashboard...");
                            break;

                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                }
                break;

            case 3:
                // --- Substitute Instructor Section ---
                System.out.print("Enter substitute instructor ID: ");
                String subId = scanner.nextLine();
                System.out.print("Enter substitute instructor name: ");
                String subName = scanner.nextLine();
                SubstituteInstructor sub = new SubstituteInstructor(subId, subName);
                System.out.println("\nLogged in as substitute instructor: " + subName);

                boolean subRunning = true;
                while (subRunning) {
                    System.out.println("\n--- Substitute Instructor Dashboard ---");
                    System.out.println("1. Course Details");
                    System.out.println("2. Attendance");
                    System.out.println("3. Calendar");
                    System.out.println("4. Exit");
                    System.out.print("Select an option: ");
                    int subOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (subOption) {
                        case 1:
                            System.out.print("Enter student ID: ");
                            String newId = scanner.nextLine();
                            System.out.print("Enter student name: ");
                            String newName = scanner.nextLine();
                            sub.addStudent(newId, newName);
                            break;

                        case 2:
                            System.out.print("Enter student name to check-in: ");
                            String checkName = scanner.nextLine();
                            sub.checkInStudent(checkName);
                            break;

                        case 3:
                            sub.printDailyAttendance();
                            break;

                        case 4:
                            subRunning = false;
                            System.out.println("Exiting substitute instructor dashboard...");
                            break;

                        default:
                            System.out.println("Invalid option. Try again.");
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }

        scanner.close();
    }
}
