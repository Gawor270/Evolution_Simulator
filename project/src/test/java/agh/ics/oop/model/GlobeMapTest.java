package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GlobeMapTest {

    @Test
    public void testPlace() {
        GlobeMap map = new GlobeMap(10, 10,0);
        Vector2d position = new Vector2d(5, 5);
        Animal animal1 = new Animal(null, position, 0, 3);
        map.place(animal1);
        assertTrue(map.isOccupied(position));
        Animal animal2 = new Animal(null, position, 0, 3);
        map.place(animal2);
        assertEquals(2, map.getAnimals().get(position).size());
        Animal animal3 = new Animal(null, position, 0, 3);
        map.place(animal3);
        assertEquals(3, map.getAnimals().get(position).size());
    }

    @Test
    public void testRemove() {
        GlobeMap map = new GlobeMap(10, 10, 0);
        Vector2d position = new Vector2d(5, 5);
        Animal animal1 = new Animal(null, position, 0, 3);
        map.place(animal1);
        assertTrue(map.isOccupied(position));
        map.remove(animal1);
        assertFalse(map.isOccupied(position));
    }

    @Test
    public void testCanMoveTo(){
        GlobeMap map = new GlobeMap(10, 10, 0);
        Vector2d position = new Vector2d(5, 5);
        assertTrue(map.canMoveTo(position));
        position = new Vector2d(5, 11);
        assertFalse(map.canMoveTo(position));
        position = new Vector2d(5, -1);
        assertFalse(map.canMoveTo(position));
        position = new Vector2d(11, 9);
        assertTrue(map.canMoveTo(position));
    }

    @Test
    public void testObjectAt(){
        GlobeMap map = new GlobeMap(10, 10, 0);
        Vector2d position = new Vector2d(5, 5);
        Animal animal1 = new Animal(null, position, 5, 3);
        map.place(animal1);
        assertEquals(animal1, map.objectAt(position));
        Animal animal2 = new Animal(null, position, 10, 3);
        map.place(new Plant(position));
        map.place(animal2);
        assertEquals(animal2, map.objectAt(position));
        map.getAnimals().get(position).first().eatPlant(5);
        Animal animal3 = new Animal(null, position, 12, 3);
        map.place(animal3);
        assertEquals(animal2, map.objectAt(position));
        position = new Vector2d(5, 11);
        assertNull(map.objectAt(position));
    }



}
