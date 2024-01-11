package agh.ics.oop.model.variantsInterfaces;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Plant;
import agh.ics.oop.model.RectangularFloraMap;

public interface PlantGrowthVariant {

    public int growPlants(int plantsCount, RectangularFloraMap map);
    public void removePlant(Plant plant, RectangularFloraMap map);
    public void setPositions(Boundary boundary);
}
