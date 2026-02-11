package ru.trukhmanov.core;

import ru.trukhmanov.core.actions.ActionCommand;
import ru.trukhmanov.core.actions.InitAction;
import ru.trukhmanov.core.actions.MoveAndTryEatForAllCreaturesAction;
import ru.trukhmanov.core.actions.RestoreStaminaForAllCreaturesAction;
import ru.trukhmanov.util.Renderer;
import ru.trukhmanov.util.searchAlgorithms.BreadthFirstSearchAlgorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Главынй класс приожения, отвечает за симуляуию мира
 */
public class Simulation {
    private final WorldMap worldMap;
    private final Renderer renderer;
    private int turnCounter = 0;

    private ActionCommand initAction;
    private final List<ActionCommand> turnActions = new LinkedList<>();

    public Simulation() {
        this(10, 10);
    }

    public Simulation(int height, int weight) {
        this.worldMap = new WorldMap(height, weight);

        renderer = new Renderer(worldMap);
        initAction = new InitAction(worldMap);
        turnActions.add(new MoveAndTryEatForAllCreaturesAction(worldMap, new BreadthFirstSearchAlgorithm(worldMap)));
        turnActions.add(new RestoreStaminaForAllCreaturesAction(worldMap));
    }

    public void initSimulation() {
        initAction.execute();
        renderer.renderMap();
        startSimulation();
    }

    private void startSimulation() {
        System.out.printf("""
                +----------------------+
                | number of turns: %3s |
                +----------------------+
                """, turnCounter);
        for (var obj : turnActions) {
            obj.execute();
        }
        turnCounter++;
    }

    private void pauseSimulation() {
        // TODO: остановкка бесконечного ццикла симуляции
    }

    private void nextTurn() {
        // TODO: симуляцуия одного хода в конце renderer.renderMap()
    }
}
