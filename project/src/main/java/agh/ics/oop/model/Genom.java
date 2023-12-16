package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Genom {
    private ArrayList<Integer> genom = new ArrayList<>();
    private int n;

    private int currentIndex = 0;

    public void setGenom(int n) {
        this.n = n;
        Random random = new Random();
        for (int i=0; i<n; i++) {
            this.genom.add(random.nextInt(8));
        }
    }

    public void setGenom(int n, ArrayList<Integer> genom){
        this.n = n;
        this.genom = genom;
    }

    public ArrayList<Integer> getGenom() {
        return genom;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public void mutate(int a){
        Random random = new Random();
        int b = (random.nextInt(7) + a) % this.n;
        this.genom.set(a, b);
    }


    public ArrayList<Integer> cross(Genom parentA, Genom parentB, int energyA, int energyB){
        ArrayList<Integer> childGenom = new ArrayList<>();
        int gensFromParentA = (energyA / energyB) * this.n;
        int gensFromParentB = this.n - gensFromParentA;

        for (int i=0; i<gensFromParentA; i++){
                childGenom.add(parentA.getGenom().get(i));
        }
        for (int i=0; i<gensFromParentB; i++){
            childGenom.add(parentB.getGenom().get(i));
        }
        Collections.shuffle(childGenom);
        return childGenom;
    }

    public void nextGen(){
        Random random = new Random();
        int a = random.nextInt(5);

        if (a == 0){
            this.currentIndex = (this.currentIndex + random.nextInt(7)) % n;
        }else{
            this.currentIndex++;
        }
    }


}
