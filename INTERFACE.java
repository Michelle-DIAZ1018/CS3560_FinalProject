// This is a simple interface that we used to get the basic information of each person in the system.
interface User {
    String getId(); // returns the ID of each person (EX: "12345")
    String getName(); // returns the person's name (EX: "Sally")
    Role getRole(); // returns the person's role (EX: "Student")
    void viewDashboard(); // Will display the dashboard according to each person's role
}

//--------------------------------------------------------------------//

// The list of all the fixed constants of the program, as our focus is on three main roles: student, teacher, and sub
// These variables will NOT change, as it will help with consistency in services and entities
enum Role {
    STUDENT,
    INSTRUCTOR,
    SUBSTITUTE_INSTRUCTOR
}

//--------------------------------------------------------------------//

// the implementation of the interface of USER with constructors and methods
abstract class TemplateUser implements User {
    // instead of being private, they are protected so subclasses can access them
    protected String id;
    protected String name;
    protected Role role;

    // initialization of the variables
    public TemplateUser(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // the implementations of each of the methods
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Role getRole() {
        return role;
    }
    public abstract void viewDashboard();

}


