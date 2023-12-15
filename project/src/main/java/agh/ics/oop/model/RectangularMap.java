package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerBound;
    private final Vector2d upperBound;
    public RectangularMap(int width, int height) {
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
