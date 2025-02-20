package dorito;

import java.util.ArrayList;
import java.util.Scanner;
import dorito.Task.Priority;

/**
 * Represents Ui object.
 */
public class Ui {
    protected boolean isExit = false;
    protected Scanner sc;
    protected String logo = "_________\n"
            + "\\       /\n"
            + " \\     / \n"
            + "  \\   /  \n"
            + "   \\ /   \n";

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Checks if the program has exited.
     *
     * @return A boolean indicating if program has exited.
     */
    public boolean shouldExit() {
        return isExit;
    }

    /**
     * Returns the next command.
     *
     * @return The next user input line.
     */
    public String get() {
        return sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void stop() {
        sc.close();
    }

    /**
     * Returns a hello message.
     *
     * @return String containing Dorito's welcome message.
     */
    public String start() {
        return logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0\n";
    }

    /**
     * Returns an exit message.
     *
     * @return String containing Dorito's exit message.
     */
    public String bye(){
        isExit = true;
        return "\nBye. Remember to stay hydrated!  >.<";
    }

    /**
     * Lists out all current tasks.
     *
     * @param tasks The current task list.
     * @return The current list of tasks in the task list.
     */
    public String list(ArrayList<Task> tasks) {
        assert tasks.size() >= 0 : "Cannot have negative tasks";
        String output  = "\nHere are the tasks in your list:  0.0\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            output += ((i + 1) + "." + t.toString() + "\n");
        }
        return output;
    }

    /**
     * Filters tasks that contain the key.
     *
     * @param tasks List of tasks.
     * @param key Key to filter tasks.
     * @return A list of filtered tasks containing the key in the task description.
     */
    public String find(ArrayList<Task> tasks, String key) {
        assert tasks.size() >= 0 : "Cannot have negative tasks";
        String output = "\nHere are the relevant tasks:  0.0\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.description.contains(key)) {
                output += ((i + 1) + "." + t.toString() + "\n");
            }
        }
        return output;
    }

    /**
     * Returns a message after adding a task.
     *
     * @param task The task to be added.
     * @param i Number of tasks in task list.
     * @return String indicating that the task has been added.
     */
    public String add(Task task, int i) {
        return "\nGot it. I've added this task:  >.<\n" + "  " + task
                + "\nNow you have " + i + " tasks in the list.  >.<\n";
    }

    /**
     * Returns a message after marking a task.
     *
     * @param task The task to be marked.
     * @return String indicating that the task has been marked.
     */
    public String mark(Task task) {
        return "\nNice! I've marked this task as done:  >.<\n" + "  " + task + "\n";
    }

    /**
     * Returns a message after unmarking a task.
     *
     * @param task The task to be unmarked.
     * @return String indicating that the task has been unmarked.
     */
    public String unmark(Task task) {
        return "\nOK! I've marked this task as not done:  0.0\n" + "  " + task + "\n";
    }

    /**
     * Returns a message after deleting a task.
     *
     * @param task The task to be deleted.
     * @param i Number of tasks remaining in task list.
     * @return String indicating that the task has been deleted.
     */
    public String delete(Task task, int i) {
        return "\nOK! I've removed this task:  0.0\n" + "  " + task
                + "\nNow you have " + i + " tasks in the list.  >.<\n";
    }

    /**
     * Returns a message after setting priority of a task.
     *
     * @param task The task to set priority.
     * @param p The priority of the task.
     * @return String indicating that the task priority has been set.
     */
    public String priority(Task task, Priority p) {
        return "\nOK! I've marked this task as " + p + " priority 0.0\n";
    }

    /**
     * Returns a default message when Dorito does not understand the input.
     *
     * @return String indicating that Dorito encountered a user input error.
     */
    public String sorry() {
        return "\nSorry! I don't understand what you mean!  >.<\n";
    }

    /**
     * Returns an error message when Dorito encounters a find error.
     *
     * @return String indicating that Dorito encountered a find error.
     */
    public String findError() {
        String message = "Sorry! Please specify a key to find tasks!  >.<";
        String format = "find <key>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters a todo error.
     *
     * @return String indicating that Dorito encountered a todo error.
     */
    public String toDoError() {
        String message = "Sorry! ToDo tasks must follow this format!  >.<";
        String format = "todo <task>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters a deadline error.
     *
     * @return String indicating that Dorito encountered a deadline error.
     */
    public String deadlineError() {
        String message = "Sorry! Deadline tasks must follow this format!  >.<";
        String format = "deadline <task> /by <YYYY-MM-DD>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters an event error.
     *
     * @return String indicating that Dorito encountered an event error.
     */
    public String eventError() {
        String message = "Sorry! Event tasks must follow this format!  >.<";
        String format = "event <task> /from <YYYY-MM-DD> /to <YYYY-MM-DD>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters a mark error.
     *
     * @return String indicating that Dorito encountered a mark error.
     */
    public String markError() {
        String message = "Sorry! Please specify a task number to mark!  >.<";
        String format = "mark <task number>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters an unmark error.
     *
     * @return String indicating that Dorito encountered an unmark error.
     */
    public String unmarkError() {
        String message = "Sorry! Please specify a task number to unmark!  >.<";
        String format = "unmark <task number>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters a delete error.
     *
     * @return String indicating that Dorito encountered a delete error.
     */
    public String deleteError() {
        String message = "Sorry! Please specify a task number to delete!  >.<";
        String format = "delete <task number>";
        return "\n" + message + "\n" + format + "\n";
    }

    /**
     * Returns an error message when Dorito encounters a priority error.
     *
     * @return String indicating that Dorito encountered a priority error.
     */
    public String priorityError() {
        String message = "Sorry! Prioritizing tasks must follow this format!  >.<";
        String format = "priority <task number> <priority>";
        String values = "Priority can only be (high, medium, low, default)";
        return "\n" + message + "\n" + format + "\n" + values + "\n";
    }
}
