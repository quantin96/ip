package dorito;

/**
 * Represents Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Represents the values that Priority can have.
     */
    public enum Priority {
        HIGH, MEDIUM, LOW, DEFAULT;
    }

    /**
     * Creates a Task that is unmarked with the given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.DEFAULT;
    }

    /**
     * Returns a string indicating whether a task is completed.
     *
     * @return "X" if done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string indicating the priority of the task.
     *
     * @return "H" if high, "M" if medium, "L" if low, " " if default.
     */
    public String getPriorityIcon() {
        String p;
        switch (this.priority) {
        case HIGH:
            p = "H";
            break;
        case MEDIUM:
            p = "M";
            break;
        case LOW:
            p = "L";
            break;
        default:
            p = " ";
            break;
        }
        return p;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Sets the priority of the task.
     *
     * @param p Priority of the task.
     */
    public void setPriority(Priority p) {
        this.priority = p;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "][" + this.getPriorityIcon() + "] " + this.description;
    }
}
