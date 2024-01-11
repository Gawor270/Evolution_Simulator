package agh.ics.oop.model.variants;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Plant;
import agh.ics.oop.model.RectangularFloraMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.util.RandomFreePositionGenerator;
import agh.ics.oop.model.variantsInterfaces.PlantGrowthVariant;

import java.util.Optional;

public class PoisonousPlants implements PlantGrowthVariant {

    Boundary square;

    RandomFreePositionGenerator generator = new RandomFreePositionGenerator(100);
    @Override
    public int growPlants(int plantsCount, RectangularFloraMap map) {
        int counter = 0;
        for(int i = 0; i < plantsCount; i++){
            Optional<Vector2d> position = generator.getPosition();
            if(position.isPresent()){
                counter++;
                Vector2d pos = position.get();
                if(pos.getX() >= square.lowerBound().getX() && pos.getX() <= square.upperBound().getX() && pos.getY() >= square.lowerBound().getY() && pos.getY() <= square.upperBound().getY()) {
                    map.place(new Plant(pos,true));
                }
                else {
                    map.place(new Plant(pos,false));
                }
                map.notifyObservers(map, "Plant grown at " + pos);

            }
            else{
                break;
            }
        }
        return counter;

    }

    @Override
    public void removePlant(Plant plant, RectangularFloraMap map) {
        generator.addPreferredPosition(plant.getPosition());
        map.getPlants().remove(plant.getPosition());
        map.notifyObservers(map, "Plant removed at " + plant.getPosition());
    }

    @Override
    public void setPositions(Boundary boundary) {
        int rectArea = (boundary.upperBound().getX()+1) * (boundary.upperBound().getY()+1);
        int twentyPercent = (int) (rectArea * 0.2);
        int squareSide = (int) Math.sqrt(twentyPercent);
        this.square = new Boundary(new Vector2d(0,0), new Vector2d(squareSide,squareSide));
        for(int i=0; i<= boundary.upperBound().getX(); i++) {
            for (int j = 0; j <= boundary.upperBound().getY(); j++) {
                    generator.addPreferredPosition(new Vector2d(i, j));
            }
        }
    }
}
