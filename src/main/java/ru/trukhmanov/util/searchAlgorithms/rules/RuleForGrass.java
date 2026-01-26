package ru.trukhmanov.util.searchAlgorithms.rules;

import ru.trukhmanov.core.Coordinates;
import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.entities.Grass;

public class RuleForGrass implements EntitySearchRule{
    @Override
    public boolean checkEntity(WorldMap worldMap, Coordinates coordinates) {
        return worldMap.getEntityByCoordinates(coordinates) instanceof Grass;
    }
}
