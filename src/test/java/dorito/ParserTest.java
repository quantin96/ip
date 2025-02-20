package dorito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class ParserTest {

    @Test
    public void parseByeCommand() {
        String input = "bye";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nBye. Remember to stay hydrated!  >.<",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseListCommand() {
        String input = "list";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nHere are the tasks in your list:  0.0\n1.[T][ ][ ] read book\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseFindCommand() {
        String input = "find read";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new ToDo("write book"));
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nHere are the relevant tasks:  0.0\n1.[T][ ][ ] read book\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseFindError() {
        String input = "find";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Please specify a key to find tasks!  >.<\nfind <key>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseToDoCommand() {
        String input = "todo read book";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nGot it. I've added this task:  >.<\n  [T][ ][ ] read book"
                + "\nNow you have 1 tasks in the list.  >.<\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseToDoError() {
        String input = "todo";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! ToDo tasks must follow this format!  >.<\ntodo <task>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseDeadlineCommand() {
        String input = "deadline read book /by 2025-01-31";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nGot it. I've added this task:  >.<\n"
                        + "  [D][ ][ ] read book (by: Jan 31 2025)"
                        + "\nNow you have 1 tasks in the list.  >.<\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseDeadlineError() {
        String input = "deadline";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Deadline tasks must follow this format!  >.<\n"
                        + "deadline <task> /by <YYYY-MM-DD>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseEventCommand() {
        String input = "event read book /from 2025-01-31 /to 2025-02-01";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nGot it. I've added this task:  >.<\n"
                        + "  [E][ ][ ] read book (from: Jan 31 2025 to: Feb 01 2025)"
                        + "\nNow you have 1 tasks in the list.  >.<\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseEventError() {
        String input = "event";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Event tasks must follow this format!  >.<\n"
                        + "event <task> /from <YYYY-MM-DD> /to <YYYY-MM-DD>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseMarkCommand() {
        String input = "mark 1";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nNice! I've marked this task as done:  >.<\n  [T][X][ ] read book\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseMarkError() {
        String input = "mark";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Please specify a task number to mark!  >.<\n"
                        + "mark <task number>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseUnmarkCommand() {
        String input = "unmark 1";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nOK! I've marked this task as not done:  0.0\n  [T][ ][ ] read book\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseUnmarkError() {
        String input = "unmark";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Please specify a task number to unmark!  >.<\n"
                        + "unmark <task number>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseDeleteCommand() {
        String input = "delete 1";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nOK! I've removed this task:  0.0\n  [T][ ][ ] read book"
                + "\nNow you have 0 tasks in the list.  >.<\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseDeleteError() {
        String input = "delete";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Please specify a task number to delete!  >.<\n"
                        + "delete <task number>\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parsePriorityCommand() {
        String input = "priority 1 high";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nOK! I've marked this task as HIGH priority 0.0\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parsePriorityError() {
        String input = "priority";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! Prioritizing tasks must follow this format!  >.<\n"
                        + "priority <task number> <priority>\n"
                        + "Priority can only be (high, medium, low, default)\n",
                parser.parse(input, storage, tasks, ui));
    }

    @Test
    public void parseErrorCommand() {
        String input = "dorito";
        Storage storage = new Storage("./data/doritotest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        assertEquals("\nSorry! I don't understand what you mean!  >.<\n",
                parser.parse(input, storage, tasks, ui));
    }
}
