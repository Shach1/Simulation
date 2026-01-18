package ru.trukhmanov;


public class Main {
    public static void main(String[] args) {
        new Simulation(true).initSimulation();
        /*
        new Simulation(
                new WorldMap(15, 15),
                new BreadthFirstSearchAlgorithm()
        ).initSimulation();
         */
    }
}