package agh.ics.oop.model;

import agh.ics.oop.model.Vector2d;

public interface MoveValidator<T> {

    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(T position);
}
