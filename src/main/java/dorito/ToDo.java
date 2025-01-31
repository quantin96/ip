package dorito;

public class ToDo extends Task {

    /**
     * Creates a ToDo Task.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
