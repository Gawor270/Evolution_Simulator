package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenomeTest {

    @Test
    public void testSetGenome() {
        Genome genome = new Genome(10);
        assertEquals(10, genome.getGenome().size());
    }

    @Test
    public void testMutate() {
        Genome genom = new Genome(10);
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
        Genome genom = new Genome(10);
        // Save the current index before calling nextGen
        int originalIndex = genom.getCurrentIndex();

        // Call nextGen
        genom.nextGen();

        // Check if the current index has changed
        assertNotEquals(originalIndex, genom.getCurrentIndex());
    }
}
