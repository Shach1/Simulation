package ru.trukhmanov.core;

import ru.trukhmanov.entities.Creature;
import ru.trukhmanov.entities.Entity;

import java.util.*;

/**
 * Карта, содержит в себе коллекцию для хранения существ и их расположения
 */
public class WorldMap {
    public static final int LOWER_LIMIT = 7;
    public static final int UPPER_LIMIT = 25;
    private final Map<Coordinates, Entity> entities = new HashMap<>();
    public static final int MIN_HEIGHT = 1;
    public static final int MIN_WIDTH = 1;
    public final int maxHeight;
    public final int maxWidth;

    public WorldMap(int maxHeight, int maxWidth) {
        if (maxHeight < LOWER_LIMIT || maxWidth < LOWER_LIMIT){
            throw new IllegalArgumentException(String.format("maxHeight and maxWidth can't less then %d. maximumHeight = %d. maxWidth = %d.", LOWER_LIMIT, maxHeight, maxWidth));
        }
        if (maxHeight > UPPER_LIMIT || maxWidth > UPPER_LIMIT){
            throw new IllegalArgumentException(String.format("maxHeight and maxWidth can't more then %d. maximumHeight = %d. maxWidth = %d.", UPPER_LIMIT, maxHeight, maxWidth));
        }
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public boolean setEntityOnMap(Coordinates coordinates, Entity entity){
        if (entities.containsKey(coordinates)) return false;
        if (!isCoordinatesInWorldMapBoundaries(coordinates)) return false;

        entities.put(coordinates, entity);
        return true;
    }

    public boolean isCoordinatesInWorldMapBoundaries(Coordinates coordinates){
        if (MIN_HEIGHT > coordinates.y() || coordinates.y() > maxHeight) return false;
        if (MIN_WIDTH > coordinates.x() || coordinates.x() > maxWidth) return false;
        return true;
    }

    public void removeEntityFromMap(Coordinates coordinates){
        entities.remove(coordinates);
    }

    public boolean isEmptyCell(Coordinates coordinates){
        return !entities.containsKey(coordinates);
    }

    public Entity getEntityByCoordinates(Coordinates coordinates){
        Entity result = entities.get(coordinates);
        if (result == null) throw new RuntimeException("This cell is empty. Coordinates = " + coordinates);
        return result;
    }

    public int size(){
        return maxHeight * maxWidth;
    }

    public Set<Coordinates> getCoordinatesOfCreatures() {
        Set<Coordinates> creatures = new HashSet<>();
        for(var key : entities.keySet()) {
            if (entityIsCreature(entities.get(key))) creatures.add(key);
        }
        return creatures;
    }

    private boolean entityIsCreature(Entity entity){
        return entity instanceof Creature;
    }

    public List<Coordinates> getNeighboringCell(Coordinates currentPosition){
        List<Coordinates> result = new LinkedList<>();
        for (var cell : getUncheckedNeighboringCell(currentPosition)){
            if (isCoordinatesInWorldMapBoundaries(cell)) result.add(cell);
        }
        return result;
    }

    private List<Coordinates> getUncheckedNeighboringCell(Coordinates currentPosition){
        Coordinates cell1 = new Coordinates(currentPosition.x() + 1, currentPosition.y());
        Coordinates cell2 = new Coordinates(currentPosition.x() - 1, currentPosition.y());
        Coordinates cell3 = new Coordinates(currentPosition.x(), currentPosition.y() + 1);
        Coordinates cell4 = new Coordinates(currentPosition.x(), currentPosition.y() - 1);

        return Arrays.asList(cell1, cell2, cell3, cell4);
    }

    public Coordinates moveCreatureFromCellToOtherCell(Coordinates from, Coordinates to){
        if (!entityIsCreature(getEntityByCoordinates(from))) throw new IllegalArgumentException("Static entity cannot move. Coordinates = " + from);
        if (!isEmptyCell(to)) throw new IllegalArgumentException("Destination coordinates must be empty. Coordinates = " + to);
        if (!getNeighboringCell(from).contains(to)) throw new IllegalArgumentException("Destination coordinates must be a neighboring cell. Coordinates = " + to);

        Entity entity = entities.remove(from);
        entities.put(to, entity);
        return to;
    }
}
