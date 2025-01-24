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
                + "What can I do for you?  0.0\n");
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                System.out.println("\nBye. Remember to stay hydrated!  >.<");
                sc.close();
                break;
            }
            if (echo.equals("list")) {
                System.out.println("\nHere are the tasks in your list:  0.0");
                for (int i = 0; i < taskNo; i++) {
                    Task task = tasks[i];
                    System.out.println((i + 1) + "." + task);
                }
                System.out.println("");
                continue;
            }
            if (echo.startsWith("todo")) {
                ToDo task = new ToDo(echo.substring(5));
                tasks[taskNo] = task;
                taskNo++;
                System.out.println("\nGot it. I've added this task:  >.<");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskNo + " tasks in the list.  >.<\n");
                continue;
            }
            if (echo.startsWith("deadline")) {
                String sub = echo.substring(9);
                String desc = sub.split(" /by ")[0];
                String by = sub.split(" /by ")[1];
                Deadline task = new Deadline(desc, by);
                tasks[taskNo] = task;
                taskNo++;
                System.out.println("\nGot it. I've added this task:  >.<");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskNo + " tasks in the list.  >.<\n");
                continue;
            }
            if (echo.startsWith("event")) {
                String sub = echo.substring(6);
                String desc = sub.split(" /from ")[0];
                String time = sub.split(" /from ")[1];
                String from = time.split( " /to ")[0];
                String to = time.split( " /to ")[1];
                Event task = new Event(desc, from, to);
                tasks[taskNo] = task;
                taskNo++;
                System.out.println("\nGot it. I've added this task:  >.<");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskNo + " tasks in the list.  >.<\n");
                continue;
            }
            if (echo.startsWith("mark")) {
                Task task = tasks[Integer.parseInt(echo.split(" ")[1]) - 1];
                task.mark();
                System.out.println("\nNice! I've marked this task as done:  >.<");
                System.out.println("  " + task + "\n");
                continue;
            }
            if (echo.startsWith("unmark")) {
                Task task = tasks[Integer.parseInt(echo.split(" ")[1]) - 1];
                task.unmark();
                System.out.println("\nOK! I've marked this task as not done:  0.0");
                System.out.println("  " + task + "\n");
                continue;
            }
        }
    }
}
