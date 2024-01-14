package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SimulationStatistics {

    private Simulation simulation;

    private int animalsCount;
    private int plantsCount;
    private int freeSpaceCount;
    HashMap<Genome, Integer> genomesCount = new HashMap<>();

    private float totalEnergy;
    private float avgLifespan;
    private float totalAge;
    private float totalDeaths;
    private float totalChildrenCount;

    private String fileName;



    public SimulationStatistics(Simulation simulation) {
        this.simulation = simulation;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        fileName = "statisticslog" + dateFormat.format(new Date()) + ".csv";
    }

    public void increaseAnimalsCount(){
        animalsCount++;
    }

    public void decreaseAnimalsCount(){
        animalsCount--;
    }

    public void updateAvgLifespan(Animal animal){
        totalAge += animal.getStatistics().getAge();
        totalDeaths++;
        avgLifespan = totalAge / totalDeaths;
    }

    public void increasePlantsCount(int x){
        plantsCount+=x;
    }

    public void decreasePlantsCount(){
        plantsCount--;
    }

    public int getAnimalsCount() {
        return animalsCount;
    }

    public int getPlantsCount() {
        return plantsCount;
    }


    public void setFreeSpaceCount(int x){
        freeSpaceCount = x;
    }

    public void updateTotalEnergy(float energy){
        totalEnergy += energy;
    }
    public void updateTotalChildrenCount(float childrenCount){
        totalChildrenCount += childrenCount;
    }
    public float getAvgChildrenCount(){
        return totalChildrenCount / totalDeaths;
    }

    public void updateGenomesCount(Genome genome){
        if(genomesCount.containsKey(genome)){
            genomesCount.put(genome, genomesCount.get(genome) + 1);
        }
        else{
            genomesCount.put(genome, 1);
        }
    }

    public Genome getMostCommonGenome(){
        int max = 0;
        Genome mostCommon = null;
        for(Genome genome : genomesCount.keySet()){
            if(genomesCount.get(genome) > max){
                max = genomesCount.get(genome);
                mostCommon = genome;
            }
        }
        return mostCommon;
    }

    public float getAvgEnergy(){
        return totalEnergy / animalsCount;
    }

    public int getFreeSpaceCount() {
        return freeSpaceCount;
    }

    public float getAvgLifespan(){
        return avgLifespan;
    }

    public void addHeader(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./project/src/main/java/logs/" + fileName, true))){
            writer.write("Day,Animals,Plants,Free space,Avg lifespan,Avg energy,Avg children count,Most common genome\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void saveToCsv(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./project/src/main/java/logs/" + fileName, true))){
            writer.write(simulation.getDay() + "," + getAnimalsCount() + "," + getPlantsCount() + "," + getFreeSpaceCount() + "," +
                    getAvgLifespan() + "," + getAvgEnergy() + "," + getAvgChildrenCount() + "," + getMostCommonGenome() + "\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
