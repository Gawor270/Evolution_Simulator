package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenomeTest {

    @Test
    public void testSetGenom() {
        int n = 10;
        Genome genome = new Genome(n);

        assertEquals(n, genome.getGenome().size());
    }

    @Test
    public void testMutate() {
        int n = 10;
        Genome genom = new Genome(n);

        // Perform mutation
        int indexToMutate = 3;
        int originalValue = genom.getGenome().get(indexToMutate);
        genom.mutate(indexToMutate);

        // Check if mutation occurred
        assertNotEquals(originalValue, (int) genom.getGenome().get(indexToMutate));
    }

    @Test
    public void testCross() {
        Genome parentA = new Genome(8);

        Genome parentB = new Genome(8);

        int energyA = 50;
        int energyB = 30;

        Genome childGenom = parentA.cross(parentB, energyA, energyB);

        // Check if the child's genome has the correct size
        assertEquals(8, childGenom.getGenome().size());

    }

    @Test
    public void testNextGen() {
        Genome genome = new Genome(10);

        // Save the current index before calling nextGen
        int originalIndex = genome.getCurrentIndex();

        // Call nextGen
        genome.nextGen();

        // Check if the current index has changed
        assertNotEquals(originalIndex, genome.getCurrentIndex());
    }
}
