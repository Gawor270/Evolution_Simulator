package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;
import java.util.Random;


public class SimulationPresenter implements MapChangeListener {

    @FXML
    private LineChart<Number,Number> lineChart1;

    @FXML
    private NumberAxis xAxis;


    @FXML
    private GridPane mapGrid;
    @FXML
    private Button pauseButton;

    private Simulation simulation;

    private int day = -1;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
        simulation.addSimulationObserver(this);
    }
    private final int WINDOW_SIZE = 400;

    public void drawMap(WorldMap worldMap) {
        clearGrid();
        Boundary bounds = worldMap.getCurrentBounds();
        int height = bounds.upperBound().getY();
        int width = bounds.upperBound().getX();

        for (int i = 0; i <= width; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(WINDOW_SIZE/width)); // CELL_WIDTH to szerokość komórki
        }

        for (int i = 0; i <= height; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(WINDOW_SIZE/height)); // CELL_HEIGHT to wysokość komórki
        }

        for(int i=0; i<= width; i++){
            for(int j=0; j<= height; j++){
                if(worldMap.isOccupied(new Vector2d(bounds.lowerBound().getX() +i,bounds.lowerBound().getY() +j))){
                    String elem = worldMap.objectAt(new Vector2d(bounds.lowerBound().getX() +i,bounds.lowerBound().getY() +j)).toString();
                    Label label = new Label(elem);
                    mapGrid.add(label,i,height-j);
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
            if(day != simulation.getDay()){
                day = simulation.getDay();
                updateChartData();
            }
        });
    }

    public void onPauseClicked(ActionEvent actionEvent) {
        if(simulation.isPaused()){
            simulation.resume();
            pauseButton.setText("Pause");
        }
        else{
            simulation.pause();
            pauseButton.setText("Resume");
        }

    }
    private XYChart.Series<Number, Number> series;
    private XYChart.Series<Number, Number> series2;

    @FXML
    public void initialize() {
        series = new XYChart.Series<>();
        series2 = new XYChart.Series<>();

        lineChart1.getData().add(series);
        lineChart1.getData().add(series2);

    }

    private void updateChartData() {
        double x = simulation.getDay();
        double y1 = simulation.getStatistics().getAnimalsCount();
        double y2 = simulation.getStatistics().getPlantsCount();


        series.getData().add(new XYChart.Data<>(x, y1));
        series2.getData().add(new XYChart.Data<>(x, y2));

        if (series.getData().size() > 20) {
            series.getData().remove(0);
        }
        if (series2.getData().size() > 20) {
            series2.getData().remove(0);
        }

        xAxis.lowerBoundProperty().setValue(series.getData().get(0).getXValue());
        xAxis.upperBoundProperty().setValue(series.getData().get(series.getData().size() - 1).getXValue());

    }
}
