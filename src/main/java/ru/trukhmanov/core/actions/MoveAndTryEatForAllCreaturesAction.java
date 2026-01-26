package ru.trukhmanov.core.actions;

import ru.trukhmanov.core.Coordinates;
import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.entities.Creature;
import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Herbivore;
import ru.trukhmanov.entities.Predator;
import ru.trukhmanov.util.Renderer;
import ru.trukhmanov.util.searchAlgorithms.PathfindingAlgorithm;

import java.util.List;

public class MoveAndTryEatForAllCreaturesAction extends ActionCommand{

    private static final int REACHABLE_DISTANCE = 1;
    private static final long RENDER_DELAY = 2300;
    private final Renderer renderer;
    private final PathfindingAlgorithm pathfindingAlgorithm;
    private Coordinates curCoordinates;
    private Creature curCreature;
    private List<Coordinates> curPathToNearestGoal;


    public MoveAndTryEatForAllCreaturesAction(WorldMap worldMap, PathfindingAlgorithm pathfindingAlgorithm) {
        super(worldMap);
        this.renderer = new Renderer(worldMap);
        this.pathfindingAlgorithm = pathfindingAlgorithm;
    }

    @Override
    public void execute() {
        for(Coordinates coordinates : worldMap.getCoordinatesOfCreatures()) {
            initForCurrentCreature(coordinates);
            moveCreatureFromCoordinatesToGoal();
            tryEatGoal();
            renderer.renderMap(RENDER_DELAY);
        }
    }

    private void initForCurrentCreature(Coordinates coordinates) {
        if (worldMap.isEmptyCell(coordinates)) return;
        curCoordinates = coordinates;
        curCreature = (Creature) worldMap.getEntityByCoordinates(coordinates);
        curPathToNearestGoal = getPathToNearestGoal();
    }

    private List<Coordinates> getPathToNearestGoal(){
        if (curCreature instanceof Predator){
            return pathfindingAlgorithm.findPathToNearestHerbivore(curCoordinates);
        }else if (curCreature instanceof Herbivore){
            return pathfindingAlgorithm.findPathToNearestGrass(curCoordinates);
        }
        else throw new IllegalArgumentException("Undefined creature type. creature = " + curCreature.getClass().getSimpleName());
    }

    private void moveCreatureFromCoordinatesToGoal(){
        if (curPathToNearestGoal.isEmpty()) return;
        while (curPathToNearestGoal.size() != REACHABLE_DISTANCE){
            if (!curCreature.canMove()) return;
            curCoordinates = worldMap.moveCreatureFromCoordinateToCoordinate(curCoordinates, curPathToNearestGoal.removeFirst());
            curCreature.makeMove();
        }
    }

    private void tryEatGoal() {
        if (curPathToNearestGoal.size() == REACHABLE_DISTANCE){
            Coordinates goalCoordinates = curPathToNearestGoal.getFirst();
            Entity goal = worldMap.getEntityByCoordinates(goalCoordinates);
            if (curCreature.tryEatEntity(goal)) worldMap.removeEntityFromMap(goalCoordinates);
        }
    }
}