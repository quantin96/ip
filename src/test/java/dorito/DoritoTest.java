package dorito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

public class DoritoTest {

    @Test
    public void emptyListSize() throws IOException {
        Dorito dorito = new Dorito("./data/doritoempty.txt");
        assertEquals(0, dorito.size());
    }
}
