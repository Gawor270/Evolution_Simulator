import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {


    @Test
    public void testNext() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.moveBy(1));
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.moveBy(1));
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.moveBy(1));
        assertEquals(MapDirection.NORTH, MapDirection.WEST.moveBy(1));
    }
}
