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
    public Optional<Coordinates> searchGrassCoordinates(WorldMap worldMap, Coordinates startingPosition) {
        return searchEntityCoordinatesByRule(worldMap, startingPosition, new RuleForGrass());
    }

    @Override
    public Optional<Coordinates> searchHerbivoreCoordinates(WorldMap worldMap, Coordinates startingPosition) {
        return searchEntityCoordinatesByRule(worldMap, startingPosition, new RuleForHerbivore());
    }

    private Optional<Coordinates> searchEntityCoordinatesByRule(WorldMap worldMap, Coordinates startingPosition, EntitySearchRule entitySearchRule){
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(startingPosition);
        Queue<Coordinates> availableEdges = new LinkedList<>(getAvailableEdges(
                worldMap,
                startingPosition,
                visitedCoordinates
        ));
        Iterator<Coordinates> availableEdgesIterator;
        Coordinates edge;
        while (!availableEdges.isEmpty()){
            availableEdgesIterator = availableEdges.iterator();
            while (availableEdgesIterator.hasNext()){
                edge = availableEdgesIterator.next();
                if (worldMap.isEmptyCell(edge)) continue;
                if (!(entitySearchRule.checkEntity(worldMap, edge))){
                    availableEdgesIterator.remove();
                    continue;
                }
                return Optional.of(edge);
            }

            int iterativeSize = availableEdges.size();
            for(int i = 0; i < iterativeSize; i++) {
                availableEdges.addAll(getAvailableEdges(
                        worldMap,
                        availableEdges.remove(),
                        visitedCoordinates
                ));
            }
        }
        return Optional.empty();
    }

    private Queue<Coordinates> getAvailableEdges(WorldMap worldMap, Coordinates startingPosition, Set<Coordinates> visitedCoordinates){
        Coordinates edge1 = new Coordinates(startingPosition.x() + 1, startingPosition.y());
        Coordinates edge2 = new Coordinates(startingPosition.x() - 1, startingPosition.y());
        Coordinates edge3 = new Coordinates(startingPosition.x(), startingPosition.y() + 1);
        Coordinates edge4 = new Coordinates(startingPosition.x(), startingPosition.y() - 1);

        Queue<Coordinates> result = new LinkedList<>();
        for (var edge : Arrays.asList(edge1, edge2, edge3, edge4)){
            if (isValidCoordinates(worldMap, edge, visitedCoordinates)){
                result.add(edge);
                visitedCoordinates.add(edge);
            }
        }
        return result;
    }

    private boolean isValidCoordinates(WorldMap worldMap, Coordinates coordinates, Set<Coordinates> visitedCoordinates ){
        if (!worldMap.isCoordinatesInWorldMapBoundaries(coordinates)) return false;
        return !visitedCoordinates.contains(coordinates);
    }
}