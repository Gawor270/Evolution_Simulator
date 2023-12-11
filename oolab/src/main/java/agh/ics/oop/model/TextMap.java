package agh.ics.oop.model;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TextMap implements WorldMap<String, Integer> {
    private Map<String, Integer> map;
    List<String> texts;

    public TextMap(List<String> texts) {
        this.texts = texts;
        map = new HashMap<String, Integer>();
        Integer pos = 0;
        for (String s : texts) {
            map.put(s, pos);
            pos++;
        }

    }

    @Override
    public boolean canMoveTo(Integer position) {
        return 0 <= position && position < texts.size();
    }

    @Override
    public void place(String text) {
        texts.add(text);
        map.put(text,texts.size()-1);
    }


    @Override
    public void move(String text, MoveDirection direction) {
        Integer curr = map.get(text);
        Integer newPos = curr + switch (direction){
            case FORWARD -> 1;
            case BACKWARD -> -1;
            default -> 0;
        };

        if(canMoveTo(newPos)){
            map.remove(text,curr);
            map.remove(texts.get(newPos),newPos);
            Collections.swap(texts, curr, newPos);
            map.put(text, newPos);
            map.put(texts.get(curr), curr);
        }
    }

    @Override
    public Boundary getCurrentBounds() {
        return null;
    }

    @Override
    public boolean isOccupied(Integer position) {
        return canMoveTo(position);
    }

    @Override
    public String objectAt(Integer position) {
        return texts.get(position);
    }

    public String toString() {
        return String.join(", ",texts);
    }


    @Override
    public Collection<String> getAnimals() {
        return texts;
    }

    @Override
    public UUID getId() {
        return null;
    }
}

