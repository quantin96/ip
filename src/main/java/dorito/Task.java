package dorito;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

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

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

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

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "][" + this.getPriorityIcon() + "] " + this.description;
    }
}
