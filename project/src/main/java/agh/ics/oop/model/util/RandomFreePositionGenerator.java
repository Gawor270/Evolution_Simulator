package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

import java.util.*;

public class RandomFreePositionGenerator {

    private List<Vector2d> positions;

    public RandomFreePositionGenerator(int maxWidth, int maxHeight) {
        positions = new ArrayList<>();
        for (int i = 0; i <= maxWidth; i++) {
            for (int j = 0; j <= maxHeight; j++) {
                positions.add(new Vector2d(i, j));
            }
        }
    }

    public void addPosition(Vector2d position){
        positions.add(position);
        Collections.shuffle(positions);
    }

    public Vector2d getPosition(){
        if(positions.isEmpty()){
            return null;
        }
        else{
            Vector2d result = positions.remove(positions.size() - 1);
            Collections.shuffle(positions);
            return result;
        }
    }


}
