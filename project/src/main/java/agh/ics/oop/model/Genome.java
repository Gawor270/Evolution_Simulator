package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Genome {
    private ArrayList<Integer> genome = new ArrayList<>();
    private int currentIndex;

    public Genome(int n) {
        Random random = new Random();
        currentIndex = random.nextInt(n);
        for (int i=0; i<n; i++) {
            this.genome.add(random.nextInt(8));
        }
    }

    public Genome(ArrayList<Integer> genome) {
        this.genome = genome;
    }

    public ArrayList<Integer> getGenome() {
        return genome;
    }


    public void mutate(int a){
        Random random = new Random();
        int b = (random.nextInt(7) + a) % 8;
        this.genome.set(a, b);
    }


    public Genome cross( Genome parentB, int energyA, int energyB){
        ArrayList<Integer> childGenome = new ArrayList<>();
        int gensFromParentA = (energyA / (energyA + energyB)) * genome.size();
        int gensFromParentB = genome.size() - gensFromParentA;

        for (int i=0; i<gensFromParentA; i++){
                childGenome.add(this.genome.get(i));
        }
        for (int i=genome.size() - gensFromParentB; i<genome.size(); i++){
            childGenome.add(parentB.getGenome().get(i));
        }
        Collections.shuffle(childGenome);
        return new Genome(childGenome);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int nextGen(){
        Random random = new Random();
        int a = random.nextInt(5);

        if (a == 0){
            this.currentIndex = (this.currentIndex + random.nextInt(7)) % genome.size();
        }else{
            this.currentIndex = (this.currentIndex + 1) % genome.size();
        }
        return this.genome.get(this.currentIndex);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Genome)) return false;
        return this.genome.equals(((Genome) obj).getGenome());
    }
}
