package agh.ics.oop.model;

public class Plant implements WorldElement{

    private int energy;
    private final Vector2d position;

    public Plant(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() { return position; }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "*";
    }
    @Override
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

}
