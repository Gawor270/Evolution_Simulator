package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Genome {
    private ArrayList<Integer> genome = new ArrayList<>();
    private final int length;
    private int currentIndex = 0;

    public Genome(int n) {
        this.length = n;
        Random random = new Random();
        for (int i=0; i<n; i++) {
            this.genome.add(random.nextInt(8));
        }
    }

    public Genome(int n, ArrayList<Integer> genome) {
        this.length = n;
        this.genome = genome;
    }

    public ArrayList<Integer> getGenome() {
        return genome;
    }

    public int getCurrentIndex() { return currentIndex; }


    public void mutate(int a){
        Random random = new Random();
        int b = (random.nextInt(7) + a) % this.length;
        this.genome.set(a, b);
    }


    public Genome cross( Genome parentB, int energyA, int energyB){
        ArrayList<Integer> childGenome = new ArrayList<>();
        int gensFromParentA = (energyA / (energyA + energyB)) * this.length;
        int gensFromParentB = this.length - gensFromParentA;

        for (int i=0; i<gensFromParentA; i++){
                childGenome.add(this.genome.get(i));
        }
        for (int i=length - gensFromParentB; i<length; i++){
            childGenome.add(parentB.getGenome().get(i));
        }
        Collections.shuffle(childGenome);
        return new Genome(this.length, childGenome);
    }

    public int nextGen(){
        Random random = new Random();
        int a = random.nextInt(5);

        if (a == 0){
            this.currentIndex = (this.currentIndex + random.nextInt(7)) % length;
        }else{
            this.currentIndex++;
        }
        return this.genome.get(this.currentIndex);
    }


}
