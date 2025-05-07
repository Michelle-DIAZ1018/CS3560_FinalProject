public class Models {
}

interface User {
    String getId();
    String getName();
    Role getRole();
    void viewDashboard();
}

//--------------------------------------------------------------------

enum Role {
    STUDENT,
    INSTRUCTOR,
    SUBSTITUTE_INSTRUCTOR
}

//--------------------------------------------------------------------

abstract class TemplateUser implements User {
    protected String id;
    protected String name;
    protected Role role;

    public TemplateUser(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Role getRole() { return role; }

    public abstract void viewDashboard();
}

//--------------------------------------------------------------------

class Student extends TemplateUser {
    public Student(String id, String name) {
        super(id, name, Role.STUDENT);
    }

    @Override
    public void viewDashboard() {
        System.out.println("Student Dashboard: View assignments, grades, and calendar.");
    }
}

//--------------------------------------------------------------------

class Instructor extends TemplateUser {
    public Instructor(String id, String name) {
        super(id, name, Role.INSTRUCTOR);
    }

    @Override
    public void viewDashboard() {
        System.out.println("Instructor Dashboard: Manage courses, assignments, and attendance.");
    }

    public void createCourse(String courseId, String title) {
        CourseManager.addCourse(new Course(courseId, title, this));
    }

    public void manageCourses() {
        for (Course c : CourseManager.getCoursesByInstructor(name)) {
            System.out.println(" - " + c.getTitle());
        }
    }
}

//--------------------------------------------------------------------

class SubstituteInstructor extends TemplateUser {
    public SubstituteInstructor(String id, String name) {
        super(id, name, Role.SUBSTITUTE_INSTRUCTOR);
    }

    @Override
    public void viewDashboard() {
        System.out.println("Substitute Dashboard: Access all courses and calendars.");
    }

    public void viewAllCourses() {
        for (Course c : CourseManager.getAllCourses()) {
            System.out.println(" - " + c.getTitle());
        }
    }
}

