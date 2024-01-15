package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.*;

abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected Map<Vector2d, TreeSet<Animal>> animals = new HashMap<>();
    protected MapVisualizer mapVis = new MapVisualizer(this);

    protected MapVariant mapVariant;
    private final UUID id = UUID.randomUUID();
    private List<MapChangeListener> observers = new ArrayList<>();

    public void registerObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void unregisterObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public void notifyObservers(WorldMap worldMap, String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(worldMap, message);
        }
    }

    protected void mapChanged(String message) {
        notifyObservers(this.getWorldMap(), message);
    }

    protected abstract WorldMap getWorldMap();


    @Override
    public void place(WorldElement element) {
        TreeSet<Animal> animalSet = animals.getOrDefault(element.getPosition(), new TreeSet<>());
        animalSet.add((Animal) element);
        animals.put(element.getPosition(), animalSet);
    }



    public void remove(WorldElement element){
        if(element instanceof Animal){
            Iterator<Animal> iterator = animals.get(element.getPosition()).iterator();
            while(iterator.hasNext()){
                Animal animal = iterator.next();
                if(animal.equals(element)){
                    iterator.remove();
                    break;
                }
            }
            if(animals.get(element.getPosition()).isEmpty()){
                animals.remove(element.getPosition());
            }
        }
    }

    @Override
    public void move(WorldElement element) {
        if(element instanceof Animal){
            Vector2d startpos = element.getPosition();
            remove(element);
            ((Animal) element).move(null);
            String info = "animal moved from" + startpos.toString() + "to" + element.getPosition().toString();
            place(element);
            mapChanged(info);
        }

    }

    public Boundary getCurrentBounds(){
        return null;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.containsKey(position)) return animals.get(position).first();
        return null;
    }

    public Map<Vector2d, TreeSet<Animal>> getAnimals() {
        return animals;
    }

    public void setMapVariant(MapVariant mapVariant) {
        this.mapVariant = mapVariant;
    }

    public MapVariant getMapVariant() {
        return mapVariant;
    }

    @Override
    public String toString() {
        return mapVis.draw(getCurrentBounds().lowerBound(), getCurrentBounds().upperBound());
    }

    @Override
    public UUID getId() {
        return id;
    }
}
