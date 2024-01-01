package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GlobeMap extends AbstractWorldMap {

    private final Boundary globeBounds;

    private Map<Vector2d, Plant> plants = new HashMap();
    public GlobeMap(int width, int height, int startPlants) {
        globeBounds = new Boundary(new Vector2d(0,0), new Vector2d(width, height));
        RandomPositionGenerator plantPositionGenerator = new RandomPositionGenerator(width, height, startPlants);
        for(Vector2d pos : plantPositionGenerator){
            this.place(new Plant(pos));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.getY() >= 0 && position.getY() < globeBounds.upperBound().getY();
    }

    public void place(WorldElement element){
        if(element instanceof Plant)
        plants.put(element.getPosition(), (Plant)element);
        else
            super.place(element);
    }

    public void remove(WorldElement element){
        if(element instanceof Plant)
            plants.remove(element.getPosition());
        else
            super.remove(element);
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
