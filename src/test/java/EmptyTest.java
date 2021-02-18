import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTest {
    @Test
    public void go() {
        var x = 2;
        var x6 = 6 * x;
        assertEquals(6, x6 / x);
    }
}
