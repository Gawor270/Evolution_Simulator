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



    public void remove(WorldElement element){
        if(element instanceof Animal){
            animals.get(element.getPosition()).remove(((Animal)element));
            if(animals.get(element.getPosition()).isEmpty())
                animals.remove(element.getPosition());
        }
    }

    @Override
    public void move(WorldElement element) {
        if(element instanceof Animal){
            Vector2d startpos = element.getPosition();
            remove(element);
            ((Animal) element).move(this);
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
        return animals.containsKey(position) && !animals.get(position).isEmpty();
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.containsKey(position) && !animals.get(position).isEmpty())
            return animals.get(position).first();
        return null;
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
