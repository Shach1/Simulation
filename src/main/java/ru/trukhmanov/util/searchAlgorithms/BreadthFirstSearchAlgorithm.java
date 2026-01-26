package ru.trukhmanov.util.searchAlgorithms;

import ru.trukhmanov.core.Coordinates;
import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.util.searchAlgorithms.rules.EntitySearchRule;
import ru.trukhmanov.util.searchAlgorithms.rules.RuleForGrass;
import ru.trukhmanov.util.searchAlgorithms.rules.RuleForHerbivore;

import java.util.*;

/**
 * Самописная реализация алгоритма поиска в ширину (BFS)
 *  <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Подробнее об алгоритме</a>
 */
public class BreadthFirstSearchAlgorithm implements PathfindingAlgorithm{
    private final WorldMap worldMap;
    private Set<Coordinates> visitedCoordinates;
    private Map<Coordinates, Coordinates> childToParent;
    private Queue<Coordinates> availableEdges;

    @Override
    public List<Coordinates> findPathToNearestGrass(Coordinates startingPosition) {
        return findPathByRule(startingPosition, new RuleForGrass());
    }

    @Override
    public List<Coordinates> findPathToNearestHerbivore(Coordinates startingPosition) {
        return findPathByRule(startingPosition, new RuleForHerbivore());
    }

    public BreadthFirstSearchAlgorithm(WorldMap worldMap){
        this.worldMap = worldMap;
    }

    private void searchInitialization(Coordinates startingPosition){
        visitedCoordinates = new HashSet<>();
        childToParent = new HashMap<>();
        visitedCoordinates.add(startingPosition);
        childToParent.put(startingPosition, null);
        availableEdges = getAvailableEdges(startingPosition);
    }

    private List<Coordinates> findPathByRule(Coordinates startingPosition, EntitySearchRule entitySearchRule){
        searchInitialization(startingPosition);

        Iterator<Coordinates> availableEdgesIterator;
        Coordinates edge;
        while (!availableEdges.isEmpty()){
            availableEdgesIterator = availableEdges.iterator();
            while (availableEdgesIterator.hasNext()){
                edge = availableEdgesIterator.next();
                if (worldMap.isEmptyCell(edge)) continue;
                if (entitySearchRule.checkEntity(worldMap, edge)) return reconstructPath(startingPosition, edge);
                availableEdgesIterator.remove();
            }
            addAvailableEdgesFromCurrentEdges();
        }
        return Collections.emptyList();
    }

    private void addAvailableEdgesFromCurrentEdges() {
        int initialSize = availableEdges.size();
        for(int i = 0; i < initialSize; i++) {
            availableEdges.addAll(getAvailableEdges(availableEdges.remove()));
        }
    }

    private Queue<Coordinates> getAvailableEdges(Coordinates currentPosition) {
        Queue<Coordinates> result = new LinkedList<>();
        for (var edge : worldMap.getNeighboringCell(currentPosition)){
            if (isValidCoordinates(edge)){
                result.add(edge);
                visitedCoordinates.add(edge);
                childToParent.put(edge, currentPosition);
            }
        }
        return result;
    }

    private boolean isValidCoordinates(Coordinates coordinates){
        return !visitedCoordinates.contains(coordinates);
    }

    private List<Coordinates> reconstructPath(Coordinates startingPosition, Coordinates destination) {
        List<Coordinates> result = new LinkedList<>();
        result.add(destination);

        Coordinates parent = childToParent.get(destination);
        while(!parent.equals(startingPosition)){
            result.add(parent);
            parent = childToParent.get(parent);
        }
        return result.reversed();
    }
}