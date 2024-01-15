package agh.ics.oop.model.variants;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.RectangularFloraMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.Optional;
import java.util.Random;

public class GlobeMap implements MapVariant<Vector2d> {

    @Override
    public Optional<Vector2d> nextPosition(Vector2d position, RectangularFloraMap map) {
        Boundary globeBounds = map.getGlobeBounds();
        Random random = new Random();
        int randomNumber =  random.nextInt(5);
        if(randomNumber == 0){
            if(map.getPlants().containsKey(position) && map.getPlants().get(position).isPoisonous()){
                position = new Vector2d(position.getX()+1, position.getY());
            }
        }
        if(position.getY() >= 0 && position.getY() <= globeBounds.upperBound().getY()){
            int width = globeBounds.upperBound().getX() + 1;
            return Optional.of(new Vector2d((position.getX() + width )%width, position.getY()));
        }
        return Optional.empty();
    }

}
