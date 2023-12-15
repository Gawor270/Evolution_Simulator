package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable{

    private final Simulation[] simulations;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    public SimulationEngine(Simulation[] simulations){
        this.simulations = simulations;
    }

    @Override
    public void run() {
//        runSync();
//        runAsync();
        runAsyncInThreadPool();
        awaitSimulationsEnd();
    }

    public void runSync() {
        for(Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() throws InterruptedException {
        List<Thread> threads = new ArrayList<>(simulations.length);
        for(Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads){
            thread.join();
        }
    }

    private void awaitSimulationsEnd() {
        try {
            executorService.shutdown(); // Initiate shutdown of the executor service
            executorService.awaitTermination(10, TimeUnit.SECONDS); // Wait for up to 10 seconds for tasks to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted while awaiting simulation completion: " + e.getMessage());
        } finally {
            if (!executorService.isTerminated()) {
                executorService.shutdownNow(); // Forcefully shutdown if tasks didn't complete in 10 seconds
            }
        }
    }

    private void runAsyncInThreadPool() {

        for(Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
    }
}
