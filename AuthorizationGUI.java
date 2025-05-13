import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizationGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAuthorizationFrame());
    }

    private static void createAuthorizationFrame() {
        JFrame frame = new JFrame("User Authorization LOGIN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 20));
        panel.setPreferredSize(new Dimension(450, 300));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        Font labelFont = new Font("SansSerif", Font.BOLD, 18);
        Font inputFont = new Font("SansSerif", Font.PLAIN, 18);

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        idField.setFont(inputFont);
        nameField.setFont(inputFont);

        String[] roles = {"STUDENT", "INSTRUCTOR", "SUBSTITUTE_INSTRUCTOR"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setFont(inputFont);

        JLabel idLabel = new JLabel("Enter ID:");
        JLabel nameLabel = new JLabel("Enter Name:");
        JLabel roleLabel = new JLabel("Select Role:");
        idLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        roleLabel.setFont(labelFont);

        JButton submitButton = new JButton("Login");
        submitButton.setFont(labelFont);
        JLabel statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setFont(labelFont);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(roleLabel);
        panel.add(roleBox);
        panel.add(new JLabel(""));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        panel.add(buttonPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 0, 0, 0);
        frame.add(panel, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String roleInput = ((String) roleBox.getSelectedItem()).toUpperCase();

                Role role;
                try {
                    role = Role.valueOf(roleInput);
                } catch (IllegalArgumentException ex) {
                    statusLabel.setText("Invalid role selected.");
                    return;
                }

                TemplateUser user;
                switch (role) {
                    case STUDENT:
                        user = new Student(id, name);
                        break;
                    case INSTRUCTOR:
                        user = new Instructor(id, name);
                        break;
                    case SUBSTITUTE_INSTRUCTOR:
                        user = new SubstituteInstructor(id, name);
                        break;
                    default:
                        statusLabel.setText("Unknown role.");
                        return;
                }

                frame.dispose();
                showDashboard(user);
            }
        });
    }

    private static void showDashboard(TemplateUser user) {
        JFrame dashboard = new JFrame("Dashboard - " + user.getName());
        dashboard.setSize(600, 500);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font bigFont = new Font("SansSerif", Font.BOLD, 22);
        Dimension buttonSize = new Dimension(250, 50);

        CardLayout cl = new CardLayout();
        JPanel mainPanel = new JPanel(cl);
        dashboard.add(mainPanel);

        JPanel instructorPanel = new JPanel();
        instructorPanel.setLayout(new BoxLayout(instructorPanel, BoxLayout.Y_AXIS));
        instructorPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.add(instructorPanel, "InstructorHome");

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getName() + "!");
        welcomeLabel.setFont(bigFont);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (user instanceof Instructor) {
            String[] subjects = {"Math", "English", "History", "Science"};

            for (String subject : subjects) {
                JPanel subjectPanel = new JPanel();
                subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.Y_AXIS));
                subjectPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

                JLabel subjectLabel = new JLabel("Welcome to " + subject);
                subjectLabel.setFont(bigFont);
                subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                subjectPanel.add(subjectLabel);
                subjectPanel.add(Box.createVerticalStrut(30));

                JButton detailsBtn = new JButton("Course Details");
                JButton attendanceBtn = new JButton("Attendance");
                JButton gradebookBtn = new JButton("Gradebook");
                JButton calendarBtn = new JButton("Calendar");
                JButton backBtn = new JButton("Back");

                JButton[] buttons = {detailsBtn, attendanceBtn, gradebookBtn, calendarBtn, backBtn};

                for (JButton btn : buttons) {
                    btn.setFont(bigFont);
                    btn.setMaximumSize(buttonSize);
                    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    subjectPanel.add(btn);
                    subjectPanel.add(Box.createVerticalStrut(20));
                }

                String subjectMenuName = subject + "Menu";
                mainPanel.add(subjectPanel, subjectMenuName);

                JPanel attendancePanel = new JPanel();
                attendancePanel.setLayout(new BoxLayout(attendancePanel, BoxLayout.Y_AXIS));
                attendancePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

                JLabel attendanceLabel = new JLabel("Attendance - " + subject);
                attendanceLabel.setFont(bigFont);
                attendanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                attendancePanel.add(attendanceLabel);
                attendancePanel.add(Box.createVerticalStrut(30));

                JButton checkInBtn = new JButton("Check-In");
                JButton checkOutBtn = new JButton("Check-Out");
                JButton viewAttendanceBtn = new JButton("Attendance Record");
                JButton attendanceBackBtn = new JButton("Back");

                JButton[] attendanceButtons = {checkInBtn, checkOutBtn, viewAttendanceBtn, attendanceBackBtn};

                for (JButton btn : attendanceButtons) {
                    btn.setFont(bigFont);
                    btn.setMaximumSize(buttonSize);
                    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    attendancePanel.add(btn);
                    attendancePanel.add(Box.createVerticalStrut(20));
                }

                String attendancePanelName = subject + "Attendance";
                mainPanel.add(attendancePanel, attendancePanelName);

                detailsBtn.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, subject + " - Course Details coming soon."));
                attendanceBtn.addActionListener(e -> cl.show(mainPanel, attendancePanelName));
                gradebookBtn.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, subject + " - Gradebook coming soon."));
                calendarBtn.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, subject + " - Calendar coming soon."));
                backBtn.addActionListener(e -> cl.show(mainPanel, "InstructorHome"));

                checkInBtn.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Check-In recorded for " + subject + "."));
                checkOutBtn.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Check-Out recorded for " + subject + "."));
                viewAttendanceBtn.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Attendance record for " + subject + " coming soon."));
                attendanceBackBtn.addActionListener(e -> cl.show(mainPanel, subjectMenuName));
            }

            JButton mathButton = new JButton("Math");
            JButton englishButton = new JButton("English");
            JButton historyButton = new JButton("History");
            JButton scienceButton = new JButton("Science");
            JButton createCourseButton = new JButton("Create a Course");
            JButton contactButton = new JButton("Contact");

            JButton[] instructorButtons = {
                    mathButton, englishButton, historyButton, scienceButton, createCourseButton, contactButton
            };

            instructorPanel.add(Box.createVerticalGlue());
            instructorPanel.add(welcomeLabel);
            instructorPanel.add(Box.createVerticalStrut(30));

            for (JButton btn : instructorButtons) {
                btn.setFont(bigFont);
                btn.setMaximumSize(buttonSize);
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                instructorPanel.add(btn);
                instructorPanel.add(Box.createVerticalStrut(20));
            }

            instructorPanel.add(Box.createVerticalGlue());

            mathButton.addActionListener(e -> cl.show(mainPanel, "MathMenu"));
            englishButton.addActionListener(e -> cl.show(mainPanel, "EnglishMenu"));
            historyButton.addActionListener(e -> cl.show(mainPanel, "HistoryMenu"));
            scienceButton.addActionListener(e -> cl.show(mainPanel, "ScienceMenu"));
            createCourseButton.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Course creation logic here."));
            contactButton.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Contact support feature coming soon."));

            cl.show(mainPanel, "InstructorHome");
        } else if (user instanceof Student) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            mainPanel.add(panel, "Student");

            JLabel label = new JLabel("Welcome, " + user.getName() + "!");
            label.setFont(bigFont);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalGlue());
            panel.add(label);
            panel.add(Box.createVerticalStrut(30));

            JButton courseDetailsButton = new JButton("Courses Details");
            JButton assignmentsButton = new JButton("Assignments");
            JButton gradesButton = new JButton("Grades");
            JButton calendarButton = new JButton("Calendar");

            JButton[] studentButtons = {courseDetailsButton, assignmentsButton, gradesButton, calendarButton};

            for (JButton btn : studentButtons) {
                btn.setFont(bigFont);
                btn.setMaximumSize(buttonSize);
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(btn);
                panel.add(Box.createVerticalStrut(20));
            }

            panel.add(Box.createVerticalGlue());
            cl.show(mainPanel, "Student");

            courseDetailsButton.addActionListener(e -> ((Student) user).viewEnrolledCourses());
            assignmentsButton.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Assignments feature coming soon."));
            gradesButton.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Grades feature coming soon."));
            calendarButton.addActionListener(e -> JOptionPane.showMessageDialog(dashboard, "Calendar feature coming soon."));
        } else if (user instanceof SubstituteInstructor) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            mainPanel.add(panel, "Sub");

            JButton viewCoursesButton = new JButton("View Taught Courses");
            viewCoursesButton.setFont(bigFont);
            viewCoursesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(viewCoursesButton);

            cl.show(mainPanel, "Sub");
        }

        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
    }
}

// Assume Role enum and TemplateUser/Student/Instructor/SubstituteInstructor are properly defined elsewhere
