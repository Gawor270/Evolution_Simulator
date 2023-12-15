package agh.ics.oop.model;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;
public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int no_tufts){
        this.placeGrass(no_tufts);
    }

    private void placeGrass(int n){
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int)Math.sqrt(10*n),(int)Math.sqrt(10*n), n);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grass.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(super.objectAt(position) == null){
            return grass.get(position);
        }
        else{
            return super.objectAt(position);
        }
    }
    public Vector2d getLowerLeft() {
        Optional<Vector2d> minPositionX = animals.keySet().stream().min(Comparator.comparing(Vector2d::getX));
        Optional<Vector2d> minPositionY = animals.keySet().stream().min(Comparator.comparing(Vector2d::getY));
        if(minPositionX.isPresent() && minPositionY.isPresent()){
            return new Vector2d(minPositionX.get().getX(), minPositionY.get().getY());
        }
        return new Vector2d(0,0);
    }

    public Vector2d getUpperRight() {
        Optional<Vector2d> maxPositionY = animals.keySet().stream().max(Comparator.comparing(Vector2d::getY));
        Optional<Vector2d> maxPositionX = animals.keySet().stream().max(Comparator.comparing(Vector2d::getX));
        if(maxPositionX.isPresent() && maxPositionY.isPresent()){
            return new Vector2d(maxPositionX.get().getX(), maxPositionY.get().getY());
        }
        return new Vector2d(0,0);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(getLowerLeft(),getUpperRight());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !super.isOccupied(position);
    }

    @Override
    protected WorldMap getWorldMap() {
        return this;
    }
}
