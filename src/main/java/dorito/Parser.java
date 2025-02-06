package dorito;

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
                ToDo todo = new ToDo(input.substring(5));
                tasks.add(todo);
                storage.updateFile(tasks);
                output = ui.add(todo, tasks.size());
                break;
            case DEADLINE:
                String deadlineSub = input.substring(9);
                String deadlineDesc = deadlineSub.split(" /by ")[0];
                String by = deadlineSub.split(" /by ")[1];
                Deadline deadline = new Deadline(deadlineDesc, by);
                tasks.add(deadline);
                storage.updateFile(tasks);
                output = ui.add(deadline, tasks.size());
                break;
            case EVENT:
                String eventSub = input.substring(6);
                String eventDesc = eventSub.split(" /from ")[0];
                String time = eventSub.split(" /from ")[1];
                String from = time.split( " /to ")[0];
                String to = time.split( " /to ")[1];
                Event event = new Event(eventDesc, from, to);
                tasks.add(event);
                storage.updateFile(tasks);
                output = ui.add(event, tasks.size());
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
            }
        } catch (Exception e) {
            return "\nSorry! I don't understand what you mean!  >.<\n";
        }
        return output;
    }
}
