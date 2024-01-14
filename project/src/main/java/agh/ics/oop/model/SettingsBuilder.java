package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.PlantGrowthVariant;
import agh.ics.oop.presenter.SimulationPresenter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.awt.event.ActionEvent;
import java.io.File;


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

    private String plantGrowthVariantName;

    private String animalMoveVariantName;

    private boolean saveToCSV;

    @JsonIgnore
    private PlantGrowthVariant plantGrowthVariant;

    @JsonIgnore
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

    @JsonIgnore
    public void setPlantGrowthVariant(PlantGrowthVariant plantGrowthVariant) {
        this.plantGrowthVariant = plantGrowthVariant;
    }

    @JsonIgnore
    public void setAnimalMoveVariant(AnimalMoveVariant animalMoveVariant) {
        this.animalMoveVariant = animalMoveVariant;
    }

    public void setAnimalMoveVariantName(String animalMoveVariantName) {
        this.animalMoveVariantName = animalMoveVariantName;
    }

    public void setPlantGrowthVariantName(String plantGrowthVariantName) {
        this.plantGrowthVariantName = plantGrowthVariantName;
    }

    public void setSaveToCSV(boolean saveToCSV) {
        this.saveToCSV = saveToCSV;
    }

    public int getAnimalStartEnergy() {
        return animalStartEnergy;
    }

    public int getBreedingEnergy() {
        return breedingEnergy;
    }

    public int getDailyPlants() {
        return dailyPlants;
    }

    public int getFullEnergy() {
        return fullEnergy;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMaxMutations() {
        return maxMutations;
    }

    public int getMinMutations() {
        return minMutations;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public int getStartAnimals() {
        return startAnimals;
    }

    public int getStartPlants() {
        return startPlants;
    }

    public PlantGrowthVariant getPlantGrowthVariant() {
        return plantGrowthVariant;
    }

    public String getAnimalMoveVariantName() {
        return animalMoveVariantName;
    }

    public String getPlantGrowthVariantName() {
        return plantGrowthVariantName;
    }

    public boolean getSaveToCSV() {
        return saveToCSV;
    }

    @JsonIgnore
    public Simulation build(SimulationPresenter presenter) {
        SimulationSettings settings = new SimulationSettings(mapHeight, mapWidth,
                startPlants, startAnimals, animalStartEnergy, plantEnergy, dailyPlants,
                fullEnergy, breedingEnergy, minMutations, maxMutations, genomeLength,
                plantGrowthVariant, animalMoveVariant, saveToCSV);
        return new Simulation(settings);
    }

    @JsonIgnore
    public void saveToJson(String name){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("./project/src/main/resources/saves",name), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public static SettingsBuilder loadFromJson(String name){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("./project/src/main/resources/saves",  name), SettingsBuilder.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
