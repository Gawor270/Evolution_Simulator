package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.variants.BitOfMadness;
import agh.ics.oop.model.variants.PoisonousPlants;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import javafx.application.Application;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World {

    public static void main(String[] args){
        SettingsBuilder builder = new SettingsBuilder();
        builder.setMapHeight(10);
        builder.setMapWidth(10);
        builder.setStartPlants(10);
        builder.setStartAnimals(10);
        builder.setAnimalStartEnergy(10);
        builder.setPlantEnergy(10);
        builder.setDailyPlants(10);
        builder.setFullEnergy(10);
        builder.setBreedingEnergy(10);
        builder.setMinMutations(1);
        builder.setMaxMutations(10);
        builder.setGenomeLength(32);
        builder.setPlantGrowthVariant(new PoisonousPlants());
        builder.setAnimalMoveVariant(new BitOfMadness());
        Simulation simulation = builder.build();
        simulation.run();
    }
}