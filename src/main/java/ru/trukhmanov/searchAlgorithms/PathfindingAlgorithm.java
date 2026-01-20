package ru.trukhmanov.searchAlgorithms;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;

import java.util.List;

/**
 * Интерфейс для нахождения объекта определенного методом типа
 */
public interface PathfindingAlgorithm {
    List<Coordinates> findPathToNearestGrass(WorldMap worldMap, Coordinates startingPosition);
    List<Coordinates> findPathToNearestHerbivore(WorldMap worldMap, Coordinates startingPosition);
}
