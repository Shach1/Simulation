package ru.trukhmanov;

import ru.trukhmanov.entities.Creature;
import ru.trukhmanov.entities.Entity;

import java.util.HashMap;

/**
 * Карта, содержит в себе коллекцию для хранения существ и их расположения
 */
public class WorldMap {
    private final java.util.Map<Coordinates, Entity> entitysMap = new HashMap<>();
    public final int minimumHeight;
    public final int maximumHeight;
    public final int minimumWeight;
    public final int maximumWeight;

    public WorldMap(int maximumHeight, int maximumWeight) {
        this.minimumWeight = 1;
        this.minimumHeight = 1;
        this.maximumHeight = maximumHeight;
        this.maximumWeight = maximumWeight;
    }

    public boolean setEntityOnMap(Coordinates coordinates, Entity entity){
        if (entitysMap.containsKey(coordinates)) return false;
        if (coordinates.x() > maximumWeight || coordinates.y() > maximumHeight) return false;

        entitysMap.put(coordinates, entity);
        if (entity instanceof Creature creature) creature.setCoordinates(coordinates);
        return true;
    }

    public boolean isCoordinatesInWorldMapBoundaries(Coordinates coordinates){
        if (coordinates.y() < minimumHeight || coordinates.y() > maximumHeight) return false;
        if (coordinates.x() < minimumWeight || coordinates.x() > maximumHeight) return false;
        return true;
    }

    public void removeEntityFromMap(Coordinates coordinates){
        entitysMap.remove(coordinates);
    }

    public boolean isEmptyCell(Coordinates coordinates){
        return !entitysMap.containsKey(coordinates);
    }

    public Entity getEntityByCoordinates(Coordinates coordinates){
        Entity result = entitysMap.get(coordinates);
        if (result == null) throw new RuntimeException("This ceil is empty.");
        return result;
    }

    public int size(){
        return maximumHeight * maximumWeight;
    }
}
