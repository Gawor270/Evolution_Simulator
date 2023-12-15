package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.TextMap;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class TextSimulation {

    private final List<MoveDirection> moves;

    private final List<String> texts;

    private final WorldMap<String, Integer> worldMap;

    public TextSimulation(List<String> texts, List<MoveDirection> moves){
        this.worldMap = new TextMap(texts);
        this.texts = texts;
        this.moves = moves;
    }

    public void run() {
        int countMoves = 0;
        System.out.println(worldMap);
        for(MoveDirection mov : moves) {
            worldMap.move(worldMap.objectAt(countMoves),mov);
            System.out.println(worldMap);
            countMoves++;
            countMoves %= texts.size();
        }
    }

}
