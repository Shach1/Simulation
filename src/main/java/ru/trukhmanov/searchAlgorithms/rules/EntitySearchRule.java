package ru.trukhmanov.searchAlgorithms.rules;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;

public interface EntitySearchRule {
    boolean checkEntity(WorldMap worldMap, Coordinates coordinates);
}
