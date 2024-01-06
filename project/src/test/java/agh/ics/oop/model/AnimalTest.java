package agh.ics.oop.model;

import agh.ics.oop.model.variants.ForestedEquator;
import agh.ics.oop.model.variants.FullPredestination;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    public void testReproduce(){
        Animal animal1 = new Animal(null, new Vector2d(5, 5), 5, 3, new FullPredestination());
        Animal animal2 = new Animal(null, new Vector2d(5, 5), 10, 3, new FullPredestination());
        Animal animal3 = animal1.reproduce(animal2, 3, 1, 10);
        assertEquals(2, animal1.getEnergy());
        assertEquals(7, animal2.getEnergy());
        assertEquals(6, animal3.getEnergy());
        assertEquals(3, animal3.getGenome().getGenome().size());
        assertEquals(3, animal3.getGenome().getGenome().size());
        assertEquals(3, animal3.getGenome().getGenome().size());
    }

    @Test
    public void testMove(){
        RectangularFloraMap map = new RectangularFloraMap(10, 10,0, new GlobeMap(), new ForestedEquator());

        ArrayList genome = new ArrayList(List.of(1,1,1));
        Animal animal1 = new Animal((List<Animal>) null, new Vector2d(10, 10), 5, new Genome(genome), new FullPredestination());

        assertEquals(new Vector2d(10, 10), animal1.getPosition());
        animal1.move(map.mapVariant, new Boundary(new Vector2d(0, 0), new Vector2d(10, 10)));
        assertEquals(new Vector2d(10, 10), animal1.getPosition());
        animal1.move(map.mapVariant, new Boundary(new Vector2d(0, 0), new Vector2d(10, 10)));
        assertEquals(new Vector2d(9, 10), animal1.getPosition());


    }
}
