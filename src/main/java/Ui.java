import java.util.ArrayList;

public class Ui {
    protected String logo = "_________\n"
            + "\\       /\n"
            + " \\     / \n"
            + "  \\   /  \n"
            + "   \\ /   \n";

    public Ui() {}

    public void start() {
        System.out.println(logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0\n");
    }

    public void stop() {
        System.out.println("\nBye. Remember to stay hydrated!  >.<");
    }

    public void list(ArrayList<Task> tasks) {
        System.out.println("\nHere are the tasks in your list:  0.0");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
        System.out.println();
    }
}
