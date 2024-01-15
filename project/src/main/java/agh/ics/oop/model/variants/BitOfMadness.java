package agh.ics.oop.model.variants;

import agh.ics.oop.model.*;
import agh.ics.oop.model.variantsInterfaces.AnimalMoveVariant;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

import java.util.Optional;
import java.util.Random;

public class BitOfMadness implements AnimalMoveVariant {

    public void moveOnce(RectangularFloraMap map, Animal animal) {
        Vector2d newPos = new Vector2d(animal.getPosition().getX(), animal.getPosition().getY());
        MapDirection orientation = animal.getOrientation().moveBy(animal.getGenome().nextGen());
        newPos = newPos.add(orientation.toUnitVector());
        Optional<Vector2d> optionalNewPos = map.getMapVariant().nextPosition(newPos, map);

        if (optionalNewPos.isPresent()) {
            animal.setPosition(optionalNewPos.get());
            animal.setOrientation(orientation);
        } else {
            animal.setOrientation(orientation.moveBy(4));
        }
    }

    public void move( RectangularFloraMap map, Animal animal) {
        Random random = new Random();
        int randomNumber =  random.nextInt(5);
        if(randomNumber == 0){
            moveOnce(map, animal);
            int randomind = random.nextInt(animal.getGenome().getGenome().size());
            int check = animal.getGenome().getCurrentIndex();
            while (randomind == check){
                randomind = random.nextInt(animal.getGenome().getGenome().size());
            }
            animal.getGenome().setIndex(randomind);
        }
        else{
            moveOnce(map, animal);
            moveOnce(map, animal);
        }

    }
}
