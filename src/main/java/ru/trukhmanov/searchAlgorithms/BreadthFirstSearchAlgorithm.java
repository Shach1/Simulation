package ru.trukhmanov.searchAlgorithms;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;
import ru.trukhmanov.searchAlgorithms.rules.EntitySearchRule;
import ru.trukhmanov.searchAlgorithms.rules.RuleForGrass;
import ru.trukhmanov.searchAlgorithms.rules.RuleForHerbivore;

import java.util.*;

/**
 * Самописная реализация алгоритма поиска в ширину (BFS)
 *  <a href="https://ru.wikipedia.org/wiki/Поиск_в_ширину">Подробнее об алгоритме</a>
 */
public class BreadthFirstSearchAlgorithm implements PathfindingAlgorithm{

    @Override
    public List<Coordinates> findPathToNearestGrass(WorldMap worldMap, Coordinates startingPosition) {
        return findPathByRule(worldMap, startingPosition, new RuleForGrass());
    }

    @Override
    public List<Coordinates> findPathToNearestHerbivore(WorldMap worldMap, Coordinates startingPosition) {
        return findPathByRule(worldMap, startingPosition, new RuleForHerbivore());
    }

    private List<Coordinates> findPathByRule(WorldMap worldMap, Coordinates startingPosition, EntitySearchRule entitySearchRule){
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Map<Coordinates, Coordinates> childToParent = new HashMap<>();
        Queue<Coordinates> availableEdges;

        visitedCoordinates.add(startingPosition);
        childToParent.put(startingPosition, null);
        availableEdges = getAvailableEdges(worldMap, startingPosition, visitedCoordinates, childToParent);

        Iterator<Coordinates> availableEdgesIterator;
        Coordinates edge;
        while (!availableEdges.isEmpty()){
            availableEdgesIterator = availableEdges.iterator();
            while (availableEdgesIterator.hasNext()){
                edge = availableEdgesIterator.next();
                if (worldMap.isEmptyCell(edge)) continue;
                if (entitySearchRule.checkEntity(worldMap, edge)) return reconstructPath(startingPosition, edge, childToParent);
                availableEdgesIterator.remove();
            }
            addAvailableEdgesFromCurrentEdges(worldMap, availableEdges, visitedCoordinates, childToParent);
        }
        return Collections.emptyList();
    }

    private void addAvailableEdgesFromCurrentEdges(
            WorldMap worldMap, 
            Queue<Coordinates> availableEdges, 
            Set<Coordinates> visitedCoordinates, 
            Map<Coordinates, Coordinates> childToParent
    ) {
        int initialSize = availableEdges.size();
        for(int i = 0; i < initialSize; i++) {
            availableEdges.addAll(getAvailableEdges(
                    worldMap,
                    availableEdges.remove(),
                    visitedCoordinates,
                    childToParent
            ));
        }
    }

    private Queue<Coordinates> getAvailableEdges(
            WorldMap worldMap,
            Coordinates currentPosition,
            Set<Coordinates> visitedCoordinates,
            Map<Coordinates, Coordinates> childToParent
    ) {
        Coordinates edge1 = new Coordinates(currentPosition.x() + 1, currentPosition.y());
        Coordinates edge2 = new Coordinates(currentPosition.x() - 1, currentPosition.y());
        Coordinates edge3 = new Coordinates(currentPosition.x(), currentPosition.y() + 1);
        Coordinates edge4 = new Coordinates(currentPosition.x(), currentPosition.y() - 1);

        Queue<Coordinates> result = new LinkedList<>();
        for (var edge : Arrays.asList(edge1, edge2, edge3, edge4)){
            if (isValidCoordinates(worldMap, edge, visitedCoordinates)){
                result.add(edge);
                visitedCoordinates.add(edge);
                childToParent.put(edge, currentPosition);
            }
        }
        return result;
    }

    private boolean isValidCoordinates(WorldMap worldMap, Coordinates coordinates, Set<Coordinates> visitedCoordinates ){
        if (!worldMap.isCoordinatesInWorldMapBoundaries(coordinates)) return false;
        return !visitedCoordinates.contains(coordinates);
    }

    private List<Coordinates> reconstructPath(
            Coordinates startingPosition,
            Coordinates goal,
            Map<Coordinates, Coordinates> childToParent
    ) {
        List<Coordinates> result = new ArrayList<>();
        result.add(goal);

        Coordinates parent = childToParent.get(goal);
        while(!parent.equals(startingPosition)){
            result.add(parent);
            parent = childToParent.get(parent);
        }
        Collections.reverse(result);
        return result;
    }
}