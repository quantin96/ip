package dorito;

import java.io.IOException;
import java.util.ArrayList;
import dorito.Task.Priority;

/**
 * Represents Parser object.
 */
public class Parser {

    public Parser() {}

    /**
     * Represents the Dorito commands.
     */
    public enum Commands {
        BYE, LIST, FIND, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, PRIORITY
    }

    /**
     * Parses the input.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String containing Dorito's response.
     */
    public String parse(String input, Storage storage, ArrayList<Task> tasks, Ui ui) {
        assert tasks.size() >= 0 : "Cannot have negative tasks";
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
                output = processFind(input, tasks, ui);
                break;
            case TODO:
                output = processToDo(input, tasks, storage, ui);
                break;
            case DEADLINE:
                output = processDeadline(input, tasks, storage, ui);
                break;
            case EVENT:
                output = processEvent(input, tasks, storage, ui);
                break;
            case MARK:
                output = processMark(input, tasks, storage, ui);
                break;
            case UNMARK:
                output = processUnmark(input, tasks, storage, ui);
                break;
            case DELETE:
                output = processDelete(input, tasks, storage, ui);
                break;
            case PRIORITY:
                output = processPriority(input, tasks, storage, ui);
                break;
            default:
                output = ui.sorry();
                break;
            }
        } catch (Exception e) {
            return ui.sorry();
        }
        return output;
    }

    /**
     * Processes Find commands for parser.
     *
     * @param input The input command.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String listing all relevant tasks or error message.
     */
    public String processFind (String input, ArrayList<Task> tasks, Ui ui) {
        try {
            return ui.find(tasks, input.split(" ")[1]);
        } catch (Exception e) {
            String message = "Sorry! Please specify a key to find tasks!  >.<";
            String format = "find <key>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes ToDo commands for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating ToDo task has been added or error message.
     */
    public String processToDo (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            ToDo todo = new ToDo(input.substring(5));
            tasks.add(todo);
            storage.updateFile(tasks);
            return ui.add(todo, tasks.size());
        } catch (Exception e) {
            String message = "Sorry! ToDo tasks must follow this format!  >.<";
            String format = "todo <task>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes Deadline commands for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating Deadline task has been added or error message.
     */
    public String processDeadline (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            String deadlineSub = input.substring(9);
            String deadlineDesc = deadlineSub.split(" /by ")[0];
            String by = deadlineSub.split(" /by ")[1];
            Deadline deadline = new Deadline(deadlineDesc, by);
            tasks.add(deadline);
            storage.updateFile(tasks);
            return ui.add(deadline, tasks.size());
        } catch (Exception e) {
            String message = "Sorry! Deadline tasks must follow this format!  >.<";
            String format = "deadline <task> /by <YYYY-MM-DD>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes Event commands for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating Event task has been added or error message.
     */
    public String processEvent (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            String eventSub = input.substring(6);
            String eventDesc = eventSub.split(" /from ")[0];
            String time = eventSub.split(" /from ")[1];
            String from = time.split( " /to ")[0];
            String to = time.split( " /to ")[1];
            Event event = new Event(eventDesc, from, to);
            tasks.add(event);
            storage.updateFile(tasks);
            return ui.add(event, tasks.size());
        } catch (Exception e) {
            String message = "Sorry! Event tasks must follow this format!  >.<";
            String format = "event <task> /from <YYYY-MM-DD> /to <YYYY-MM-DD>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes Mark commands for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating task has been marked or error message.
     */
    public String processMark (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            Task mark = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
            mark.mark();
            storage.updateFile(tasks);
            return ui.mark(mark);
        } catch (Exception e) {
            String message = "Sorry! Please specify a task number to mark!  >.<";
            String format = "mark <task number>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes Unmark commands for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating task has been unmarked or error message.
     */
    public String processUnmark (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            Task unmark = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
            unmark.unmark();
            storage.updateFile(tasks);
            return ui.unmark(unmark);
        } catch (Exception e) {
            String message = "Sorry! Please specify a task number to unmark!  >.<";
            String format = "unmark <task number>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes Delete commands for parser.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating task has been deleted or error message.
     */
    public String processDelete (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            Task delete = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
            tasks.remove(Integer.parseInt(input.split(" ")[1]) - 1);
            storage.updateFile(tasks);
            return ui.delete(delete, tasks.size());
        } catch (Exception e) {
            String message = "Sorry! Please specify a task number to delete!  >.<";
            String format = "delete <task number>";
            return "\n" + message + "\n" + format + "\n";
        }
    }

    /**
     * Processes priority for a task.
     *
     * @param input The input command.
     * @param storage The storage path.
     * @param tasks The list of tasks.
     * @param ui The UI to obtain Dorito's output.
     * @return String indicating priority has been updated or error message.
     */
    public String processPriority (String input, ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            Task task = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
            Priority p = Priority.valueOf(input.split(" ")[2].toUpperCase());
            task.setPriority(p);
            storage.updateFile(tasks);
            return ui.priority(task, p);
        } catch (Exception e) {
            String message = "Sorry! Prioritizing tasks must follow this format!  >.<";
            String format = "priority <task number> <priority>";
            String values = "Priority can only be (high, medium, low, default)";
            return "\n" + message + "\n" + format + "\n" + values + "\n";
        }
    }
}
