package agh.ics.oop.model.variantsInterfaces;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.RectangularFloraMap;
import agh.ics.oop.model.variantsInterfaces.MapVariant;

public interface AnimalMoveVariant {

    public void move(RectangularFloraMap map, Animal animal);
}
