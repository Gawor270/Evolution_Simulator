package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;

public class GenomTest {

    @Test
    public void testSetGenom() {
        Genom genom = new Genom();
        int n = 10;
        genom.setGenom(n);

        assertEquals(n, genom.getGenom().size());
    }

    @Test
    public void testMutate() {
        Genom genom = new Genom();
        int n = 10;
        genom.setGenom(n);

        // Perform mutation
        int indexToMutate = 3;
        int originalValue = genom.getGenom().get(indexToMutate);
        genom.mutate(indexToMutate);

        // Check if mutation occurred
        assertNotEquals(originalValue, (int) genom.getGenom().get(indexToMutate));
    }

    @Test
    public void testCross() {
        Genom parentA = new Genom();
        parentA.setGenom(8);

        Genom parentB = new Genom();
        parentB.setGenom(8);

        int energyA = 50;
        int energyB = 30;

        Genom childGenom = new Genom();
        childGenom.setGenom(8);
        childGenom.setGenom(8, childGenom.cross(parentA, parentB, energyA, energyB));

        // Check if the child's genome has the correct size
        assertEquals(8, childGenom.getGenom().size());

        // Check if the child's genome contains unique values
        assertEquals(8, childGenom.getGenom().stream().distinct().count());
    }

    @Test
    public void testNextGen() {
        Genom genom = new Genom();
        genom.setGenom(10);

        // Save the current index before calling nextGen
        int originalIndex = genom.getCurrentIndex();

        // Call nextGen
        genom.nextGen();

        // Check if the current index has changed
        assertNotEquals(originalIndex, genom.getCurrentIndex());
    }
}
