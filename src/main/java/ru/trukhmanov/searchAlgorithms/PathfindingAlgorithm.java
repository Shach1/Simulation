package ru.trukhmanov.searchAlgorithms;

import ru.trukhmanov.Coordinates;

import java.util.List;

/**
 * Интерфейс для нахождения пути до объекта
 */
public interface PathfindingAlgorithm {
    List<Coordinates> findPathToNearestGrass(Coordinates startingPosition);
    List<Coordinates> findPathToNearestHerbivore(Coordinates startingPosition);
}
