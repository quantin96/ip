package dorito;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String path;

    /**
     * Creates a new Storage.
     *
     * @param path Path of the file to store task info.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Rewrites the file with an updates list of tasks.
     *
     * @param tasks The updated list of tasks.
     */
    public void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            newText.append(t).append("\n");
        }
        fw.write(String.valueOf(newText));
        fw.close();
    }

    /**
     * Loads the saved tasks from a file.
     */
    public ArrayList<Task> loadFile() throws IOException {
        File f = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!f.exists()) {
            f.createNewFile();
        }
        assert f.exists() : "File does not exist";
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            Task task;
            if (s.charAt(1) == 'T') {
                task = loadToDo(s);
                tasks.add(task);
            } else if (s.charAt(1) == 'D') {
                task = loadDeadline(s);
                tasks.add(task);
            } else if (s.charAt(1) == 'E') {
                task = loadEvent(s);
                tasks.add(task);
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Loads ToDo task based on string from a file.
     *
     * @param s String representing ToDo task.
     */
    public ToDo loadToDo(String s) {
        ToDo task = new ToDo(s.substring(7));
        processMark(task, s);
        return task;
    }

    /**
     * Loads Deadline task based on string from a file.
     *
     * @param s String representing Deadline task.
     */
    public Deadline loadDeadline(String s) {
        DateTimeFormatter dateInput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dateOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sub = s.substring(7);
        String desc = sub.split(" \\(by: ")[0];
        String bytemp = sub.split(" \\(by: ")[1];
        String by = bytemp.substring(0, bytemp.length() - 1);
        Deadline task = new Deadline(desc, LocalDate.parse(by, dateInput).format(dateOutput));
        processMark(task, s);
        return task;
    }

    /**
     * Loads Event task based on string from a file.
     *
     * @param s String representing Event task.
     */
    public Event loadEvent(String s) {
        DateTimeFormatter dateInput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dateOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sub = s.substring(7);
        String desc = sub.split(" \\(from: ")[0];
        String time = sub.split(" \\(from: ")[1];
        String from = time.split( " to: ")[0];
        String totemp = time.split( " to: ")[1];
        String to = totemp.substring(0, totemp.length() - 1);
        Event task = new Event(desc,LocalDate.parse(from, dateInput).format(dateOutput),
                LocalDate.parse(to, dateInput).format(dateOutput));
        processMark(task, s);
        return task;
    }

    public void processMark(Task task, String s) {
        if (s.charAt(4) == 'X') {
            task.mark();
        }
    }
}
