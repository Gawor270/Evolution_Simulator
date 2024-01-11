package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.variants.BitOfMadness;
import agh.ics.oop.model.variants.GlobeMap;
import agh.ics.oop.model.variants.PoisonousPlants;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;
import agh.ics.oop.presenter.SimulationPresenter;

import java.util.*;

import static java.lang.Thread.sleep;

public class Simulation implements Runnable{

    private final Object lock = new Object();

    private boolean isPaused = false;

    public boolean isPaused(){
        return isPaused;
    }

    private List<Animal> animals;
    private SimulationStatistics statistics;

    private final RectangularFloraMap worldMap;
    private SimulationSettings settings;

    private int day = 0;
    public Simulation(SimulationSettings settings){
        statistics = new SimulationStatistics(this);
        statistics.increasePlantsCount(settings.startPlants());
        statistics.updateTotalEnergy(settings.startAnimals() * settings.animalStartEnergy());
        worldMap = new RectangularFloraMap(settings.mapWidth(), settings.mapHeight(), settings.startPlants(), new GlobeMap(), settings.plantGrowthVariant());
        animals = new ArrayList<>();
        this.settings = settings;
        spawnAnimals();
    }

    public void addSimulationObserver(MapChangeListener observer){
        worldMap.registerObserver(observer);
    }

    private void spawnAnimals(){
        Random random = new Random();
        for(int i = 0; i < settings.startAnimals(); i++){
            Vector2d position = new Vector2d(random.nextInt(settings.mapWidth()), random.nextInt(settings.mapHeight()));
            Animal animal = new Animal(null, position, settings.animalStartEnergy(), settings.genomeLength() , settings.animalMoveVariant());
            animals.add(animal);
            worldMap.place(animal);
            statistics.increaseAnimalsCount();
        }
    }



    private void consumeAndBreed(){
        ArrayList<Animal> toAdd = new ArrayList();
        for(Map.Entry<Vector2d, TreeSet<Animal>> entry : worldMap.getAnimals().entrySet()){
            Vector2d position = entry.getKey();
            TreeSet<Animal> animals = entry.getValue();
            if(animals != null){
                Animal strongest = animals.first();
                if(worldMap.getPlants().get(position) != null) {
                    if(worldMap.getPlants().get(position).isPoisonous()){
                        statistics.updateTotalEnergy(-Math.min(strongest.getEnergy(), settings.plantEnergy()));
                        strongest.setEnergy(strongest.getEnergy() - settings.plantEnergy());
                    }
                    else{
                        strongest.setEnergy(strongest.getEnergy() + settings.plantEnergy());
                        statistics.updateTotalEnergy(settings.plantEnergy());
                    }
                    worldMap.removePlant(worldMap.getPlants().get(position));
                    statistics.decreasePlantsCount();
                }
                if(animals.size() > 1  && animals.first().getEnergy() >= settings.fullEnergy()){
                    Animal secondStrongest = animals.stream().skip(1).findFirst().get();
                    if(secondStrongest.getEnergy() >= settings.fullEnergy()){
                        Animal child = strongest.reproduce(secondStrongest,settings.breedingEnergy(), settings.minMutations(), settings.maxMutations());
                        toAdd.add(child);
                        this.animals.add(child);
                        statistics.increaseAnimalsCount();
                    }
                }
            }
        }
        for(Animal animal : toAdd){
            worldMap.place(animal);
        }
    }

    private void removeDeadAnimals(){
        List<Animal> toRemove = new ArrayList<>();
        for(Animal animal : animals){
            if(animal.getEnergy() <= 0){
                statistics.decreaseAnimalsCount();
                statistics.updateAvgLifespan(animal);
                worldMap.remove(animal);
                toRemove.add(animal);
            }
        }
        animals.removeAll(toRemove);
    }

    private void moveAnimals(){
        for(Animal animal : animals){
            animal.decreseEnergy();
            animal.getStatistics().getOlder();
            statistics.updateTotalEnergy(-1);
            worldMap.move(animal);
        }
    }
    public void run() {
        while(animals.size() > 0 ){
            synchronized (lock){
                while(isPaused){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            executeOneStep();
            increaseDay();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeOneStep(){
        removeDeadAnimals();
        worldMap.notifyObservers(worldMap, "Day " + day);
        moveAnimals();
        worldMap.notifyObservers(worldMap, "Day " + day);
        consumeAndBreed();
        worldMap.notifyObservers(worldMap, "Day " + day);
        statistics.increasePlantsCount(worldMap.growPlants(settings.dailyPlants()));
        updateFreeSpaceCount();
        day++;
    }

    private void updateFreeSpaceCount(){
        int count = 0;
        for(int i = 0; i < settings.mapWidth(); i++){
            for(int j = 0; j < settings.mapHeight(); j++){
                if(!worldMap.isOccupied(new Vector2d(i,j))){
                    count++;
                }
            }
        }
        statistics.setFreeSpaceCount(count);
    }

    public void pause(){
        synchronized (lock){
            isPaused = true;
        }
    }
    public void resume(){
        synchronized (lock){
            isPaused = false;
            lock.notifyAll();
        }
    }

    public List<Animal> getAnimals(){
        return animals;
    }

    public int getDay() {
        return day;
    }

    public void increaseDay(){
        day++;
    }

    public SimulationStatistics getStatistics(){
        return statistics;
    }
}
