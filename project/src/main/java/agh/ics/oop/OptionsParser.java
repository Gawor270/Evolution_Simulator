package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args){
        List<MoveDirection> directions = new LinkedList<>();


        for(String arg : args){
            switch(arg){
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "fr" -> directions.add(MoveDirection.FORWARD_RIGHT);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "br" -> directions.add(MoveDirection.BACKWARD_RIGHT);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "bl" -> directions.add(MoveDirection.BACKWARD_LEFT);
                case "l" -> directions.add(MoveDirection.LEFT);
                case "fl" -> directions.add(MoveDirection.FORWARD_LEFT);
                default -> throw new IllegalArgumentException(arg + "is not legal move specification");
            }
        }
        return directions;
    }
}
