package agh.ics.oop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;

    private int energy;
    private final List<Animal> parents;
    public Statistics statistics;
    private final Map<MapDirection, String> representation =
        new HashMap<>(){{
            put(MapDirection.NORTH_EAST, "6");
            put(MapDirection.EAST, ">");
            put(MapDirection.SOUTH_EAST, "J");
            put(MapDirection.SOUTH, "v");
            put(MapDirection.SOUTH_WEST, "L");
            put(MapDirection.WEST, "<");
            put(MapDirection.NORTH, "^");
            put(MapDirection.NORTH_WEST, "F");
    }};


    public Animal(List<Animal> parents , Vector2d position, int energy) {
        statistics = new Statistics(this);
        this.parents = parents;
        this.position = position;
        this.energy = energy;
    }

    public List<Animal> getParents() {
        return parents;
    }
    public void eatPlant(Plant plant){
        setEnergy(getEnergy() + plant.getEnergy());
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return representation.get(orientation);
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals((position));
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        Vector2d newPos =  new Vector2d(position.getX(), position.getY());
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> newPos = newPos.add(orientation.toUnitVector());
            case BACKWARD -> newPos = newPos.subtract(orientation.toUnitVector());
        }

        if(validator.canMoveTo(newPos)){
            position = newPos;
        }

    }

    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }
}
