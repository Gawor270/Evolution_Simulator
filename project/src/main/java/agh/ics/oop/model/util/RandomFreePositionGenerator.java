package agh.ics.oop.model.util;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Vector2d;

import java.util.*;

public class RandomFreePositionGenerator {

    private List<Vector2d> positions;
    private List<Vector2d> preferredPositions;

    private int probability;

    public RandomFreePositionGenerator(int probability) {
        positions = new ArrayList<>();
        preferredPositions = new ArrayList<>();
        this.probability = probability;
    }

    public void addPosition(Vector2d position){
        positions.add(position);
        int radomind = new Random().nextInt(positions.size());
        Collections.swap(positions, positions.size() - 1, radomind);
    }

    public void addPreferredPosition(Vector2d position){
        preferredPositions.add(position);
        int radomind = new Random().nextInt(preferredPositions.size());
        Collections.swap(preferredPositions, preferredPositions.size() - 1, radomind);
    }

    public Optional<Vector2d> getPosition(){
        Random random = new Random();
        int randomIndex = random.nextInt(100);
        if(randomIndex < probability){
            if(preferredPositions.isEmpty())
                return Optional.empty();
            else
                return Optional.of(preferredPositions.remove(preferredPositions.size() - 1));
        }
        else{
            if(positions.isEmpty())
                return Optional.empty();
            else
                return Optional.of(positions.remove(positions.size() - 1));
        }
    }


}
