package dorito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void dateFormat() {
        Task task = new Event("read book", "2025-01-31", "2025-02-01");
        assertEquals("[E][ ][ ] read book (from: Jan 31 2025 to: Feb 01 2025)", task.toString());
    }

}
