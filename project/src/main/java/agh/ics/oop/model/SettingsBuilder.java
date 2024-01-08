package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.PlantGrowthVariant;


public class SettingsBuilder {

    private int mapHeight;
    private int mapWidth;
    private int startPlants;
    private int startAnimals;
    private int animalStartEnergy;
    private int plantEnergy;
    private int dailyPlants;
    private int fullEnergy;
    private int breedingEnergy;
    private int minMutations;
    private int maxMutations;
    private int genomeLength;
    private PlantGrowthVariant plantGrowthVariant;
    private AnimalMoveVariant animalMoveVariant;

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setStartPlants(int startPlants) {
        this.startPlants = startPlants;
    }

    public void setStartAnimals(int startAnimals) {
        this.startAnimals = startAnimals;
    }

    public void setAnimalStartEnergy(int animalStartEnergy) {
        this.animalStartEnergy = animalStartEnergy;
    }

    public void setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
    }

    public void setDailyPlants(int dailyPlants) {
        this.dailyPlants = dailyPlants;
    }

    public void setFullEnergy(int fullEnergy) {
        this.fullEnergy = fullEnergy;
    }

    public void setBreedingEnergy(int breedingEnergy) {
        this.breedingEnergy = breedingEnergy;
    }

    public void setMinMutations(int minMutations) {
        this.minMutations = minMutations;
    }

    public void setMaxMutations(int maxMutations) {
        this.maxMutations = maxMutations;
    }

    public void setGenomeLength(int genomeLength) {
        this.genomeLength = genomeLength;
    }

    public void setPlantGrowthVariant(PlantGrowthVariant plantGrowthVariant) {
        this.plantGrowthVariant = plantGrowthVariant;
    }

    public void setAnimalMoveVariant(AnimalMoveVariant animalMoveVariant) {
        this.animalMoveVariant = animalMoveVariant;
    }

    public Simulation build() {
        SimulationSettings settings = new SimulationSettings(mapHeight, mapWidth, startPlants, startAnimals, animalStartEnergy, plantEnergy, dailyPlants, fullEnergy, breedingEnergy, minMutations, maxMutations, genomeLength, plantGrowthVariant, animalMoveVariant);
        return new Simulation(settings);
    }
}
