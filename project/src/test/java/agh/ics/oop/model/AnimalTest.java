package agh.ics.oop.model;

import agh.ics.oop.World;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.World.upperBound;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    WorldMap map = new GlobeMap(upperBound.getX(), upperBound.getY());
    @Test
    public void testOrientationRight() {
        Animal animal = new Animal();
        for(MapDirection mov : MapDirection.values()){
            assertEquals(mov, animal.getOrientation());
            animal.move(MoveDirection.RIGHT,map);
        }
    }

    @Test
    public void testOrientationLeft() {
        Animal animal = new Animal();
        animal.move(MoveDirection.LEFT,map);
        for(int i = MapDirection.values().length-1; i>=0; i--){
            assertEquals(MapDirection.values()[i], animal.getOrientation());
            animal.move(MoveDirection.LEFT,map);
        }
    }

    @Test
    public void testMoving() {
        Animal animal = new Animal();
        assertTrue(animal.isAt(new Vector2d(2,2)));
        animal.move(MoveDirection.FORWARD,map);
        assertTrue(animal.isAt(new Vector2d(2,3)));
        animal.move(MoveDirection.BACKWARD,map);
        assertTrue(animal.isAt(new Vector2d(2,2)));
        animal.move(MoveDirection.BACKWARD,map);
        assertTrue(animal.isAt(new Vector2d(2,1)));
    }

    @Test
    public void testBounds() {
        Animal animal = new Animal(World.lowerBound);
        animal.move(MoveDirection.BACKWARD,map);
        assertTrue(animal.isAt(World.lowerBound));
        animal = new Animal(upperBound);
        animal.move(MoveDirection.FORWARD,map);
        assertTrue(animal.isAt(upperBound));
    }



}
