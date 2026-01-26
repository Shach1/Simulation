package ru.trukhmanov.util.searchAlgorithms;

import ru.trukhmanov.core.Coordinates;

import java.util.List;

/**
 * Интерфейс для нахождения пути до объекта
 */
public interface PathfindingAlgorithm {
    List<Coordinates> findPathToNearestGrass(Coordinates startingPosition);
    List<Coordinates> findPathToNearestHerbivore(Coordinates startingPosition);
}
