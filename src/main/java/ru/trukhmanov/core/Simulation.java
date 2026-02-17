package ru.trukhmanov.core;

import ru.trukhmanov.core.actions.ActionCommand;
import ru.trukhmanov.core.actions.init.FirstRenderMapAction;
import ru.trukhmanov.core.actions.init.PlaceAllEntitiesAction;
import ru.trukhmanov.core.actions.turn.MoveAndTryEatForAllCreaturesAction;
import ru.trukhmanov.core.actions.turn.RestoreStaminaForAllCreaturesAction;
import ru.trukhmanov.util.searchAlgorithms.BreadthFirstSearchAlgorithm;

import java.util.LinkedList;
import java.util.List;


/**
 * Главынй класс приожения, отвечает за симуляуию мира
 */
public class Simulation{
    private static final String NUMBER_OF_TURNS = """
            +----------------------+
            | number of turns: %3s |
            +----------------------+
            """;
    private static final String DIALOG_BOX = """
            +----------------------+
            | Enter 'p' to pause.  |
            | Enter 'u' to unpause.|
            +----------------------+
            """;
    private final List<ActionCommand> turnActions = new LinkedList<>();
    private final List<ActionCommand> initActions = new LinkedList<>();

    private int turnCounter = 0;
    private volatile boolean isUnpaused;

    public Simulation(){
        this(10, 10);
    }

    public Simulation(int height, int weight){
        WorldMap worldMap = new WorldMap(height, weight);

        initActions.add(new PlaceAllEntitiesAction(worldMap));
        initActions.add(new FirstRenderMapAction(worldMap));
        turnActions.add(new MoveAndTryEatForAllCreaturesAction(worldMap, new BreadthFirstSearchAlgorithm(worldMap)));
        turnActions.add(new RestoreStaminaForAllCreaturesAction(worldMap));
    }

    public void initSimulation(){
        initActions.forEach(ActionCommand::execute);
        new Thread(new Input(this)).start();
        startSimulation();
    }

    private void startSimulation(){
        System.out.println(DIALOG_BOX);
        unpauseSimulation();
        while(true){
            nextTurn();
        }
    }

    public void pauseSimulation(){
        isUnpaused = false;
    }

    public void unpauseSimulation(){
        isUnpaused = true;
    }

    private void nextTurn(){
        if(isUnpaused){
            turnActions.forEach(ActionCommand::execute);
            turnCounter++;
            System.out.printf(NUMBER_OF_TURNS, turnCounter);
        }
    }
}
