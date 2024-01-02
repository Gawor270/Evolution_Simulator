package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    public void testReproduce(){
        Animal animal1 = new Animal(null, new Vector2d(5, 5), 5, 3);
        Animal animal2 = new Animal(null, new Vector2d(5, 5), 10, 3);
        Animal animal3 = animal1.reproduce(animal2, 3);
        assertEquals(2, animal1.getEnergy());
        assertEquals(7, animal2.getEnergy());
        assertEquals(6, animal3.getEnergy());
        assertEquals(3, animal3.getGenome().getGenome().size());
        assertEquals(3, animal3.getGenome().getGenome().size());
        assertEquals(3, animal3.getGenome().getGenome().size());
    }

    @Test
    public void testMove(){
        GlobeMap map = new GlobeMap(10, 10);
        ArrayList genome = new ArrayList(List.of(1,1,1));
        Animal animal1 = new Animal((List<Animal>) null, new Vector2d(10, 10), 5, new Genome(genome) );

        assertEquals(new Vector2d(10, 10), animal1.getPosition());
        animal1.move(map);
        assertEquals(new Vector2d(10, 10), animal1.getPosition());
        animal1.move(map);
        assertEquals(new Vector2d(0, 10), animal1.getPosition());


    }
}
