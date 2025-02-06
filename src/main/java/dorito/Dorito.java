package dorito;

import java.util.ArrayList;
import java.io.IOException;

public class Dorito {

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new Dorito chatbot
     *
     * @param path Path of the file to store task info.
     */
    public Dorito(String path) throws IOException {
        this.storage = new Storage(path);
        this.ui = new Ui();
        this.tasks = storage.loadFile();
        this.parser = new Parser();
    }

    /**
     * Runs the Dorito chatbot.
     */
    public void run(){
        System.out.println(ui.start());
        while (true) {
            try {
                String input = ui.get();
                String output = parser.parse(input, storage, tasks, ui);
                System.out.println(output);
                if (ui.isExit()) {
                    break;
                }
            } catch (Exception e) {
                ui.stop();
            }
        }
        ui.stop();
    }

    public static void main(String[] args) throws IOException {
        new Dorito("./data/dorito.txt").run();
    }

    /**
     * Returns the number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }
}
