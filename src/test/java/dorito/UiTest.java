package dorito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import dorito.Task.Priority;

public class UiTest {

    @Test
    public void startMessage() {
        Ui ui = new Ui();
        String logo = "_________\n"
                + "\\       /\n"
                + " \\     / \n"
                + "  \\   /  \n"
                + "   \\ /   \n";
        assertEquals(logo + "\nHello! I'm Dorito  >.<\n"
                + "What can I do for you?  0.0\n", ui.start());
    }

    @Test
    public void byeMessage() {
        Ui ui = new Ui();
        assertEquals("\nBye. Remember to stay hydrated!  >.<", ui.bye());
    }

    @Test
    public void listMessage() {
        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        assertEquals("\nHere are the tasks in your list:  0.0\n1.[T][ ][ ] read book\n",
                ui.list(tasks));
    }

    @Test
    public void findMessage() {
        Ui ui = new Ui();
        ArrayList<Task>tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new ToDo("write book"));
        String key = "read";
        assertEquals("\nHere are the relevant tasks:  0.0\n1.[T][ ][ ] read book\n",
                ui.find(tasks, key));
    }

    @Test
    public void addMessage() {
        Ui ui = new Ui();
        ToDo task = new ToDo("read book");
        assertEquals("\nGot it. I've added this task:  >.<\n  [T][ ][ ] read book"
                + "\nNow you have 1 tasks in the list.  >.<\n", ui.add(task, 1));
    }

    @Test
    public void markMessage() {
        Ui ui = new Ui();
        ToDo task = new ToDo("read book");
        task.mark();
        assertEquals("\nNice! I've marked this task as done:  >.<\n  [T][X][ ] read book\n",
                ui.mark(task));
    }

    @Test
    public void unmarkMessage() {
        Ui ui = new Ui();
        ToDo task = new ToDo("read book");
        assertEquals("\nOK! I've marked this task as not done:  0.0\n  [T][ ][ ] read book\n",
                ui.unmark(task));
    }

    @Test
    public void deleteMessage() {
        Ui ui = new Ui();
        ToDo task = new ToDo("read book");
        assertEquals("\nOK! I've removed this task:  0.0\n  [T][ ][ ] read book"
                + "\nNow you have 0 tasks in the list.  >.<\n", ui.delete(task, 0));
    }

    @Test
    public void priorityMessage() {
        Ui ui = new Ui();
        ToDo task = new ToDo("read book");
        assertEquals("\nOK! I've marked this task as HIGH priority 0.0\n",
                ui.priority(task, Priority.HIGH));
    }

    @Test
    public void sorryMessage() {
        Ui ui = new Ui();
        assertEquals("\nSorry! I don't understand what you mean!  >.<\n", ui.sorry());
    }
}
