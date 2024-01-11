package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.List;

public class SimulationStatistics {

    private Simulation simulation;

    private int animalsCount;
    private int plantsCount;
    private int freeSpaceCount;
    List<Genome> mostCommonGenomes;

    private float totalEnergy;
    private float avgEnergy;
    private float avgLifespan;
    private float totalAge;
    private float totalDeaths;
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

    public void updateAvgLifespan(Animal animal){
        totalAge += animal.getStatistics().getAge();
        totalDeaths++;
        avgLifespan = totalAge / totalDeaths;

    }

    public void increasePlantsCount(int x){
        plantsCount+=x;
    }

    public void decreasePlantsCount(){
        plantsCount--;
    }

    public int getAnimalsCount() {
        return animalsCount;
    }

    public int getPlantsCount() {
        return plantsCount;
    }
    public void setFreeSpaceCount(int x){
        freeSpaceCount = x;
    }

    public void updateTotalEnergy(float energy){
        totalEnergy += energy;
    }

    public float getAvgEnergy(){
        return totalEnergy / animalsCount;
    }

    public int getFreeSpaceCount() {
        return freeSpaceCount;
    }

    public float getAvgLifespan(){
        return avgLifespan;
    }

    public void updateAvgChildrenCount(float childrenCount){
        avgChildrenCount = childrenCount;
    }











}
