public class Authorization {
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
