package ru.trukhmanov.util.searchAlgorithms.rules;

import ru.trukhmanov.core.Coordinates;
import ru.trukhmanov.core.WorldMap;

public interface EntitySearchRule {
    boolean checkEntity(WorldMap worldMap, Coordinates coordinates);
}
