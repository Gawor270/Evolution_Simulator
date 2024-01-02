package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.RandomFreePositionGenerator;

import java.util.*;

public class Simulation implements Runnable{


    private List<Animal> animals;
    private SimulationStatistics statistics;

    private RandomFreePositionGenerator positionGenerator;
    private final WorldMap<WorldElement, Vector2d> worldMap;
    private SimulationSettings settings;
    public Simulation(int mapHeight, int mapWidth, SimulationSettings settings){
        statistics = new SimulationStatistics(this);
        positionGenerator = new RandomFreePositionGenerator(mapWidth, mapHeight);
        worldMap = new GlobeMap(mapWidth, mapHeight);
        ((GlobeMap)worldMap).registerObserver(new ConsoleMapDisplay());
        animals = new ArrayList<>();
        this.settings = settings;
        spawnAnimals(settings.startAnimals(), settings.animalStartEnergy(), mapWidth, mapHeight);
        spawnPlants(settings.startPlants());
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

    private void spawnPlants(int plantsCount){
        for(int i = 0; i < plantsCount; i++){
            Vector2d position = positionGenerator.getPosition();
            if(position == null){
                break;
            }
            Plant plant = new Plant(position);
            worldMap.place(plant);
            statistics.increasePlantsCount();
        }
    }



    private void update(){
        for(Map.Entry<Vector2d, TreeSet<Animal>> entry : ((GlobeMap)worldMap).getAnimals().entrySet()){
            Vector2d position = entry.getKey();
            TreeSet<Animal> animals = entry.getValue();
            if(animals != null){
                Animal strongest = animals.first();
                if(((GlobeMap)worldMap).getPlants().get(position) != null) {
                    strongest.eatPlant(settings.plantEnergy());
                    ((GlobeMap)worldMap).remove(((GlobeMap)worldMap).getPlants().get(position));
                    positionGenerator.addPosition(position);
                }
                if(animals.size() > 1  && animals.first().getEnergy() >= settings.breedingEnergy()){
                    Animal secondStrongest = animals.stream().skip(1).findFirst().get();
                    if(secondStrongest.getEnergy() >= settings.breedingEnergy()){
                        Animal child = strongest.reproduce(secondStrongest,settings.breedingEnergy());
                        worldMap.place(child);
                        statistics.increaseAnimalsCount();
                    }
                }
            }
        }
    }

    private void moveAnimals(){
        List<Animal> toRemove = new ArrayList<>();
        for(Animal animal : animals){
            animal.decreseEnergy();
            if(animal.getEnergy() <= 0){
                statistics.decreaseAnimalsCount();
                ((GlobeMap)worldMap).remove(animal);
                toRemove.add(animal);
            }
            else{
                worldMap.move(animal);
            }
        }
        animals.removeAll(toRemove);
    }
    public void run() {
        for(int i =0; i<10; i++) {
            System.out.println("ANIMALS COUNT:" + statistics.getAnimalsCount());
            update();
            moveAnimals();
            spawnPlants(settings.dailyPlants());
        }
    }

}
