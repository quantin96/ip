package dorito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void dateFormat() {
        Task task = new Deadline("read book", "2025-01-31");
        assertEquals("[D][ ][ ] read book (by: Jan 31 2025)", task.toString());
    }

}
