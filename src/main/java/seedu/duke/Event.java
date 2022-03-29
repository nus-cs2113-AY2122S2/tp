package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    protected LocalDate at;
    public String description;

    public Event(String description, LocalDate by) {
        this.description = description;
        this.at = at;
    }

    @Override
    public String toString(){
        String message = "Event " + description
                + " (at: " + getAt() + ")";
        return message;
    }

    public String toFileString() {
        return getDescription() + " | " + getAt();
    }

    public void setAt(String at) {
        this.at = LocalDate.parse(at);
    }

    public String getAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public String getDescription() {
        return description;
    }
}
