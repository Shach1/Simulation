package ru.trukhmanov.core;

import java.util.Scanner;

public class Input implements Runnable{
    private final Scanner scanner = new Scanner(System.in);
    private final Simulation simulation;

    public Input(Simulation simulation){
        this.simulation = simulation;
    }

    @Override
    public void run(){
        input();
    }

    public void input(){
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("p")){
                System.out.println("Pause the simulation after the turn ends.");
                simulation.pauseSimulation();
            } else if(line.equals("u")){
                System.out.println("The simulation has been unpaused.");
                simulation.unpauseSimulation();
            } else System.out.println("wrong symbol");
        }
    }
}