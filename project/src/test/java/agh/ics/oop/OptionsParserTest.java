import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import agh.ics.oop.OptionsParser;

import java.util.List;


public class OptionsParserTest {

    @Test
    public void testConvert() {
        String[] args = {"f", "b", "r", "l"};
        List<MoveDirection> expected = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT);
        List<MoveDirection> result = OptionsParser.parse(args);
        assertIterableEquals(expected, result);
    }

    @Test
    public void testConvertWithInvalidDirections() {
        String[] args = {"f", "x", "b", "r", "l", "z"};
        List<MoveDirection> expected = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT);
        assertThrows(IllegalArgumentException.class, () -> {
            OptionsParser.parse(args);
        });
    }

    @Test
    public void testConvertWithNoValidDirections() {
        String[] args = {"x", "y", "z"};
        List<MoveDirection> expected = List.of();
        assertThrows(IllegalArgumentException.class, () -> {
            OptionsParser.parse(args);
        });
    }
}