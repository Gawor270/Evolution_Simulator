package agh.ics.oop.model;

public class GlobeMap extends AbstractWorldMap {

    private final Boundary globeBounds;
    public GlobeMap(int width, int height) {
        globeBounds = new Boundary(new Vector2d(0,0), new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerBound) && position.precedes(upperBound) && super.canMoveTo(position);
    }


    @Override
    public Boundary getCurrentBounds() {
        return super.getCurrentBounds();
    }

    @Override
    protected WorldMap getWorldMap() {
        return this;
    }
}
