package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void testEquals() {
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(2, 3);
        assertTrue(vector1.equals(vector2));
    }

    @Test
    public void testToString() {
        Vector2d vector = new Vector2d(2, 3);
        assertEquals("(2,3)", vector.toString());
    }

    @Test
    public void testPrecedes() {
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(4, 5);
        assertTrue(vector1.precedes(vector2));
        assertFalse(vector2.precedes(vector1));
    }

    @Test
    public void testFollows() {
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(4, 5);
        assertFalse(vector1.follows(vector2));
        assertTrue(vector2.follows(vector1));
    }

    @Test
    public void testUpperRight() {
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(4, 5);
        Vector2d result = vector1.upperRight(vector2);
        assertEquals(4, result.getX());
        assertEquals(5, result.getY());
    }

    @Test
    public void testLowerLeft() {
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(4, 5);
        Vector2d result = vector1.lowerLeft(vector2);
        assertEquals(2, result.getX());
        assertEquals(3, result.getY());
    }

    @Test
    public void testAdd() {
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(4, 5);
        Vector2d result = vector1.add(vector2);
        assertEquals(6, result.getX());
        assertEquals(8, result.getY());
    }

    @Test
    public void testSubtract() {
        Vector2d vector1 = new Vector2d(4, 5);
        Vector2d vector2 = new Vector2d(2, 3);
        Vector2d result = vector1.subtract(vector2);
        assertEquals(2, result.getX());
        assertEquals(2, result.getY());
    }

    @Test
    public void testOpposite() {
        Vector2d vector = new Vector2d(2, 3);
        Vector2d result = vector.opposite();
        assertEquals(-2, result.getX());
        assertEquals(-3, result.getY());
    }

}
