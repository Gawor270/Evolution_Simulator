package agh.ics.oop.model;

import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;
import agh.ics.oop.model.variantsInterfaces.PlantGrowthVariant;

public record SimulationSettings(int mapHeight, int mapWidth,
                                 int startPlants, int startAnimals,
                                 int animalStartEnergy, int plantEnergy,
                                 int dailyPlants, int fullEnergy,
                                 int breedingEnergy, int minMutations,
                                 int maxMutations, int genomeLength,
                                 PlantGrowthVariant plantGrowthVariant, AnimalMoveVariant animalMoveVariant
                                 ){
}
