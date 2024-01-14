package agh.ics.oop.model.util;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Plant;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageSupplier {

    Image animalImage;


    Image plantImage;
    public ImageSupplier(){
        animalImage = new Image(new File("./project/src/main/resources/img/guinea_pig.png").toURI().toString());
        plantImage = new Image(new File("./project/src/main/resources/img/lettuce.png").toURI().toString());
    }
    public ImageView getImageView(Object element, int fullEnergy, Animal trackedAnimal){
        if(element instanceof Animal){
            ImageView imageView = new ImageView(animalImage);
            imageView.setRotate(((Animal) element).getOrientation().toAngle());
            double opacity = Math.min(1.0,((Animal) element).getEnergy()/(double)fullEnergy);
            imageView.setOpacity(opacity);
            if(trackedAnimal != null && ((Animal) element).getPosition() == trackedAnimal.getPosition()){
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setHue(0.9);
                imageView.setEffect(colorAdjust);
            }
            return imageView;
        }
        else{
            ImageView imageView = new ImageView(plantImage);
            if(((Plant)element).isPoisonous()){
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setHue(0.9);
                imageView.setEffect(colorAdjust);
            }
            return imageView;
        }
    }
}
