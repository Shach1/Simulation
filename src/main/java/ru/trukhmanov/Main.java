package ru.trukhmanov;


import ru.trukhmanov.core.Simulation;

public class Main {
    public static void main(String[] args) {
        new Simulation(true).initSimulation();

//        var worldMap = new WorldMap(15, 15);
//        new Simulation(worldMap, new BreadthFirstSearchAlgorithm(worldMap)).initSimulation();
    }
}