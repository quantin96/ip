package dorito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an Event Task.
     *
     * @param description Description of task.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
