import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime dateTime;

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Event: " + name + "\nDate: " + dateTime.toLocalDate() + "\nTime: " + dateTime.toLocalTime();
    }
}

