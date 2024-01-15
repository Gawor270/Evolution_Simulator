package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.BuilderController;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.List;

public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SettingsWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Settings");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }
}
