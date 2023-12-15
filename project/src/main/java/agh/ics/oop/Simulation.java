package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{

    private final List<MoveDirection>  moves;
    private final List<Animal> animals;

    private Statistics statistics;
    private final WorldMap<WorldElement, Vector2d> worldMap;
    public Simulation(int mapHeight, int mapWidth, int startPlants, int plantEnergy,
                      int dailyPlants, int startAnimals, int animalStartEnergy,
                      int fullEnergy, int breedingEnergy, int minMutations, int maxMutations, int genomeLength){
        statistics = new Statistics(this);
    }

    public void run() {
    }

}
