package ru.trukhmanov;

import ru.trukhmanov.actions.ActionsCommand;
import ru.trukhmanov.actions.InitActions;
import ru.trukhmanov.actions.TurnActions;
import ru.trukhmanov.searchAlgorithms.BreadthFirstSearch;
import ru.trukhmanov.searchAlgorithms.PathfindingAlgorithm;

/**
 * Главынй класс приожения, отвечает за симуляуию мира
 */
public class Simulation {
    private WorldMap worldMap = new WorldMap(15, 15);
    private Renderer renderer = new Renderer(worldMap);
    private PathfindingAlgorithm pathfindingAlgorithm= new BreadthFirstSearch();
    private int turnCounter = 0;

    private final ActionsCommand initActions = new InitActions(worldMap);
    private final ActionsCommand testInitActions = new TestInitActions(worldMap);
    private final ActionsCommand turnActions = new TurnActions(worldMap);

    public void initSimulation(){
        initActions.execute();
        renderer.renderMap();
        startSimulation();
    }

    private void startSimulation(){
        // TODO: игровой цикл

    }

    private void pauseSimulation(){
        // TODO: остановкка бесконечного ццикла симуляции
    }

    private void nextTurn(){
        // TODO: симуляцуия одного хода в конце renderer.renderMap()
    }
}
