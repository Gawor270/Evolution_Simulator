package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.SettingsBuilder;
import agh.ics.oop.model.variants.BitOfMadness;
import agh.ics.oop.model.variants.ForestedEquator;
import agh.ics.oop.model.variants.FullPredestination;
import agh.ics.oop.model.variants.PoisonousPlants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class BuilderController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField mapHeightField;
    @FXML
    private TextField mapWidthField;

    @FXML
    private TextField startPlantsField;

    @FXML
    private TextField startAnimalsField;

    @FXML
    private TextField animalStartEnergyField;

    @FXML
    private TextField plantEnergyField;

    @FXML
    private TextField dailyPlantsField;

    @FXML
    private TextField fullEnergyField;

    @FXML
    private TextField breedingEnergyField;

    @FXML
    private TextField minMutationsField;

    @FXML
    private TextField maxMutationsField;

    @FXML
    private TextField genomeLengthField;

    @FXML
    private ComboBox plantGrowthVariant;

    @FXML
    private ComboBox animalMoveVariant;

    @FXML
    private TextField saveFileName;

    @FXML
    private ListView<String> fileListView;


    @FXML
    public void initialize(){
        String directoryPath = "./project/src/main/resources/saves";

        // Get the list of files from the directory
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // Create an ObservableList to store file names
        ObservableList<String> fileNames = FXCollections.observableArrayList();

        // Add file names to the ObservableList
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }

        // Set the ObservableList as the items of the ListView
        fileListView.setItems(fileNames);
    }

    public void startSimulation(ActionEvent actionEvent) throws IOException {
        SettingsBuilder builder = new SettingsBuilder();
        builder.setMapHeight(Integer.parseInt(mapHeightField.getText()));
        builder.setMapWidth(Integer.parseInt(mapWidthField.getText()));
        builder.setStartPlants(Integer.parseInt(startPlantsField.getText()));
        builder.setStartAnimals(Integer.parseInt(startAnimalsField.getText()));
        builder.setAnimalStartEnergy(Integer.parseInt(animalStartEnergyField.getText()));
        builder.setPlantEnergy(Integer.parseInt(plantEnergyField.getText()));
        builder.setDailyPlants(Integer.parseInt(dailyPlantsField.getText()));
        builder.setFullEnergy(Integer.parseInt(fullEnergyField.getText()));
        builder.setBreedingEnergy(Integer.parseInt(breedingEnergyField.getText()));
        builder.setMinMutations(Integer.parseInt(minMutationsField.getText()));
        builder.setMaxMutations(Integer.parseInt(maxMutationsField.getText()));
        builder.setGenomeLength(Integer.parseInt(genomeLengthField.getText()));

        if(plantGrowthVariant.getValue().equals("Poisonous Plants")){
            System.out.println(plantGrowthVariant.getValue());
            builder.setPlantGrowthVariant(new PoisonousPlants());
        }
        else if(plantGrowthVariant.getValue().equals("Forested Equator")){
            builder.setPlantGrowthVariant(new ForestedEquator());
        }
        if(animalMoveVariant.getValue().equals("Bit Of Madness")){
            System.out.println(animalMoveVariant.getValue());
            builder.setAnimalMoveVariant(new BitOfMadness());
        }
        else if(animalMoveVariant.getValue().equals("Full Predestination")){
            builder.setAnimalMoveVariant(new FullPredestination());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        root = loader.load();

        SimulationPresenter presenter = loader.getController();

        stage = new Stage();
        //stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("NewHope");
        stage.show();

        Simulation simulation = builder.build(presenter);
        presenter.setSimulation(simulation);
        Thread thread = new Thread(simulation);
        thread.start();

    }

    public void saveSettings(ActionEvent actionEvent){
        SettingsBuilder builder = new SettingsBuilder();
        builder.setMapHeight(Integer.parseInt(mapHeightField.getText()));
        builder.setMapWidth(Integer.parseInt(mapWidthField.getText()));
        builder.setStartPlants(Integer.parseInt(startPlantsField.getText()));
        builder.setStartAnimals(Integer.parseInt(startAnimalsField.getText()));
        builder.setAnimalStartEnergy(Integer.parseInt(animalStartEnergyField.getText()));
        builder.setPlantEnergy(Integer.parseInt(plantEnergyField.getText()));
        builder.setDailyPlants(Integer.parseInt(dailyPlantsField.getText()));
        builder.setFullEnergy(Integer.parseInt(fullEnergyField.getText()));
        builder.setBreedingEnergy(Integer.parseInt(breedingEnergyField.getText()));
        builder.setMinMutations(Integer.parseInt(minMutationsField.getText()));
        builder.setMaxMutations(Integer.parseInt(maxMutationsField.getText()));
        builder.setGenomeLength(Integer.parseInt(genomeLengthField.getText()));
        builder.setAnimalMoveVariantName(animalMoveVariant.getValue().toString());
        builder.setPlantGrowthVariantName(plantGrowthVariant.getValue().toString());

        builder.saveToJson(saveFileName.getText());
        initialize();
    }

    public void handleFileSelection(){
        String fileName = fileListView.getSelectionModel().getSelectedItem();
        SettingsBuilder builder = SettingsBuilder.loadFromJson(fileName);
        mapHeightField.setText(String.valueOf(builder.getMapHeight()));
        mapWidthField.setText(String.valueOf(builder.getMapWidth()));
        startPlantsField.setText(String.valueOf(builder.getStartPlants()));
        startAnimalsField.setText(String.valueOf(builder.getStartAnimals()));
        animalStartEnergyField.setText(String.valueOf(builder.getAnimalStartEnergy()));
        plantEnergyField.setText(String.valueOf(builder.getPlantEnergy()));
        dailyPlantsField.setText(String.valueOf(builder.getDailyPlants()));
        fullEnergyField.setText(String.valueOf(builder.getFullEnergy()));
        breedingEnergyField.setText(String.valueOf(builder.getBreedingEnergy()));
        minMutationsField.setText(String.valueOf(builder.getMinMutations()));
        maxMutationsField.setText(String.valueOf(builder.getMaxMutations()));
        genomeLengthField.setText(String.valueOf(builder.getGenomeLength()));
        plantGrowthVariant.setValue(builder.getPlantGrowthVariantName());
        animalMoveVariant.setValue(builder.getAnimalMoveVariantName());
    }
}
