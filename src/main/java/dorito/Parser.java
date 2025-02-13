package dorito;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public Parser() {}

    public enum Commands {
        BYE, LIST, FIND, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }

    /**
     * Parses the input.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     */
    public String parse(String input, Storage storage, ArrayList<Task> tasks, Ui ui) {
        String output = "";
        try {
            Commands command = Commands.valueOf(input.split(" ")[0].toUpperCase());
            switch(command) {
            case BYE:
                output = ui.bye();
                break;
            case LIST:
                output = ui.list(tasks);
                break;
            case FIND:
                output = ui.find(tasks, input.split(" ")[1]);
                break;
            case TODO:
                try {
                    if (input.length() < 6) {
                        throw new DoritoException("Sorry! Please give a description for your task!  >.<");
                    }
                } catch (DoritoException e) {
                    return "\n" + e.getMessage() + "\n";
                }
                output = processToDo(input, tasks, storage, ui);
                break;
            case DEADLINE:
                output = processDeadline(input, tasks, storage, ui);
                break;
            case EVENT:
                output = processEvent(input, tasks, storage, ui);
                break;
            case MARK:
                Task mark = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                mark.mark();
                storage.updateFile(tasks);
                output = ui.mark(mark);
                break;
            case UNMARK:
                Task unmark = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                unmark.unmark();
                storage.updateFile(tasks);
                output = ui.unmark(unmark);
                break;
            case DELETE:
                Task delete = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                tasks.remove(Integer.parseInt(input.split(" ")[1]) - 1);
                storage.updateFile(tasks);
                output = ui.delete(delete, tasks.size());
                break;
            default:
                ui.sorry();
            }
        } catch (Exception e) {
            ui.sorry();
        }
        return output;
    }

    /**
     * Processes ToDo tasks for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     */
    public String processToDo (String input, ArrayList<Task> tasks, Storage storage, Ui ui) throws IOException {
        ToDo todo = new ToDo(input.substring(5));
        tasks.add(todo);
        storage.updateFile(tasks);
        return ui.add(todo, tasks.size());
    }

    /**
     * Processes Deadline tasks for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     */
    public String processDeadline (String input, ArrayList<Task> tasks, Storage storage, Ui ui) throws IOException {
        String deadlineSub = input.substring(9);
        String deadlineDesc = deadlineSub.split(" /by ")[0];
        String by = deadlineSub.split(" /by ")[1];
        Deadline deadline = new Deadline(deadlineDesc, by);
        tasks.add(deadline);
        storage.updateFile(tasks);
        return ui.add(deadline, tasks.size());
    }

    /**
     * Processes Event tasks for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     */
    public String processEvent (String input, ArrayList<Task> tasks, Storage storage, Ui ui) throws IOException {
        String eventSub = input.substring(6);
        String eventDesc = eventSub.split(" /from ")[0];
        String time = eventSub.split(" /from ")[1];
        String from = time.split( " /to ")[0];
        String to = time.split( " /to ")[1];
        Event event = new Event(eventDesc, from, to);
        tasks.add(event);
        storage.updateFile(tasks);
        return ui.add(event, tasks.size());
    }
}
