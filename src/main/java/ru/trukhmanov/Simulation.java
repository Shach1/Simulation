package ru.trukhmanov;

import ru.trukhmanov.actions.ActionsCommand;
import ru.trukhmanov.actions.InitActions;
import ru.trukhmanov.actions.TestInitActions;
import ru.trukhmanov.actions.TurnActions;
import ru.trukhmanov.searchAlgorithms.BreadthFirstSearchAlgorithm;
import ru.trukhmanov.searchAlgorithms.PathfindingAlgorithm;

/**
 * Главынй класс приожения, отвечает за симуляуию мира
 */
public class Simulation {
    private WorldMap worldMap;
    private Renderer renderer;
    private PathfindingAlgorithm pathfindingAlgorithm;
    private int turnCounter = 0;

    private final ActionsCommand initActions;
    private final ActionsCommand turnActions;

    /** Конструктор для ручного тестирования симуляцию */
    public Simulation(boolean test){
        this.worldMap = new WorldMap(5, 5);
        this.pathfindingAlgorithm = new BreadthFirstSearchAlgorithm();

        renderer = new Renderer(worldMap);
        initActions = new TestInitActions(worldMap);
        turnActions = new TurnActions(worldMap);
    }

    public Simulation(WorldMap worldMap, PathfindingAlgorithm pathfindingAlgorithm){
        this.worldMap = worldMap;
        this.pathfindingAlgorithm = pathfindingAlgorithm;

        renderer = new Renderer(worldMap);
        initActions = new InitActions(worldMap);
        turnActions = new TurnActions(worldMap);
    }

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
