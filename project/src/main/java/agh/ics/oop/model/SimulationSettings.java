package agh.ics.oop.model;

public record SimulationSettings(int plantEnergy, int dailyPlants,
                                 int fullEnergy, int breedingEnergy,
                                 int minMutations, int maxMutations, int genomeLength){
}
