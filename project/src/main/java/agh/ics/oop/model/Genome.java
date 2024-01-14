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


    public Genome cross( Genome parentB, int energyA, int energyB, int minMutations, int maxMutations){
        ArrayList<Integer> childGenome = new ArrayList<>();
        int gensFromParentA = (energyA / (energyA + energyB)) * genome.size();
        int gensFromParentB = genome.size() - gensFromParentA;

        for (int i=0; i<gensFromParentA; i++){
                childGenome.add(this.genome.get(i));
        }
        for (int i=genome.size() - gensFromParentB; i<genome.size(); i++){
            childGenome.add(parentB.getGenome().get(i));
        }
        Genome result = new Genome(childGenome);
        Random random = new Random();
        int mutations = random.nextInt(maxMutations - minMutations) + minMutations;
        for (int i=0; i<mutations; i++){
            result.mutate(random.nextInt(genome.size()));
        }
        return result;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setIndex(int index) {
        currentIndex = index;
    }

    public int nextGen(){
        currentIndex = (currentIndex + 1) % this.genome.size();
        return this.genome.get((currentIndex - 1 + this.genome.size()) % this.genome.size());
    }

    @Override
    public String toString() {
        return genome.toString();
    }

    @Override
    public int hashCode() {
        return genome.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Genome)) return false;
        return this.genome.equals(((Genome) obj).getGenome());
    }
}
