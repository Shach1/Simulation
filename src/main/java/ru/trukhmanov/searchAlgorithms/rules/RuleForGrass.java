package ru.trukhmanov.searchAlgorithms.rules;

import ru.trukhmanov.Coordinates;
import ru.trukhmanov.WorldMap;
import ru.trukhmanov.entities.Grass;

public class RuleForGrass implements EntitySearchRule{
    @Override
    public boolean checkEntity(WorldMap worldMap, Coordinates coordinates) {
        return worldMap.getEntityByCoordinates(coordinates) instanceof Grass;
    }
}
