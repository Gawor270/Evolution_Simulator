package agh.ics.oop.model;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class GlobeMap extends AbstractWorldMap {

    private final Boundary globeBounds;

    private Map<Vector2d, Plant> plants = new HashMap();
    public GlobeMap(int width, int height) {
        globeBounds = new Boundary(new Vector2d(0,0), new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.getY() >= 0 && position.getY() <= globeBounds.upperBound().getY();
    }

    public void place(WorldElement element){
        if(element instanceof Plant) {
            plants.put(element.getPosition(), (Plant) element);
            notifyObservers(this, "Plant added at " + element.getPosition());
        }else{
            super.place(element);
        }
    }

    public void remove(WorldElement element){
        if(element instanceof Plant) {
            plants.remove(element.getPosition());
            notifyObservers(this, "Plant eaten at" + element.getPosition());
        }
        else
            super.remove(element);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || plants.containsKey(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        return globeBounds;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(super.objectAt(position) == null){
            return plants.get(position);
        }
        return super.objectAt(position);
    }
    public Map<Vector2d,TreeSet<Animal>> getAnimals(){
        return animals;
    }

    public Map<Vector2d, Plant> getPlants() {
        return plants;
    }

    public int getWidth(){
        return globeBounds.upperBound().getX()+1;
    }

    @Override
    protected WorldMap getWorldMap() {
        return this;
    }
}
