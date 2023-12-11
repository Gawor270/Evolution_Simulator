package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;

    private final Map<MapDirection, String> representation;
    public Animal() {
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
        representation = new HashMap<>(){{
           put(MapDirection.NORTH, "^");
           put(MapDirection.SOUTH, "v");
           put(MapDirection.WEST, "<");
           put(MapDirection.EAST, ">");
        }};
    }

    public Animal(Vector2d position) {
        this();
        this.position = position;
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
