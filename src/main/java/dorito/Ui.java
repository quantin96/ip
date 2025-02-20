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
}
