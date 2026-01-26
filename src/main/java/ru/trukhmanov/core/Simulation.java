package ru.trukhmanov.core;

import ru.trukhmanov.core.actions.*;
import ru.trukhmanov.util.Renderer;
import ru.trukhmanov.util.searchAlgorithms.BreadthFirstSearchAlgorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Главынй класс приожения, отвечает за симуляуию мира
 */
public class Simulation {
    private WorldMap worldMap;
    private Renderer renderer;
    private int turnCounter = 0;

    private final ActionCommand initAction;
    private final List<ActionCommand> turnActions = new LinkedList<>();

    /** Конструктор для ручного тестирования симуляции */
    public Simulation(boolean test){
        this.worldMap = new WorldMap(5, 5);

        renderer = new Renderer(worldMap);
        initAction = new TestInitAction(worldMap);

        turnActions.add(new MoveAndTryEatForAllCreaturesAction(worldMap, new BreadthFirstSearchAlgorithm(worldMap)));
        turnActions.add(new RestoreStaminaForAllCreaturesAction(worldMap));
    }

    public Simulation(WorldMap worldMap){
        this.worldMap = worldMap;

        renderer = new Renderer(worldMap);
        initAction = new InitAction(worldMap);
    }

    public void initSimulation(){
        initAction.execute();
        renderer.renderMap();
        startSimulation();
    }

    private void startSimulation(){
        // TODO: игровой цикл
        for(var obj : turnActions) {
            obj.execute();
        }
    }

    private void pauseSimulation(){
        // TODO: остановкка бесконечного ццикла симуляции
    }

    private void nextTurn(){
        // TODO: симуляцуия одного хода в конце renderer.renderMap()
    }
}
