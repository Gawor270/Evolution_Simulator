package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.variants.BitOfMadness;
import agh.ics.oop.model.variants.ForestedEquator;
import agh.ics.oop.model.variants.PoisonousPlants;

import java.util.*;

public class Simulation implements Runnable{


    private List<Animal> animals;
    private SimulationStatistics statistics;

    private final RectangularFloraMap worldMap;
    private SimulationSettings settings;
    public Simulation(int mapHeight, int mapWidth, SimulationSettings settings){
        statistics = new SimulationStatistics(this);
        worldMap = new RectangularFloraMap(mapWidth, mapHeight, settings.startPlants(), new GlobeMap(), new PoisonousPlants());
        worldMap.registerObserver(new ConsoleMapDisplay());
        animals = new ArrayList<>();
        this.settings = settings;
        spawnAnimals(settings.startAnimals(), settings.animalStartEnergy(), mapWidth, mapHeight);
    }

    private void spawnAnimals(int startAnimals, int animalStartEnergy,int w,int h){
        Random random = new Random();
        for(int i = 0; i < startAnimals; i++){
            Vector2d position = new Vector2d(random.nextInt(w), random.nextInt(h));
            Animal animal = new Animal(null, position, animalStartEnergy, settings.genomeLength() , new BitOfMadness());
            animals.add(animal);
            worldMap.place(animal);
            statistics.increaseAnimalsCount();
        }
    }



    private void consumeAndBreed(){
        for(Map.Entry<Vector2d, TreeSet<Animal>> entry : worldMap.getAnimals().entrySet()){
            Vector2d position = entry.getKey();
            TreeSet<Animal> animals = entry.getValue();
            if(animals != null){
                Animal strongest = animals.first();
                if(worldMap.getPlants().get(position) != null) {
                    if(worldMap.getPlants().get(position).isPoisonous()){
                        strongest.setEnergy(strongest.getEnergy() - settings.plantEnergy());
                    }
                    else{
                        strongest.setEnergy(strongest.getEnergy() + settings.plantEnergy());
                    }
                    worldMap.removePlant(worldMap.getPlants().get(position));
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
        for(int i =0; i<100; i++) {
            System.out.println("ANIMALS COUNT:" + statistics.getAnimalsCount());
            removeDeadAnimals();
            moveAnimals();
            consumeAndBreed();
            worldMap.growPlants(settings.dailyPlants());
        }
    }

}
