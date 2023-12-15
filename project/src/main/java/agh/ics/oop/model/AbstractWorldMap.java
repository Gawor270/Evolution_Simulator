package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected Map<Vector2d, TreeSet<Animal>> animals = new HashMap<>();
    protected MapVisualizer mapVis = new MapVisualizer(this);

    private final UUID id = UUID.randomUUID();
    private List<MapChangeListener> observers = new ArrayList<>();

    public void registerObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void unregisterObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(WorldMap worldMap, String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(worldMap, message);
        }
    }

    protected void mapChanged(String message) {
        notifyObservers(this.getWorldMap(), message);
    }

    protected abstract WorldMap getWorldMap();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public void place(WorldElement element) {
        TreeSet<Animal> animalSet = animals.getOrDefault(element.getPosition(), new TreeSet<>());
        animalSet.add((Animal) element);
        animals.put(element.getPosition(), animalSet);
    }

    @Override
    public void move(WorldElement element) {
        if(element instanceof Animal){
            animals.remove(element.getPosition());
            ((Animal) element).move(this);
            String info = "animal at " + element.getPosition() + " moved";
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
    public String toString() {
        return mapVis.draw(getCurrentBounds().lowerBound(), getCurrentBounds().upperBound());
    }

    @Override
    public UUID getId() {
        return id;
    }
}
