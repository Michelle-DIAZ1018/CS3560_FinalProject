import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SchoolCalendar {
    private List<Event> events;

    public SchoolCalendar() {
        events = new ArrayList<>();
    }

    // Add event - only allowed by a teacher
    public void addEvent(Instructor teacher, String name, LocalDateTime dateTime) {
        if (teacher != null) {
            Event newEvent = new Event(name, dateTime);
            events.add(newEvent);
            System.out.println("Event added: " + newEvent);
        } else {
            System.out.println("Only teachers can add events.");
        }
    }

    // Edit event - only allowed by a teacher
    public void editEvent(Instructor teacher, Event event, String newName, LocalDateTime newDateTime) {
        if (teacher != null) {
            Event finalEvent = event;
            event = events.stream()
                    .filter(e -> e.equals(finalEvent))
                    .findFirst()
                    .orElse(null);
            if (event != null) {
                // Assuming event is editable by the teacher
                events.remove(event);
                events.add(new Event(newName, newDateTime));
                System.out.println("Event updated to: " + newName + " on " + newDateTime);
            } else {
                System.out.println("Event not found.");
            }
        } else {
            System.out.println("Only teachers can edit events.");
        }
    }

    // View all events - available to everyone
    public void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
        } else {
            System.out.println("Scheduled Events:");
            for (Event event : events) {
                System.out.println(event);
                System.out.println("------------------------------------");
            }
        }
    }
}
