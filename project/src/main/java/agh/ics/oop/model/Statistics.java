package agh.ics.oop.model;

import java.util.List;

public class Statistics {
    public int childrenCounter;

    public int descendantsCounter;

    private final Animal animal;
    public Statistics(Animal animal) {
        this.animal = animal;
    }

    public void updateStatistics() {
        List<Animal> parents = animal.getParents();
        if (parents != null) {
            for (Animal parent : animal.getParents()) {
                parent.statistics.childrenCounter++;
            }
        }
        updateDescendantsCounter();
    }

    private void updateDescendantsCounter() {
        if(animal.getParents() != null) {
            for (Animal parent : animal.getParents()) {
                parent.statistics.descendantsCounter++;
                parent.statistics.updateDescendantsCounter();
            }
        }
    }


}
