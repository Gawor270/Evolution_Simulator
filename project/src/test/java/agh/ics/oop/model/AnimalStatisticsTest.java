package agh.ics.oop.model;
import agh.ics.oop.model.variants.FullPredestination;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalStatisticsTest {

    @Test
    public void descendantsTest() {
        Animal animal1 = new Animal(null,new Vector2d(1,1),9,8, new FullPredestination()); // Initialize your Animal object here
        Animal animal2 = new Animal(null,new Vector2d(1,1),9,8, new FullPredestination()); // Initialize your Animal object here
        Animal animal3 = new Animal(List.of(animal1,animal2),new Vector2d(1,1),9,8, new FullPredestination());
        Animal animal4 = new Animal(null,new Vector2d(1,1),9,8, new FullPredestination()); // Initialize your Animal object here
        Animal animal5 = new Animal(List.of(animal3,animal4), new Vector2d(1,1),9,8, new FullPredestination());
        assertEquals(0,animal5.getStatistics().getChildrenCounter());
        assertEquals(2, animal1.getStatistics().getDescendantsCounter());
        assertEquals(1, animal1.getStatistics().getChildrenCounter());
    }

    @Test
    public void testGetOlderIncreasesAge() {
        Animal animal = new Animal(null, new Vector2d(1,1), 9,8, new FullPredestination());
        assertEquals(0, animal.getStatistics().getAge());
        animal.getStatistics().getOlder();
        assertEquals(1, animal.getStatistics().getAge());
    }

}
