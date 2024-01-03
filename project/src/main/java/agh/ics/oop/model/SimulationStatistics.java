package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.List;

public class SimulationStatistics {

    private Simulation simulation;

    private int animalsCount;
    private int plantsCount;
    private int freeSpaceCount;
    List<Genome> mostCommonGenomes;
    private float avgEnergy;
    private float avgLifespan;
    private float avgChildrenCount;

    public SimulationStatistics(Simulation simulation) {
        this.simulation = simulation;
    }

    public void increaseAnimalsCount(){
        animalsCount++;
    }

    public void decreaseAnimalsCount(){
        animalsCount--;
    }

    public void increasePlantsCount(){
        plantsCount++;
    }

    public void decreasePlantsCount(){
        plantsCount--;
    }

    public int getAnimalsCount() {
        return animalsCount;
    }










}
