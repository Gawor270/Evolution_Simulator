package agh.ics.oop.presenter;

import agh.ics.oop.model.SettingsBuilder;
import javafx.fxml.FXML;

import java.awt.*;

public class BuilderController {

    private SettingsBuilder builder = new SettingsBuilder();
    @FXML
    private TextField mapHeight;
    @FXML
    private TextField mapWidth;

    @FXML
    private TextField startPlants;

    @FXML
    private TextField startAnimals;

    @FXML
    private TextField animalStartEnergy;

    @FXML
    private TextField plantEnergy;

    @FXML
    private TextField dailyPlants;

    @FXML
    private TextField fullEnergy;

    @FXML
    private TextField breedingEnergy;

    @FXML
    private TextField minMutations;

    @FXML
    private TextField maxMutations;

    @FXML
    private TextField genomeLength;


    public void saveSettings(ActiveEvent activeEvent){

    }

    public  void startSimulation(ActiveEvent activeEvent){

    }

}
