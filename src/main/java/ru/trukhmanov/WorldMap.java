package ru.trukhmanov;

import ru.trukhmanov.entities.Entity;

import java.util.HashMap;

/**
 * Карта, содержит в себе коллекцию для хранения существ и их расположения
 */
public class WorldMap {
    private final java.util.Map<Coordinates, Entity> entitysMap = new HashMap<>();
    public final int height;
    public final int weight;

    public WorldMap(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public boolean setEntityOnMap(Coordinates coordinates, Entity entity){
        if (entitysMap.containsKey(coordinates)) return false;
        if (coordinates.x() > weight || coordinates.y() > height) return false;

        entitysMap.put(coordinates, entity);
        entity.setCoordinates(coordinates);
        return true;
    }

    public void removeEntityFromMap(Coordinates coordinates){
        entitysMap.remove(coordinates);
    }

    public boolean isEmptyCell(Coordinates coordinates){
        return !entitysMap.containsKey(coordinates);
    }

    public Entity getEntityByCoordinates(Coordinates coordinates){
        return entitysMap.get(coordinates);
    }

    public int size(){
        return height * weight;
    }
}
