package ru.trukhmanov.searchAlgorithms;

import ru.trukhmanov.Coordinates;

import java.util.List;

/**
 * Интерфейс для нахождения объекта определенного методом типа
 */
public interface PathfindingAlgorithm {
    List<Coordinates> findPathToNearestGrass(Coordinates startingPosition);
    List<Coordinates> findPathToNearestHerbivore(Coordinates startingPosition);
}
