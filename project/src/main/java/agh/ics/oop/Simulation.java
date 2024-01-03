package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.RandomFreePositionGenerator;

import java.util.*;

public class Simulation implements Runnable{


    private List<Animal> animals;
    private SimulationStatistics statistics;

    private final WorldMap worldMap;
    private SimulationSettings settings;
    public Simulation(int mapHeight, int mapWidth, SimulationSettings settings){
        statistics = new SimulationStatistics(this);
        worldMap = new GlobeMap(mapWidth, mapHeight, settings.startPlants());
        ((GlobeMap)worldMap).registerObserver(new ConsoleMapDisplay());
        animals = new ArrayList<>();
        this.settings = settings;
        spawnAnimals(settings.startAnimals(), settings.animalStartEnergy(), mapWidth, mapHeight);
    }

    private void spawnAnimals(int startAnimals, int animalStartEnergy,int w,int h){
        Random random = new Random();
        for(int i = 0; i < startAnimals; i++){
            Vector2d position = new Vector2d(random.nextInt(w), random.nextInt(h));
            Animal animal = new Animal(null, position, animalStartEnergy, settings.genomeLength());
            animals.add(animal);
            worldMap.place(animal);
            statistics.increaseAnimalsCount();
        }
    }



    private void consumeAndBreed(){
        for(Map.Entry<Vector2d, TreeSet<Animal>> entry : ((GlobeMap)worldMap).getAnimals().entrySet()){
            Vector2d position = entry.getKey();
            TreeSet<Animal> animals = entry.getValue();
            if(animals != null){
                Animal strongest = animals.first();
                if(((GlobeMap)worldMap).getPlants().get(position) != null) {
                    strongest.eatPlant(settings.plantEnergy());
                    worldMap.remove(((GlobeMap)worldMap).getPlants().get(position));
                }
                if(animals.size() > 1  && animals.first().getEnergy() >= settings.fullEnergy()){
                    Animal secondStrongest = animals.stream().skip(1).findFirst().get();
                    if(secondStrongest.getEnergy() >= settings.fullEnergy()){
                        Animal child = strongest.reproduce(secondStrongest,settings.breedingEnergy(), settings.minMutations(), settings.maxMutations());
                        worldMap.place(child);
                        statistics.increaseAnimalsCount();
                    }
                }
            }
        }
    }

    private void removeDeadAnimals(){
        List<Animal> toRemove = new ArrayList<>();
        for(Animal animal : animals){
            if(animal.getEnergy() <= 0){
                statistics.decreaseAnimalsCount();
                worldMap.remove(animal);
                toRemove.add(animal);
            }
        }
        animals.removeAll(toRemove);
    }

    private void moveAnimals(){
        for(Animal animal : animals){
            animal.decreseEnergy();
            worldMap.move(animal);
        }
    }
    public void run() {
        for(int i =0; i<20; i++) {
            System.out.println("ANIMALS COUNT:" + statistics.getAnimalsCount());
            removeDeadAnimals();
            moveAnimals();
            consumeAndBreed();
            ((GlobeMap)worldMap).spawnPlants(settings.dailyPlants());
        }
    }

}
