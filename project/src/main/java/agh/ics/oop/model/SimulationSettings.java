package agh.ics.oop.model;

public record SimulationSettings(int startPlants, int startAnimals,
                                 int animalStartEnergy, int plantEnergy,
                                 int dailyPlants, int fullEnergy,
                                 int breedingEnergy, int minMutations,
                                 int maxMutations, int genomeLength){
}
