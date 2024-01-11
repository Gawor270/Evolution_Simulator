package agh.ics.oop.model;


import agh.ics.oop.model.variantsInterfaces.MapVariant;
import agh.ics.oop.model.variantsInterfaces.PlantGrowthVariant;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RectangularFloraMap extends AbstractWorldMap {

    protected Boundary globeBounds;


    private PlantGrowthVariant plantGrowthVariant;
    protected Map<Vector2d, Plant> plants = new HashMap();
    public RectangularFloraMap(int width, int height, int startPlants, MapVariant mapVariant, PlantGrowthVariant plantGrowthVariant) {
        globeBounds = new Boundary(new Vector2d(0,0), new Vector2d(width, height));
        super.setMapVariant(mapVariant);
        setPlantGrowthVariant(plantGrowthVariant);
        plantGrowthVariant.setPositions(globeBounds);
        plantGrowthVariant.growPlants(startPlants, this);
    }

    @Override
    public void setMapVariant(MapVariant mapVariant) {
        super.setMapVariant(mapVariant);
    }

    public void setPlantGrowthVariant(PlantGrowthVariant plantGrowthVariant) {
        this.plantGrowthVariant = plantGrowthVariant;
    }

    @Override
    public void move(WorldElement element) {
        if(element instanceof Animal){
            Vector2d startpos = element.getPosition();
            remove(element);
            ((Animal) element).move(mapVariant, globeBounds);
            String info = "animal moved from" + startpos.toString() + "to" + element.getPosition().toString();
            place(element);
            mapChanged(info);
        }

    }

    public int growPlants(int plantsCount){
        return plantGrowthVariant.growPlants(plantsCount, this);
    }
    public void place(WorldElement element){
        if(element instanceof Plant) {
            plants.put(element.getPosition(), (Plant) element);
            notifyObservers(this, "Plant added at " + element.getPosition());
        }else{
            super.place(element);
        }
    }

    public void removePlant(Plant plant){
        plantGrowthVariant.removePlant(plant, this);
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
