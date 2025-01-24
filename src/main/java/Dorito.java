import java.util.Scanner;

public class Dorito {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "_________\n"
                + "\\       /\n"
                + " \\     / \n"
                + "  \\   /  \n"
                + "   \\ /   \n";
        Task[] tasks = new Task[100];
        int taskNo = 0;

        System.out.println(logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0");
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye. Remember to stay hydrated!  >.<");
                sc.close();
                break;
            }
            if (echo.equals("list")) {
                System.out.println("Here are the tasks in your list:  0.0");
                for (int i = 0; i < taskNo; i++) {
                    Task task = tasks[i];
                    System.out.println((i + 1) + ".[" + task.getStatusIcon() + "] " + task);
                }
                continue;
            }
            if (echo.startsWith("mark")) {
                Task task = tasks[Integer.parseInt(echo.split(" ")[1]) - 1];
                task.mark();
                System.out.println("Nice! I've marked this task as done:  >.<");
                System.out.println("[" + task.getStatusIcon() + "] " + task);
                continue;
            }
            if (echo.startsWith("unmark")) {
                Task task = tasks[Integer.parseInt(echo.split(" ")[1]) - 1];
                task.unmark();
                System.out.println("OK! I've marked this task as not done:  0.0");
                System.out.println("[" + task.getStatusIcon() + "] " + task);
                continue;
            }
            Task task = new Task(echo);
            tasks[taskNo] = task;
            taskNo++;
            System.out.println("added: " + task + "  >.<");
        }
    }
}
