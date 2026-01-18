package ru.trukhmanov.searchAlgorithms;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;

import java.util.Optional;

/**
 * Интерфейс для нахождения объекта определенного методом типа
 */
public interface PathfindingAlgorithm {
    public Optional<Coordinates> searchGrassCoordinates(WorldMap worldMap, Coordinates startingPosition);
    public Optional<Coordinates> searchHerbivoreCoordinates(WorldMap worldMap, Coordinates startingPosition);
}
