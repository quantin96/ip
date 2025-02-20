package dorito;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents Dorito object.
 */
public class Dorito {

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new Dorito chatbot
     *
     * @param path Path to store tasks file.
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
                if (ui.shouldExit()) {
                    break;
                }
            } catch (Exception e) {
                ui.stop();
            }
        }
        ui.stop();
    }

    /**
     * Returns the welcome message.
     *
     * @return Dorito's welcome message
     */
    public String start() {
        return ui.start();
    }

    public static void main(String[] args) throws IOException {
        new Dorito("./data/dorito.txt").run();
    }

    /**
     * Returns the number of tasks.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input User input.
     * @return Dorito's response.
     */
    public String getResponse(String input) {
        return parser.parse(input, storage, tasks, ui);
    }
}
