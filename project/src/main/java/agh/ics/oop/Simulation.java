package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{

    private final List<Animal> animals;

    private SimulationStatistics statistics;
    private final WorldMap<WorldElement, Vector2d> worldMap;

    private SimulationSettings settings;
    public Simulation(int mapHeight, int mapWidth, int startPlants,int startAnimals, int animalStartEnergy, SimulationSettings settings){
        statistics = new SimulationStatistics(this);
        worldMap = new GlobeMap(mapWidth, mapHeight, startPlants);
        animals = new ArrayList<>();
        this.settings = settings;
    }

    public void run() {
    }

}
