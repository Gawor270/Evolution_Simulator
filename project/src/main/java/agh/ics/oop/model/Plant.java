package agh.ics.oop.model;

public class Plant implements WorldElement{

    private final Vector2d position;
    private boolean poisonous = false;

    public Plant(Vector2d position) {
        this.position = position;
    }

    public Plant(Vector2d position, boolean poisonous) {
        this.position = position;
        this.poisonous = poisonous;
    }

    public Vector2d getPosition() { return position; }

    public boolean isPoisonous() {
        return poisonous;
    }

    @Override
    public String toString() {
        if(poisonous) {
            return "P";
        }
        else {
            return "*";
        }
    }
    @Override
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

}
