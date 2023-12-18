package agh.ics.oop.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GlobeMap extends AbstractWorldMap {

    private final Boundary globeBounds;

    private Map<Vector2d, Plant> plants = new HashMap();
    public GlobeMap(int width, int height, int startPlants, int plantEnergy, int dailyPlants) {
        globeBounds = new Boundary(new Vector2d(0,0), new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.getY() >= 0 && position.getY() < globeBounds.upperBound().getY();
    }


    @Override
    public Boundary getCurrentBounds() {
        return super.getCurrentBounds();
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.containsKey(position)){
            return animals.get(position).first();
        }
        if(plants.containsKey(position)){
            return plants.get(position);
        }
        return null;
    }

    @Override
    public Collection<WorldElement> getAnimals() {
        return null;
    }

    @Override
    protected WorldMap getWorldMap() {
        return this;
    }
}
