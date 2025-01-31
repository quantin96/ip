package dorito;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Dorito {

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Dorito(String path) throws IOException {
        this.storage = new Storage(path);
        this.ui = new Ui();
        this.tasks = storage.loadFile();
    }

    public void run() throws IOException {
        ui.start();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                ui.stop();
                break;
            } else if (echo.equals("list")) {
                ui.list(tasks);
            } else {
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
                    storage.updateFile(tasks);
                    System.out.println("\nGot it. I've added this task:  >.<");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.  >.<\n");
                    continue;
                }
                if (echo.startsWith("deadline")) {
                    String sub = echo.substring(9);
                    String desc = sub.split(" /by ")[0];
                    String by = sub.split(" /by ")[1];
                    Deadline task = new Deadline(desc, by);
                    tasks.add(task);
                    storage.updateFile(tasks);
                    System.out.println("\nGot it. I've added this task:  >.<");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.  >.<\n");
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
                    storage.updateFile(tasks);
                    System.out.println("\nGot it. I've added this task:  >.<");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.  >.<\n");
                    continue;
                }
                if (echo.startsWith("mark")) {
                    Task task = tasks.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                    task.mark();
                    storage.updateFile(tasks);
                    System.out.println("\nNice! I've marked this task as done:  >.<");
                    System.out.println("  " + task + "\n");
                    continue;
                }
                if (echo.startsWith("unmark")) {
                    Task task = tasks.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                    task.unmark();
                    storage.updateFile(tasks);
                    System.out.println("\nOK! I've marked this task as not done:  0.0");
                    System.out.println("  " + task + "\n");
                    continue;
                }
                if (echo.startsWith("delete")) {
                    Task task = tasks.get(Integer.parseInt(echo.split(" ")[1]) - 1);
                    tasks.remove(Integer.parseInt(echo.split(" ")[1]) - 1);
                    storage.updateFile(tasks);
                    System.out.println("\nOK! I've removed this task:  0.0");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.  >.<\n");
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

    public static void main(String[] args) throws IOException {
        new Dorito("./data/dorito.txt").run();
    }

    public int size() {
        return this.tasks.size();
    }
}
