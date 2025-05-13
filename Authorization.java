import java.util.Scanner;

public class Authorization {

    public static TemplateUser authorizeAndCreateUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Role (STUDENT, INSTRUCTOR, SUBSTITUTE_INSTRUCTOR): ");
        String roleInput = scanner.nextLine().toUpperCase();

        Role role;
        try {
            role = Role.valueOf(roleInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role. Access denied.");
            return null;
        }

        switch (role) {
            case STUDENT:
                return new Student(id, name);
            case INSTRUCTOR:
                return new Instructor(id, name);
            case SUBSTITUTE_INSTRUCTOR:
                return new SubstituteInstructor(id, name);
            default:
                System.out.println("Unknown role. Access denied.");
                return null;
        }
    }

    // 🚀 Run Role-Specific Menu
    public static void routeUserToDashboard(TemplateUser user) {
        if (user == null) return;

        user.viewDashboard(); // general dashboard message

        Scanner scanner = new Scanner(System.in);

        if (user instanceof Instructor) {
            Instructor instructor = (Instructor) user;
            System.out.println("Enter '1' to create course, '2' to view courses, '0' to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    instructor.createCourse(title, subject, java.time.LocalDateTime.now(), location);
                    break;
                case 2:
                    instructor.displayCourses();
                    break;
                case 0:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } else if (user instanceof Student) {
            Student student = (Student) user;
            System.out.println("Enter '1' to view enrolled courses, '0' to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                student.viewEnrolledCourses();
            }
        } else if (user instanceof SubstituteInstructor) {
            SubstituteInstructor sub = (SubstituteInstructor) user;
            System.out.println("Enter '1' to view taught courses, '0' to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                sub.displayCourses();
            }
        }
    }
}
