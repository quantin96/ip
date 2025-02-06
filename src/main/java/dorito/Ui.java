package dorito;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected boolean exit = false;
    protected Scanner sc;
    protected String logo = "_________\n"
            + "\\       /\n"
            + " \\     / \n"
            + "  \\   /  \n"
            + "   \\ /   \n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Checks if the program has exited.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Returns the next command.
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
     */
    public String start() {
        return logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0\n";
    }

    /**
     * Returns an exit message.
     */
    public String bye(){
        exit = true;
        return "\nBye. Remember to stay hydrated!  >.<";
    }

    /**
     * Lists out all current tasks.
     *
     * @param tasks The current task list.
     */
    public String list(ArrayList<Task> tasks) {
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
     */
    public String find(ArrayList<Task> tasks, String key) {
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
     */
    public String add(Task task, int i) {
        return "\nGot it. I've added this task:  >.<\n" + "  " + task
                + "\nNow you have " + i + " tasks in the list.  >.<\n";
    }

    /**
     * Returns a message after marking a task.
     *
     * @param task The task to be marked.
     */
    public String mark(Task task) {
        return "\nNice! I've marked this task as done:  >.<\n" + "  " + task + "\n";
    }

    /**
     * Returns a message after unmarking a task.
     *
     * @param task The task to be unmarked.
     */
    public String unmark(Task task) {
        return "\nOK! I've marked this task as not done:  0.0\n" + "  " + task + "\n";
    }

    /**
     * Returns a message after deleting a task.
     *
     * @param task The task to be deleted.
     * @param i Number of tasks remaining in task list.
     */
    public String delete(Task task, int i) {
        return "\nOK! I've removed this task:  0.0\n" + "  " + task
                + "\nNow you have " + i + " tasks in the list.  >.<\n";
    }
}
