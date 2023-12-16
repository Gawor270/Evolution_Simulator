package agh.ics.oop.model;

import java.util.List;

public class AnimalStatistics {

    private int age = 0;
    private int childrenCounter =0;
    private int descendantsCounter =0;
    private int plantCounter = 0;

    private final Animal animal;
    public AnimalStatistics(Animal animal) {
        this.animal = animal;
    }

    public void updateStatistics() {
        List<Animal> parents = animal.getParents();
        if (parents != null) {
            for (Animal parent : animal.getParents()) {
                parent.getStatistics().increaseChildrenCounter();
            }
        }
        updateDescendantsCounter();
    }

    private void updateDescendantsCounter() {
        if(animal.getParents() != null) {
            for (Animal parent : animal.getParents()) {
                parent.getStatistics().increaseDescendantsCounter();
                parent.getStatistics().increaseDescendantsCounter();
            }
        }
    }

    public void getOlder(){
        age++;
    }

    public int getAge() {
        return age;
    }

    public int getChildrenCounter() {
        return childrenCounter;
    }

    public void increasePlantCounter(){
        plantCounter++;
    }

    public void increaseChildrenCounter(){
        childrenCounter++;
    }

    public void increaseDescendantsCounter(){
        descendantsCounter++;
    }

}