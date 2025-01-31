package dorito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markTask() {
        Task task = new ToDo("read book");
        task.mark();
        assertEquals("[T][X] read book", task.toString());
    }

    @Test
    public void unmarkTask() {
        Task task = new ToDo("read book");
        task.mark();
        task.unmark();
        assertEquals("[T][ ] read book", task.toString());
    }

}

