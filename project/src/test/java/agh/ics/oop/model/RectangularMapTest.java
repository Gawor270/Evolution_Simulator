package agh.ics.oop.model;

import agh.ics.oop.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveTo() throws PositionAlreadyOccupiedException {
        RectangularMap map = new RectangularMap(5,6);
        assertFalse(map.canMoveTo(new Vector2d(-1,0)));
        assertTrue(map.canMoveTo(new Vector2d(4,2)));
        assertFalse(map.canMoveTo(new Vector2d(7,1)));

        map.place(new Animal(new Vector2d(1,1)));
        assertFalse(map.canMoveTo(new Vector2d(1,1)));
    }

    @Test
    void place() throws PositionAlreadyOccupiedException {
        RectangularMap map = new RectangularMap(5,6);
        assertThrows(PositionAlreadyOccupiedException.class, () -> {
            map.place(new Animal(new Vector2d(-1,-1)));
        });

        assertDoesNotThrow(() -> {
            map.place(new Animal(new Vector2d(4,5)));
        });
        assertThrows(PositionAlreadyOccupiedException.class, () -> {
            map.place(new Animal(new Vector2d(4,5)));
        });
    }

    @Test
    void moveAndObjectAt() throws PositionAlreadyOccupiedException {
        RectangularMap map = new RectangularMap(2,8);

        map.place(new Animal(new Vector2d(1,1)));
        map.move(map.objectAt(new Vector2d(1,1)),MoveDirection.FORWARD);
        assertEquals(map.objectAt(new Vector2d(1,2)), new Animal(new Vector2d(1,2)));
        assertNull(map.objectAt(new Vector2d(1, 1)));

        map.place(new Animal(new Vector2d(1,7)));
        map.move(map.objectAt(new Vector2d(1,7)),MoveDirection.FORWARD);
        assertEquals(map.objectAt(new Vector2d(1,7)), new Animal(new Vector2d(1,7)));
        assertNull(map.objectAt(new Vector2d(0, 0)));
    }

    @Test
    void isOccupied() throws PositionAlreadyOccupiedException {
        RectangularMap map = new RectangularMap(11,3);

        map.place(new Animal(new Vector2d(8,1)));
        map.place(new Animal(new Vector2d(0,1)));
        assertTrue(map.isOccupied(new Vector2d(8,1)));
        assertTrue(map.isOccupied(new Vector2d(0,1)));
        assertFalse(map.isOccupied(new Vector2d(0,2)));
        assertFalse(map.isOccupied(new Vector2d(3,2)));
    }


}