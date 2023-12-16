package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;



public class SimulationPresenter implements MapChangeListener {
    private GrassField map;

    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveListTextField;
    @FXML
    private GridPane mapGrid;

    private final int WINDOW_SIZE = 400;

    public void setWorldMap(GrassField map) {this.map = map;}
    public void drawMap(WorldMap worldMap) {
        clearGrid();
        Boundary bounds = worldMap.getCurrentBounds();
        int height = bounds.upperBound().getY() - bounds.lowerBound().getY()+2;
        int width = bounds.upperBound().getX() - bounds.lowerBound().getX()+2;

        for (int i = 0; i < width; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(WINDOW_SIZE/width)); // CELL_WIDTH to szerokość komórki
        }

        for (int i = 0; i < height; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(WINDOW_SIZE/height)); // CELL_HEIGHT to wysokość komórki
        }

        Label label = new Label("y/x");
        mapGrid.add(label,0,0);
        GridPane.setHalignment(label, HPos.CENTER);

        for(int i=0; i<width-1; i++){
            String elem = Integer.toString(bounds.lowerBound().getX() + i);
            label = new Label(elem);
            mapGrid.add(label,i+1,0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for(int i=0; i<height-1; i++){
            String elem = Integer.toString(bounds.lowerBound().getY() + i);
            label = new Label(elem);
            mapGrid.add(label,0,height-1-i);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for(int i=0; i<width-1; i++){
            for(int j=0; j<height-1; j++){
                if(worldMap.isOccupied(new Vector2d(bounds.lowerBound().getX() +i,bounds.lowerBound().getY() +j))){
                    String elem = worldMap.objectAt(new Vector2d(bounds.lowerBound().getX() +i,bounds.lowerBound().getY() +j)).toString();
                    label = new Label(elem);
                    mapGrid.add(label,i+1,height-1-j);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged( WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            infoLabel.setText(message);
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {

        map = new GrassField(4);
        setWorldMap(map);
        map.registerObserver(this);
        map.registerObserver(new ConsoleMapDisplay());

        String userInput = moveListTextField.getText();
        String[] moves = userInput.split(" ");
        System.out.println(userInput);

        List<Vector2d> positions = List.of(new Vector2d(-1, -1), new Vector2d(7, 7));


        Simulation simulation = new Simulation(positions, directions, map);
        Thread thread = new Thread(simulation);
        thread.start();

    }
}
