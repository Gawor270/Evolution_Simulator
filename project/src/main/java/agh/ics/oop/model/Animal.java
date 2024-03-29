package agh.ics.oop.model;

import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.*;


public class Animal implements WorldElement, Comparable<Animal>{
    private MapDirection orientation;
    private Vector2d position;
    private int energy;
    private Genome genome;
    private AnimalStatistics statistics;
    private final List<Animal> parents;

    AnimalMoveVariant moveVariant;
    private final Map<MapDirection, String> asciiRepresentation =
        new HashMap<>(){{
            put(MapDirection.NORTH_EAST, "7");
            put(MapDirection.EAST, ">");
            put(MapDirection.SOUTH_EAST, "J");
            put(MapDirection.SOUTH, "v");
            put(MapDirection.SOUTH_WEST, "L");
            put(MapDirection.WEST, "<");
            put(MapDirection.NORTH, "^");
            put(MapDirection.NORTH_WEST, "F");
    }};


    public Animal(List<Animal> parents , Vector2d position, int energy, int genomeSize, AnimalMoveVariant moveVariant) {
        this.genome = new Genome(genomeSize);
        this.statistics = new AnimalStatistics(this);
        this.parents = parents;
        this.position = position;
        this.energy = energy;
        this.orientation = MapDirection.getRandomDirection();
        this.moveVariant = moveVariant;
        statistics.updateStatistics();
    }

    public Animal(List<Animal> parents , Vector2d position, int energy, Genome genome, AnimalMoveVariant moveVariant) {
        this.genome = genome;
        this.statistics = new AnimalStatistics(this);
        this.moveVariant = moveVariant;
        this.parents = parents;
        this.position = position;
        this.energy = energy;
        this.orientation = MapDirection.getRandomDirection();
        statistics.updateStatistics();
    }

    public void eatPlant(int plantEnergy){
        energy += plantEnergy;
        statistics.increasePlantCounter();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals((position));
    }

    public void move(RectangularFloraMap map) {
        moveVariant.move(map, this);
    }

    public Animal reproduce(Animal other, int breedEnergy, int minMutations, int maxMutations){
        int childEnergy = 2*breedEnergy;
        energy -= breedEnergy;
        other.energy -= breedEnergy;
        return new Animal(new ArrayList<>(List.of(this, other)), position, childEnergy,
                genome.cross(other.getGenome(), energy + breedEnergy,
                        other.getEnergy() + breedEnergy, minMutations, maxMutations), moveVariant);
    }

    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getOrientation() { return orientation; }
    public List<Animal> getParents() { return parents; }
    public int getEnergy() { return energy; }

    public void decreseEnergy(){
        energy--;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setEnergy(int energy) { this.energy = energy; }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public AnimalStatistics getStatistics() {
        return statistics;
    }


    @Override
    public String toString() {
        return String.valueOf(this.energy) + " " + asciiRepresentation.get(orientation);
    }

    @Override
    public int compareTo(Animal o) {

        int compareOrientation = Integer.compare(o.getOrientation().ordinal(), this.orientation.ordinal());
        if(compareOrientation != 0){
            return compareOrientation;
        }

        int energyCompare = Integer.compare(o.getEnergy(), this.energy);
        if(energyCompare != 0){
            return energyCompare;
        }

        int ageCompare = Integer.compare(o.statistics.getAge(), statistics.getAge());
        if(ageCompare != 0){
            return ageCompare;
        }

        int childrenCompare = Integer.compare(o.statistics.getChildrenCounter(), statistics.getChildrenCounter());
        if(childrenCompare != 0){
            return childrenCompare;
        }

        return (int) Math.pow(-1, o.hashCode() % 2);
    }
}
