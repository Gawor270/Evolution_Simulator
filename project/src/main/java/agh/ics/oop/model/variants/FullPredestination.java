package agh.ics.oop.model.variants;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.Optional;

public class FullPredestination implements AnimalMoveVariant {

    public void move(MapVariant validator, Boundary bounds, Animal animal) {
        Vector2d newPos = new Vector2d(animal.getPosition().getX(), animal.getPosition().getY());
        MapDirection orientation = animal.getOrientation().moveBy(animal.getGenome().nextGen());
        newPos = newPos.add(orientation.toUnitVector());
        Optional<Vector2d> optionalNewPos = validator.nextPosition(newPos, bounds);

        if (optionalNewPos.isPresent()) {
            animal.setPosition(optionalNewPos.get());
            animal.setOrientation(orientation);
        } else {
            animal.setOrientation(orientation.moveBy(4));
        }
    }
}
