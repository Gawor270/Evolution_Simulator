package agh.ics.oop.model.variants;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.Optional;
import java.util.Random;

public class BitOfMadness implements AnimalMoveVariant {

    public void moveOnce(MapVariant validator, Boundary bounds, Animal animal) {
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

    public void move(MapVariant validator, Boundary bounds, Animal animal) {
        Random random = new Random();
        int randomNumber =  random.nextInt(5);
        if(randomNumber == 0){
            moveOnce(validator, bounds, animal);
            int randomind = random.nextInt(animal.getGenome().getGenome().size());
            animal.getGenome().setIndex(randomind);
        }
        else{
            moveOnce(validator, bounds, animal);
            moveOnce(validator, bounds, animal);
        }

    }
}
