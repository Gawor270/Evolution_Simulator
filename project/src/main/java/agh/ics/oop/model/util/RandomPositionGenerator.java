package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomPositionGenerator implements Iterable<Vector2d> {

    private List<Vector2d> positions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int count) {
        positions = new ArrayList<>(count);
        for (int i = 0; i <= maxWidth; i++) {
            for (int j = 0; j <= maxHeight; j++) {
                positions.add(new Vector2d(i, j));
            }
        }

        Collections.shuffle(positions);
        positions = positions.subList(0,count);
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new PositionIterator();
    }

    private class PositionIterator implements Iterator<Vector2d> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < positions.size();
        }

        @Override
        public Vector2d next() {
            if (!hasNext()) {
                throw new IllegalStateException("Brak kolejnych elementów");
            }
            return positions.get(currentIndex++);
        }
    }
}
