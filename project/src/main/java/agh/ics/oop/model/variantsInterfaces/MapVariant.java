package agh.ics.oop.model.variantsInterfaces;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Vector2d;

import java.util.Optional;

public interface MapVariant<T> {

    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */

    Optional<Vector2d> nextPosition(T position, Boundary bounds);
}
