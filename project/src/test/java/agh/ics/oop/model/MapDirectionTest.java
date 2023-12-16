package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {


    @Test
    public void testNext() {
        assertEquals(MapDirection.NORTH_EAST, MapDirection.NORTH.moveBy(1));
        assertEquals(MapDirection.SOUTH_EAST, MapDirection.EAST.moveBy(1));
        assertEquals(MapDirection.SOUTH_WEST, MapDirection.SOUTH.moveBy(1));
        assertEquals(MapDirection.NORTH_WEST, MapDirection.WEST.moveBy(1));
    }
}
