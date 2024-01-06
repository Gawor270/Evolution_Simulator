package agh.ics.oop.model;

import agh.ics.oop.model.variants.ForestedEquator;
import agh.ics.oop.model.variants.FullPredestination;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularFloraMapTest {

    @Test
    public void testPlace() {
        RectangularFloraMap map = new RectangularFloraMap(10, 10,0,
                new GlobeMap(), new ForestedEquator());
        Vector2d position = new Vector2d(5, 5);
        Animal animal1 = new Animal(null, position, 0, 3, new FullPredestination());
        map.place(animal1);
        assertTrue(map.isOccupied(position));
        Animal animal2 = new Animal(null, position, 0, 3, new FullPredestination());
        map.place(animal2);
        assertEquals(2, map.getAnimals().get(position).size());
        Animal animal3 = new Animal(null, position, 0, 3, new FullPredestination());
        map.place(animal3);
        assertEquals(3, map.getAnimals().get(position).size());
    }

    @Test
    public void testRemove() {
        RectangularFloraMap map = new RectangularFloraMap(10, 10, 0,
                new GlobeMap(), new ForestedEquator());
        Vector2d position = new Vector2d(5, 5);
        Animal animal1 = new Animal(null, position, 0, 3, new FullPredestination());
        map.place(animal1);
        assertTrue(map.isOccupied(position));
        map.remove(animal1);
        assertFalse(map.isOccupied(position));
    }


    @Test
    public void testObjectAt(){
        RectangularFloraMap map = new RectangularFloraMap(10, 10, 0,
                new GlobeMap(), new ForestedEquator());
        Vector2d position = new Vector2d(5, 5);
        Animal animal1 = new Animal(null, position, 5, 3, new FullPredestination());
        map.place(animal1);
        assertEquals(animal1, map.objectAt(position));
        Animal animal2 = new Animal(null, position, 10, 3, new FullPredestination());
        map.place(new Plant(position));
        map.place(animal2);
        assertEquals(animal2, map.objectAt(position));
        map.getAnimals().get(position).first().eatPlant(5);
        Animal animal3 = new Animal(null, position, 12, 3, new FullPredestination());
        map.place(animal3);
        assertEquals(animal2, map.objectAt(position));
        position = new Vector2d(5, 11);
        assertNull(map.objectAt(position));
    }



}
