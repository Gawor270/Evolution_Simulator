package agh.ics.oop.model;

public class GlobeMap extends AbstractWorldMap {

    private final Vector2d lowerBound;
    private final Vector2d upperBound;
    public GlobeMap(int width, int height) {
        lowerBound = new Vector2d(0,0);
        upperBound = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerBound) && position.precedes(upperBound) && super.canMoveTo(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerBound, upperBound);
    }

    @Override
    protected WorldMap getWorldMap() {
        return this;
    }
}
