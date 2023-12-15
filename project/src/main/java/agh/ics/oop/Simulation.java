package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{

    private final List<MoveDirection>  moves;
    private final List<Animal> animals;
    private final WorldMap<WorldElement, Vector2d> worldMap;
    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap<WorldElement, Vector2d> worldMap) {
        this.worldMap = worldMap;
        animals = new ArrayList<>();
        for(Vector2d position : positions) {
            Animal newAnimal = new Animal(position);
            animals.add(newAnimal);
            worldMap.place(newAnimal);
        }

        this.moves = moves;
    }

    public void run() {
        int countMoves = 0;
//        System.out.println(worldMap);
        for(MoveDirection mov : moves) {
            try {
                worldMap.move(animals.get(countMoves),mov);
                Thread.sleep(500);
                countMoves++;
                countMoves %= animals.size();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(worldMap);

        }
    }

}
