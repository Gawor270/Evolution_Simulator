package agh.ics.oop.model.util;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Plant;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageSupplier {

    Image animalImage;

    Image plantImage;

    private SimulationPresenter simulationPresenter;

    private int squareSize;

    public ImageSupplier(SimulationPresenter simulationPresenter){
        animalImage = new Image(new File("./project/src/main/resources/img/guinea_pig.png").toURI().toString());
        plantImage = new Image(new File("./project/src/main/resources/img/lettuce.png").toURI().toString());
        this.simulationPresenter = simulationPresenter;
        int bigger = Math.max(simulationPresenter.getSimulation().getSettings().mapWidth(), simulationPresenter.getSimulation().getSettings().mapHeight());
        squareSize = simulationPresenter.getWINDOW_SIZE()/bigger;
    }
    public ImageView getImageView(Object element){
        if(element instanceof Animal){
            ImageView imageView = new ImageView(animalImage);
            imageView.setRotate(((Animal) element).getOrientation().toAngle());
            double opacity = Math.min(1.0,((Animal) element).getEnergy()/(double)(simulationPresenter.getSimulation().getSettings().fullEnergy()));
            imageView.setOpacity(opacity);
            if(simulationPresenter.getTrackedAnimal() != null && ((Animal) element).getPosition() == simulationPresenter.getTrackedAnimal().getPosition()){
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setHue(0.9);
                imageView.setEffect(colorAdjust);
            }
            imageView.setFitHeight(squareSize);
            imageView.setFitWidth(squareSize);
            return imageView;
        }
        else{
            ImageView imageView = new ImageView(plantImage);
            if(((Plant)element).isPoisonous()){
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setHue(0.9);
                imageView.setEffect(colorAdjust);
            }
            imageView.setFitHeight(squareSize);
            imageView.setFitWidth(squareSize);
            return imageView;
        }
    }

    public ImageView getRedAnimalImage(Object element){
        ImageView imageView = new ImageView(animalImage);
        imageView.setRotate(((Animal) element).getOrientation().toAngle());
        imageView.setFitHeight(squareSize);
        imageView.setFitWidth(squareSize);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.0);
        colorAdjust.setSaturation(1.0);
        colorAdjust.setBrightness(0.0);
        colorAdjust.setContrast(1.0);
        imageView.setEffect(colorAdjust);
        return imageView;
    }
}
