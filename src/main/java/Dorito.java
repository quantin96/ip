import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dorito {

    private static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/dorito.txt");
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            newText.append(t).append("\n");
        }
        fw.write(String.valueOf(newText));
        fw.close();
    }

    private static void loadFile(ArrayList<Task> tasks) throws IOException {
        File f = new File("./data/dorito.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        DateTimeFormatter dateInput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dateOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            Task task;
            if (s.charAt(1) == 'T') {
                task = new ToDo(s.substring(7));
            } else if (s.charAt(1) == 'D') {
                String sub = s.substring(7);
                String desc = sub.split(" \\(by: ")[0];
                String bytemp = sub.split(" \\(by: ")[1];
                String by = bytemp.substring(0, bytemp.length() - 1);
                System.out.println((by));
                task = new Deadline(desc, LocalDate.parse(by, dateInput).format(dateOutput));
            } else {
                String sub = s.substring(7);
                String desc = sub.split(" \\(from: ")[0];
                String time = sub.split(" \\(from: ")[1];
                String from = time.split( " to: ")[0];
                String totemp = time.split( " to: ")[1];
                String to = totemp.substring(0, totemp.length() - 1);
                task = new Event(desc, LocalDate.parse(from, dateInput).format(dateOutput),
                        LocalDate.parse(to, dateInput).format(dateOutput));
            }
            if (s.charAt(4) == 'X') {
                task.mark();
            }
            tasks.add(task);
        }
        sc.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String logo = "_________\n"
                + "\\       /\n"
                + " \\     / \n"
                + "  \\   /  \n"
                + "   \\ /   \n";
        ArrayList<Task> tasks = new ArrayList<>();
        loadFile(tasks);
        int taskNo = tasks.size();

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
                    Task t = tasks.get(i);
                    System.out.println((i + 1) + "." + t);
                }
                System.out.println();
                continue;
            }
            if (echo.startsWith("todo")) {
                try {
                    if (echo.length() < 6) {
                        throw new DoritoException("Sorry! Please give a description for your task!  >.<");
                    }
                } catch (DoritoException e) {
                    System.out.println("\n" + e.getMessage() + "\n");
                    continue;
                }
                    ToDo task = new ToDo(echo.substring(5));
                    tasks.add(task);
                    taskNo++;
                    updateFile(tasks);
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
                tasks.add(task);
                taskNo++;
                updateFile(tasks);
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
                tasks.add(task);
                taskNo++;
                updateFile(tasks);
                System.out.println("\nGot it. I've added this task:  >.<");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskNo + " tasks in the list.  >.<\n");
                continue;
            }
            if (echo.startsWith("mark")) {
                Task task = tasks.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                task.mark();
                updateFile(tasks);
                System.out.println("\nNice! I've marked this task as done:  >.<");
                System.out.println("  " + task + "\n");
                continue;
            }
            if (echo.startsWith("unmark")) {
                Task task = tasks.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                task.unmark();
                updateFile(tasks);
                System.out.println("\nOK! I've marked this task as not done:  0.0");
                System.out.println("  " + task + "\n");
                continue;
            }
            if (echo.startsWith("delete")) {
                Task task = tasks.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                tasks.remove(Integer.parseInt(echo.split(" ")[1]) - 1);
                taskNo--;
                updateFile(tasks);
                System.out.println("\nOK! I've removed this task:  0.0");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskNo + " tasks in the list.  >.<\n");
                continue;
            }
            try {
                throw new DoritoException("Sorry! I don't understand what you mean!  >.<");
            } catch (DoritoException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        }
    }
}
