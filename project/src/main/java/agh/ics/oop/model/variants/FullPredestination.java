package agh.ics.oop.model.variants;

import agh.ics.oop.model.*;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.Optional;

public class FullPredestination implements AnimalMoveVariant {

    public void move(RectangularFloraMap map, Animal animal) {
        MapVariant validator = map.getMapVariant();
        Boundary bounds = map.getGlobeBounds();
        Vector2d newPos = new Vector2d(animal.getPosition().getX(), animal.getPosition().getY());
        MapDirection orientation = animal.getOrientation().moveBy(animal.getGenome().nextGen());
        newPos = newPos.add(orientation.toUnitVector());
        Optional<Vector2d> optionalNewPos = validator.nextPosition(newPos, map);

        if (optionalNewPos.isPresent()){
            animal.setPosition(optionalNewPos.get());
            animal.setOrientation(orientation);
        } else {
            animal.setOrientation(orientation.moveBy(4));
        }
    }
}
