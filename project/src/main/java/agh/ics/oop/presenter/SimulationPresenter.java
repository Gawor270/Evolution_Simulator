package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import com.sun.javafx.charts.Legend;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;
import java.util.Random;


public class SimulationPresenter implements MapChangeListener {

    @FXML
    private LineChart<Number,Number> countChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private LineChart<Number,Number> averageChart;

    @FXML
    private NumberAxis xAxis2;


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
                if(worldMap.isOccupied(new Vector2d(i,j))){
                    String elem = worldMap.objectAt(new Vector2d(i,j)).toString();
                    Label label = new Label(elem);
                    mapGrid.add(label,i,height-j);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
                else{
                    Label label = new Label("");
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
    private XYChart.Series<Number, Number> animalsCount;
    private XYChart.Series<Number, Number> plantsCount;

    private XYChart.Series<Number, Number> freeSpaceCount;

    private XYChart.Series<Number, Number> averageLifespan;
    private XYChart.Series<Number, Number> averageEnergy;

    @FXML
    public void initialize() {
        animalsCount = new XYChart.Series<>();
        animalsCount.setName("Number of animals");
        plantsCount = new XYChart.Series<>();
        plantsCount.setName("Number of plants");
        freeSpaceCount = new XYChart.Series<>();
        freeSpaceCount.setName("Number of free spaces");
        averageLifespan = new XYChart.Series<>();
        averageLifespan.setName("Average lifespan");
        averageEnergy = new XYChart.Series<>();
        averageEnergy.setName("Average energy");

        countChart.getData().addAll(animalsCount, plantsCount, freeSpaceCount);
        averageChart.getData().addAll(averageLifespan,averageEnergy);

    }

    private void updateChartData() {
        double x = simulation.getDay();
        double y1 = simulation.getStatistics().getAnimalsCount();
        double y2 = simulation.getStatistics().getPlantsCount();
        double y3 = simulation.getStatistics().getFreeSpaceCount();

        double y4 = simulation.getStatistics().getAvgLifespan();
        double y5 = simulation.getStatistics().getAvgEnergy();


        animalsCount.getData().add(new XYChart.Data<>(x, y1));
        plantsCount.getData().add(new XYChart.Data<>(x, y2));
        freeSpaceCount.getData().add(new XYChart.Data<>(x, y3));

        averageLifespan.getData().add(new XYChart.Data<>(x, y4));
        averageEnergy.getData().add(new XYChart.Data<>(x, y5));

        if (animalsCount.getData().size() > 20) {
            animalsCount.getData().remove(0);
        }
        if (plantsCount.getData().size() > 20) {
            plantsCount.getData().remove(0);
        }
        if (freeSpaceCount.getData().size() > 20) {
            freeSpaceCount.getData().remove(0);
        }

        if (averageLifespan.getData().size() > 20) {
            averageLifespan.getData().remove(0);
        }
        if(averageEnergy.getData().size() > 20){
            averageEnergy.getData().remove(0);
        }

        xAxis.lowerBoundProperty().setValue(animalsCount.getData().get(0).getXValue());
        xAxis.upperBoundProperty().setValue(animalsCount.getData().get(animalsCount.getData().size() - 1).getXValue());

        xAxis2.lowerBoundProperty().setValue(averageLifespan.getData().get(0).getXValue());
        xAxis2.upperBoundProperty().setValue(averageLifespan.getData().get(averageLifespan.getData().size() - 1).getXValue());
    }

    @FXML
    private void handleGridPaneClick(MouseEvent mouseEvent){
        int clickedRow = mapGrid.getRowIndex((Node) mouseEvent.getSource());
        int clickedColumn = mapGrid.getColumnIndex((Node) mouseEvent.getSource());
        System.out.println("Clicked cell: " + clickedColumn + " " + clickedRow);
    }
}
