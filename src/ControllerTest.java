import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @Test
    public void testValiderJaNejInput() {
        Controller controller = new Controller(new Database());
        assertTrue(controller.validerJaNejInput("ja"));
        assertTrue(controller.validerJaNejInput("nej"));
        assertFalse(controller.validerJaNejInput("måske"));
        assertFalse(controller.validerJaNejInput(""));
    }
}