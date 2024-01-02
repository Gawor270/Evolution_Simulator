package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World {

    public static void main(String[] args){
        System.out.println("Start");
        SimulationSettings settings = new SimulationSettings(4, 20, 10, 10, 10, 10, 1, 10, 10, 10);
        Simulation simulation = new Simulation(10,10,settings);
        simulation.run();
    }
}