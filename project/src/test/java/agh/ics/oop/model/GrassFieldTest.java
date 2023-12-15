package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    @Test
    void placeGrassTest() {
        for(int n = -100; n<=100; n++) {

            GrassField map = new GrassField(n);
            int countGrass = 0;
            for (int i = 0; i <= 10*(int)Math.ceil(Math.sqrt(n)); i++) {
                for (int j = 0; j <= 10*(int)Math.ceil(Math.sqrt(n)); j++) {
                    if (map.objectAt(new Vector2d(i, j)) instanceof Grass) countGrass++;
                }
            }

            assertEquals(countGrass, Math.max(0,n));
        }
    }

    @Test
    void placeTest() throws PositionAlreadyOccupiedException {
        for(int n = -100; n<=100; n++) {
            GrassField map = new GrassField(n);
            map.place(new Animal(new Vector2d(n*(int)(Math.ceil(Math.sqrt(n)))+4,-n)));
            int finalN = n;
            assertThrows(PositionAlreadyOccupiedException.class, () -> {
                map.place(new Animal(new Vector2d(finalN *(int)(Math.ceil(Math.sqrt(finalN)))+4,-finalN)));
            });

        }
    }

    @Test
    void moveTest() throws PositionAlreadyOccupiedException {
        GrassField map = new GrassField(7);
        Animal animal1 = new Animal(new Vector2d(-1,-5));

        map.place(animal1);
        assertTrue(map.objectAt(new Vector2d(-1,-5))  instanceof Animal);

        map.move(animal1, MoveDirection.FORWARD);
        assertTrue(map.objectAt(new Vector2d(-1,-4))  instanceof Animal);

        map.move(animal1,MoveDirection.LEFT);
        map.move(animal1, MoveDirection.BACKWARD);
        assertTrue(map.objectAt(new Vector2d(0,-4))  instanceof Animal);

        map.move(animal1,MoveDirection.BACKWARD);
        assertTrue(map.objectAt(new Vector2d(1,-4))  instanceof Animal);

        map.move(animal1,MoveDirection.BACKWARD);
        assertTrue(map.objectAt(new Vector2d(2,-4))  instanceof Animal);

        map.move(animal1,MoveDirection.RIGHT);
        map.move(animal1,MoveDirection.FORWARD);
        assertTrue(map.objectAt(new Vector2d(2,-3))  instanceof Animal);
    }

    @Test
    void ObjectAtTest() {
        GrassField map = new GrassField(15);
        LinkedList<Vector2d> grassPos = new LinkedList<>();
        for (int i = 0; i <= 10*(int)Math.ceil(Math.sqrt(15)); i++) {
            for (int j = 0; j <= 10*(int)Math.ceil(Math.sqrt(15)); j++) {
                if (map.objectAt(new Vector2d(i, j)) instanceof Grass)grassPos.push(new Vector2d(i,j));
            }
        }
        assertNull(map.objectAt(new Vector2d(-1,-10)));

        for(Vector2d v : grassPos) {
            assertTrue(map.objectAt(v) instanceof Grass);
        }


    }

    @Test
    void isOccupiedTest() {
        GrassField map = new GrassField(21);
        LinkedList<Vector2d> grassPos = new LinkedList<>();
        for (int i = 0; i <= 10*(int)Math.ceil(Math.sqrt(15)); i++) {
            for (int j = 0; j <= 10*(int)Math.ceil(Math.sqrt(15)); j++) {
                if (map.objectAt(new Vector2d(i, j)) instanceof Grass)grassPos.push(new Vector2d(i,j));
            }
        }
        assertNull(map.objectAt(new Vector2d(-1,-10)));

        for(Vector2d v : grassPos) {
            assertTrue(map.isOccupied(v));
        }
    }



}
