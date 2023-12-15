package agh.ics.oop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Animal implements WorldElement, Comparable<Animal>{
    private final MapDirection orientation;
    private Vector2d position;

    private int age;
    public int childrenCounter;
    public int descendantsCounter;
    private int energy;
    private int plantCounter;
    private final List<Animal> parents;
    private final Map<MapDirection, String> asciiRepresentation =
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
        age = 0;
        childrenCounter = 0;
        descendantsCounter = 0;
        plantCounter = 0;
        this.parents = parents;
        this.position = position;
        this.energy = energy;
        this.orientation = MapDirection.NORTH;
        updateParents();
    }

    public void getOlder(){
        age++;
    }
    public void eatPlant(Plant plant){
        setEnergy(getEnergy() + plant.getEnergy());
        plantCounter++;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals((position));
    }

    public void move(MoveValidator validator) {
        Vector2d newPos =  new Vector2d(position.getX(), position.getY());
//        Instead of one there will be value of current gen
        newPos = newPos.add(orientation.moveBy(1).toUnitVector());

        if(validator.canMoveTo(newPos)){
            position = newPos;
        }

    }

    public void updateParents() {
        if (parents != null) {
            for (Animal parent : parents) {
                parent.childrenCounter++;
            }
        }
        updateDescendantsCounter(this);
    }
    private void updateDescendantsCounter(Animal animal) {
        if(animal.getParents() != null) {
            for (Animal parent : animal.getParents()) {
                parent.descendantsCounter++;
                parent.updateDescendantsCounter(parent);
            }
        }
    }

    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getOrientation() { return orientation; }
    public List<Animal> getParents() { return parents; }
    public int getEnergy() { return energy; }
    public int getPlantCounter() { return plantCounter; }
    public int getAge() { return age; }
    public void setEnergy(int energy) { this.energy = energy; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && Objects.equals(position, animal.position);
    }

    @Override
    public String toString() {
        return asciiRepresentation.get(orientation);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }

    @Override
    public int compareTo(Animal o) {
        if(this.energy == o.getEnergy()){
            if(this.age == o.getAge()){
                if(this.childrenCounter == o.childrenCounter){
                    return (int) Math.pow(-1, (int) (Math.random() * 2));
                }
                return Integer.compare(o.childrenCounter,this.childrenCounter);
            }
            return Integer.compare(o.getAge(),this.age);
        }
        return Integer.compare(o.getEnergy(),this.energy)
    }
}
