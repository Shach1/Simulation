package ru.trukhmanov.actions;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.Renderer;
import ru.trukhmanov.WorldMap;
import ru.trukhmanov.entities.Creature;
import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Herbivore;
import ru.trukhmanov.entities.Predator;
import ru.trukhmanov.searchAlgorithms.PathfindingAlgorithm;

import java.util.List;

public class MoveAndTryEatForAllCreaturesAction extends ActionCommand{

    public static final int REACHABLE_DISTANCE = 1;
    private final Renderer renderer;
    private final PathfindingAlgorithm pathfindingAlgorithm;
    private List<Coordinates> curPathToNearestGoal;
    private Coordinates curCoordinates;
    private Creature curCreature;

    public MoveAndTryEatForAllCreaturesAction(WorldMap worldMap, Renderer renderer, PathfindingAlgorithm pathfindingAlgorithm) {
        super(worldMap);
        this.renderer = renderer;
        this.pathfindingAlgorithm = pathfindingAlgorithm;
    }

    @Override
    public void execute() {
        for(Coordinates coordinates : worldMap.getCoordinatesOfCreatures()) {
            curCoordinates = coordinates;
            curCreature = (Creature) worldMap.getEntityByCoordinates(coordinates);
            curPathToNearestGoal = getPathToNearestGoal();
            moveCreatureFromCoordinatesToGoal();
            getTryEatGoal();
            renderer.renderMap();
        }
    }

    private void getTryEatGoal() {
        if (curPathToNearestGoal.size() == REACHABLE_DISTANCE){
            Coordinates goalCoordinates = curPathToNearestGoal.getFirst();
            Entity goal = worldMap.getEntityByCoordinates(goalCoordinates);
            if (curCreature.tryEatEntity(goal)) worldMap.removeEntityFromMap(goalCoordinates);
        }
    }

    private void moveCreatureFromCoordinatesToGoal(){
        if (curPathToNearestGoal.isEmpty()) return;
        while (curPathToNearestGoal.size() != REACHABLE_DISTANCE){
            if (!curCreature.canMove()) return;
            curCoordinates = worldMap.moveCreatureFromCoordinateToCoordinate(curCoordinates, curPathToNearestGoal.removeFirst());
            curCreature.makeMove();
        }
    }

    private List<Coordinates> getPathToNearestGoal(){
        if (curCreature instanceof Predator){
            return pathfindingAlgorithm.findPathToNearestHerbivore(curCoordinates);
        }else if (curCreature instanceof Herbivore){
            return pathfindingAlgorithm.findPathToNearestGrass(curCoordinates);
        }
        else throw new IllegalArgumentException("Undefined creature type. creature = " + curCreature.getClass().getSimpleName());
    }
}