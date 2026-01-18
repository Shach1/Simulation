package ru.trukhmanov.searchAlgorithms;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;
import ru.trukhmanov.entities.Grass;
import ru.trukhmanov.entities.Herbivore;

import java.util.*;

/**
 * Самописная реализация алгоритма BFS
 *  <a href="https://ru.wikipedia.org/wiki/Поиск_в_ширину">Подробнее об алгоритме</a>
 * @see PathfindingAlgorithm
 */
public class BreadthFirstSearch implements PathfindingAlgorithm{

    @Override
    public Optional<Coordinates> searchGrassCoordinates(WorldMap worldMap, Coordinates startingPosition) {
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
                if (worldMap.isEmptyCell(edge))continue;
                if (!(worldMap.getEntityByCoordinates(edge) instanceof Grass)){
                    availableEdgesIterator.remove();
                    continue;
                }
                return Optional.of(edge);
            }

            List<Coordinates> listForIteration = new LinkedList<>(availableEdges);
            for(Coordinates emptyCeil : listForIteration) {
                availableEdges.addAll(getAvailableEdges(
                        worldMap,
                        emptyCeil,
                        visitedCoordinates
                ));
                availableEdges.remove();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Coordinates> searchHerbivoreCoordinates(WorldMap worldMap, Coordinates startingPosition) {
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
                if (worldMap.isEmptyCell(edge))continue;
                if (!(worldMap.getEntityByCoordinates(edge) instanceof Herbivore)){
                    availableEdgesIterator.remove();
                    continue;
                }
                return Optional.of(edge);
            }

            List<Coordinates> listForIteration = new LinkedList<>(availableEdges);
            for(Coordinates emptyCeil : listForIteration) {
                availableEdges.addAll(getAvailableEdges(
                        worldMap,
                        emptyCeil,
                        visitedCoordinates
                ));
                availableEdges.remove();
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