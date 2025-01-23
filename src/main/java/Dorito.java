import java.util.Scanner;

public class Dorito {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "_________\n"
                + "\\       /\n"
                + " \\     / \n"
                + "  \\   /  \n"
                + "   \\ /   \n";
        String exit = "bye";
        String list = "list";
        String[] tasks = new String[100];
        int task = 0;

        System.out.println(logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0");
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals(exit)) {
                System.out.println("Bye. Remember to stay hydrated!  >.<");
                sc.close();
                break;
            }
            if (echo.equals(list)) {
                if (task == 0) {
                    System.out.println("No current tasks!  >.<");
                }
                for (int i = 0; i < task; i++) {
                    if (i + 1 == task) {
                        System.out.println((i + 1) + ". " + tasks[i] + "  0.0");
                        break;
                    }
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                continue;
            }
            tasks[task] = echo;
            task++;
            System.out.println("added: " + echo + "  >.<");
        }
    }
}
