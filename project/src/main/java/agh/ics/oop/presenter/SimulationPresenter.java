package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ImageSupplier;
import agh.ics.oop.model.variants.ForestedEquator;
import agh.ics.oop.model.variants.PoisonousPlants;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

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
    @FXML
    private Button fieldsButton;
    @FXML
    private Button animalsButton;
    @FXML
    private Label dayLabel;

    @FXML
    private Label dominantGenomeLabel;
    private Simulation simulation;

    private XYChart.Series<Number, Number> animalsCount;
    private XYChart.Series<Number, Number> plantsCount;

    private XYChart.Series<Number, Number> freeSpaceCount;

    private XYChart.Series<Number, Number> averageLifespan;
    private XYChart.Series<Number, Number> averageEnergy;

    private XYChart.Series<Number, Number> averageChildrenCount;
    private Animal trackedAnimal = null;

    @FXML
    private Label beforeSelect;
    @FXML
    private Label selectedAnimalGenome;
    @FXML
    private Label selectedAnimalEnergy;
    @FXML
    private Label selectedAnimalChildren;
    @FXML
    private Label selectedAnimalAge;
    @FXML
    private Label selectedAnimalDescendants;
    @FXML
    private Label selectedAnimalEaten;
    @FXML
    private Label selectedAnimalDeathDay;
    @FXML
    private Label selectedAnimalActivatedGen;
    private int day = -1;
    private final int WINDOW_SIZE = 500;
    private ImageSupplier imageSupplier;
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
        averageChildrenCount = new XYChart.Series<>();
        averageChildrenCount.setName("Average children count");

        countChart.getData().addAll(animalsCount, plantsCount, freeSpaceCount);
        averageChart.getData().addAll(averageLifespan,averageEnergy, averageChildrenCount);
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
        simulation.addSimulationObserver(this);
        imageSupplier = new ImageSupplier(this);
    }
    public void drawMap(WorldMap worldMap) {
        clearGrid();
        Boundary bounds = worldMap.getCurrentBounds();
        int height = bounds.upperBound().getY();
        int width = bounds.upperBound().getX();

        int bigger = Math.max(height, width);
        int squareSize = WINDOW_SIZE/bigger;

        for (int i = 0; i <= width; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(squareSize)); // CELL_WIDTH to szerokość komórki
        }

        for (int i = 0; i <= height; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(squareSize)); // CELL_HEIGHT to wysokość komórki
        }
        mapGrid.setStyle("-fx-background-color: #67aa67; -fx-grid-lines-visible: true; fx-border-width: 2px; -fx-border-color: black");

        for(int i=0; i<= width; i++){
            for(int j=0; j<= height; j++){
                if(worldMap.isOccupied(new Vector2d(i,j))){
                    ImageView imageView = imageSupplier.getImageView(worldMap.objectAt(new Vector2d(i,j)));
                    imageView.setFitHeight(squareSize);
                    imageView.setFitWidth(squareSize);
                    mapGrid.add(imageView, i, j);
                    if(worldMap.objectAt(new Vector2d(i,j)) instanceof Animal){
                        addPane(i,j);
                    }
//                    mapGrid.add(new Label(worldMap.objectAt(new Vector2d(i,j)).toString()), i, j);
                }
            }
        }
    }

    private void addPane(int i, int j){
        Pane pane = new Pane();
        pane.setOnMouseClicked((MouseEvent event) -> {
            handleGridPaneClick(i,j);
        });
        mapGrid.add(pane, i, j);
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
                updateData();
            }
        });
    }

    public void onPauseClicked(ActionEvent actionEvent) {
        if(simulation.isPaused()){
            animalsButton.setVisible(false);
            fieldsButton.setVisible(false);
            simulation.resume();
            pauseButton.setText("Pause");
        }
        else{
            simulation.pause();
            animalsButton.setVisible(true);
            fieldsButton.setVisible(true);
            pauseButton.setText("Resume");
        }

    }

    public void onFieldsButtonClicked(ActionEvent actionEvent) {
        Boundary boundary;
        if (simulation.getWorldMap().getPlantGrowthVariant() instanceof PoisonousPlants) {
            boundary = ((PoisonousPlants) simulation.getWorldMap().getPlantGrowthVariant()).getSquare();
        } else {
            boundary = ((ForestedEquator) simulation.getWorldMap().getPlantGrowthVariant()).getEquator();
        }

        Boundary bounds = simulation.getWorldMap().getCurrentBounds();
        int height = bounds.upperBound().getY();
        int width = bounds.upperBound().getX();

        int bigger = Math.max(height, width);
        int squareSize = WINDOW_SIZE / bigger;

        for (int i = boundary.lowerBound().getX(); i <= boundary.upperBound().getX(); i++) {
            for (int j = boundary.lowerBound().getY(); j < boundary.upperBound().getY(); j++) {
                javafx.scene.shape.Rectangle square = new Rectangle(squareSize, squareSize);
                square.setFill(Color.BLUE);
                square.setOpacity(0.5);
                mapGrid.add(square, i, j);
            }
        }
    }


    private void updateData(){
        dayLabel.setText("Day: " + simulation.getDay());
        dominantGenomeLabel.setText("Dominant genome: " + simulation.getStatistics().getMostCommonGenome().toString());
        updateChartData();
        updateTrackedAnimal();
    }
    private void updateTrackedAnimal(){
        if(trackedAnimal != null) {
            beforeSelect.setVisible(false);
            selectedAnimalGenome.setVisible(true);
            selectedAnimalGenome.setText("Genome: " + trackedAnimal.getGenome().toString());
            selectedAnimalActivatedGen.setVisible(true);
            selectedAnimalActivatedGen.setText("Activated gen: " + trackedAnimal.getGenome().getGen());
            selectedAnimalEnergy.setVisible(true);
            selectedAnimalEnergy.setText("Energy: " + trackedAnimal.getEnergy());
            selectedAnimalChildren.setVisible(true);
            selectedAnimalChildren.setText("Children: " + trackedAnimal.getStatistics().getChildrenCounter());
            selectedAnimalAge.setVisible(true);
            selectedAnimalAge.setText("Age: " + trackedAnimal.getStatistics().getAge());
            selectedAnimalEaten.setVisible(true);
            selectedAnimalEaten.setText("Eaten: " + trackedAnimal.getStatistics().getPlantCounter());
            selectedAnimalDescendants.setText("Descendants: " + trackedAnimal.getStatistics().getDescendantsCounter());
            selectedAnimalDescendants.setVisible(true);
            if (trackedAnimal.getStatistics().getDeathDay() != -1) {
                selectedAnimalDeathDay.setText("Death day: " + trackedAnimal.getStatistics().getDeathDay());
                selectedAnimalDeathDay.setVisible(true);
            } else {
                selectedAnimalDeathDay.setVisible(false);
            }
        }
    }
    private void updateChartData() {
        double x = simulation.getDay();
        double y1 = simulation.getStatistics().getAnimalsCount();
        double y2 = simulation.getStatistics().getPlantsCount();
        double y3 = simulation.getStatistics().getFreeSpaceCount();

        double y4 = simulation.getStatistics().getAvgLifespan();
        double y5 = simulation.getStatistics().getAvgEnergy();
        double y6 = simulation.getStatistics().getAvgChildrenCount();


        animalsCount.getData().add(new XYChart.Data<>(x, y1));
        plantsCount.getData().add(new XYChart.Data<>(x, y2));
        freeSpaceCount.getData().add(new XYChart.Data<>(x, y3));

        averageLifespan.getData().add(new XYChart.Data<>(x, y4));
        averageEnergy.getData().add(new XYChart.Data<>(x, y5));
        averageChildrenCount.getData().add(new XYChart.Data<>(x, y6));

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
        if(averageChildrenCount.getData().size() > 20){
            averageChildrenCount.getData().remove(0);
        }

        xAxis.lowerBoundProperty().setValue(animalsCount.getData().get(0).getXValue());
        xAxis.upperBoundProperty().setValue(animalsCount.getData().get(animalsCount.getData().size() - 1).getXValue());

        xAxis2.lowerBoundProperty().setValue(averageLifespan.getData().get(0).getXValue());
        xAxis2.upperBoundProperty().setValue(averageLifespan.getData().get(averageLifespan.getData().size() - 1).getXValue());
    }



    private void handleGridPaneClick(int x, int y){
        if(simulation.isPaused()){
            WorldElement element = simulation.getWorldMap().objectAt(new Vector2d(x,y));
            if(element instanceof Animal){
                trackedAnimal = (Animal) element;
                updateTrackedAnimal();
                drawMap(simulation.getWorldMap());
            }

        }
    }

    @FXML
    private void onAnimalButtonClickedt sta(ActionEvent event){
        List<Animal> toHighlight = simulation.getMostCommonGenomeAnimals();
        for(Animal animal : toHighlight){
            ImageView imageView = imageSupplier.getRedAnimalImage(animal);
            mapGrid.add(imageView, animal.getPosition().getX(), animal.getPosition().getY());
        }
    }

    public int getWINDOW_SIZE() {
        return WINDOW_SIZE;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public Animal getTrackedAnimal() {
        return trackedAnimal;
    }
}
