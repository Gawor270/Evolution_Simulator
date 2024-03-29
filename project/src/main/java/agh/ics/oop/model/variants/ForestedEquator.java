package agh.ics.oop.model.variants;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Plant;
import agh.ics.oop.model.RectangularFloraMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.util.RandomFreePositionGenerator;
import agh.ics.oop.model.variantsInterfaces.PlantGrowthVariant;

import java.util.Optional;

public class ForestedEquator implements PlantGrowthVariant {

    Boundary equator;

    RandomFreePositionGenerator positionGenerator;

    @Override
    public int growPlants(int plantsCount, RectangularFloraMap map) {
        int counter = 0;
        for(int i = 0; i < plantsCount; i++){
            Optional<Vector2d> position = positionGenerator.getPosition();
            if(position.isEmpty()){
                continue;
            }
            position.ifPresent(vector2d -> map.place(new Plant(vector2d)));
            counter++;
        }
        return counter;
    }

    @Override
    public void removePlant(Plant plant, RectangularFloraMap map) {
        if(plant.getPosition().getY() >= equator.lowerBound().getY() && plant.getPosition().getY() <= equator.upperBound().getY()){
            positionGenerator.addPreferredPosition(plant.getPosition());
        }
        else{
            positionGenerator.addPosition(plant.getPosition());
        }
        map.getPlants().remove(plant.getPosition());
        map.notifyObservers(map, "Plant removed at " + plant.getPosition());
    }

    @Override
    public void setPositions(Boundary boundary) {
        positionGenerator = new RandomFreePositionGenerator(80);
        int h = boundary.upperBound().getY() - boundary.lowerBound().getY() + 1;
        int need = (int) Math.round((float)(h)/5.0);
        int zone = need/2 ;
        System.out.println(zone);
        System.out.println(h);
        System.out.println(Math.floorDiv(h,2) - zone);
        System.out.println(Math.floorDiv(h+1,2) +zone);
        equator = new Boundary(new Vector2d(0 , Math.floorDiv(h,2) - zone),
                new Vector2d(boundary.upperBound().getX(), Math.floorDiv(h+1,2) +zone));

        for(int i = boundary.lowerBound().getX(); i <= boundary.upperBound().getX(); i++){
            for(int j = boundary.lowerBound().getY(); j <= boundary.upperBound().getY(); j++){
                if(j < equator.lowerBound().getY() || j >= equator.upperBound().getY()){
                    positionGenerator.addPosition(new Vector2d(i, j));
                }
                else{
                    positionGenerator.addPreferredPosition(new Vector2d(i, j));
                }
            }
        }
    }

    public Boundary getEquator(){return this.equator;}
}
