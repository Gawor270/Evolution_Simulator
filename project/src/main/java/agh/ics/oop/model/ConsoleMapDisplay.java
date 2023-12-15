package agh.ics.oop.model;

public class ConsoleMapDisplay implements  MapChangeListener{
    private int totalUpdates = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (this){
            System.out.println("World id: " + worldMap.getId());
            System.out.println("Received message: " + message);
            System.out.println("Map representation:");
            System.out.println(worldMap);
            totalUpdates++;
            System.out.println("Total updates so far: " + totalUpdates);
            System.out.println("----------------------");
        }
    }
}
