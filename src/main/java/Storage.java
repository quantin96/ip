import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String path;

    public Storage(String path) {
        this.path = path;
    }

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

    public ArrayList<Task> loadFile() throws IOException {
        File f = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
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
        return tasks;
    }
}
