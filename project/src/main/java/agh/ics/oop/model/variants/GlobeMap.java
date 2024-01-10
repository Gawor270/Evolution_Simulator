package agh.ics.oop.model.variants;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.Optional;

public class GlobeMap implements MapVariant<Vector2d> {

    @Override
    public Optional<Vector2d> nextPosition(Vector2d position, Boundary globeBounds){
        if(position.getY() >= 0 && position.getY() <= globeBounds.upperBound().getY()
                && position.getX() >= 0 && position.getX() <= globeBounds.upperBound().getX()){
            int width = globeBounds.upperBound().getX() + 1;
            return Optional.of(new Vector2d((position.getX() + width )%width, position.getY()));
        }
        return Optional.empty();
    }

}
