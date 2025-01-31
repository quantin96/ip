package dorito;

import java.util.ArrayList;

public class Ui {
    protected String logo = "_________\n"
            + "\\       /\n"
            + " \\     / \n"
            + "  \\   /  \n"
            + "   \\ /   \n";

    public Ui() {}

    /**
     * Prints a hello message.
     */
    public void start() {
        System.out.println(logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0\n");
    }

    /**
     * Prints an exit message.
     */
    public void stop() {
        System.out.println("\nBye. Remember to stay hydrated!  >.<");
    }

    /**
     * Lists out all current tasks.
     *
     * @param tasks The current task list.
     */
    public void list(ArrayList<Task> tasks) {
        System.out.println("\nHere are the tasks in your list:  0.0");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
        System.out.println();
    }

    /**
     * Filters tasks that contain the key.
     *
     * @param tasks List of tasks.
     * @param key Key to filter tasks.
     */
    public void find(ArrayList<Task> tasks, String key) {
        System.out.println("\nHere are the relevant tasks:  0.0");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.description.contains(key)) {
                System.out.println((i + 1) + "." + t);
            }
        }
        System.out.println();
    }
}
