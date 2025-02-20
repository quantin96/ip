package dorito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Deadline object.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline Task.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
