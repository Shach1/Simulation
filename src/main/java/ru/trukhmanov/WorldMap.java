package ru.trukhmanov;

import ru.trukhmanov.entities.Creature;
import ru.trukhmanov.entities.Entity;

import java.util.*;

/**
 * Карта, содержит в себе коллекцию для хранения существ и их расположения
 */
public class WorldMap {
    private final Map<Coordinates, Entity> entities = new HashMap<>();
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
        if (entities.containsKey(coordinates)) return false;
        if (!isCoordinatesInWorldMapBoundaries(coordinates)) return false;

        entities.put(coordinates, entity);
        return true;
    }

    public boolean isCoordinatesInWorldMapBoundaries(Coordinates coordinates){
        if (minimumHeight > coordinates.y() || coordinates.y() > maximumHeight) return false;
        if (minimumWeight > coordinates.x() || coordinates.x() > maximumWeight) return false;
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
        return maximumHeight * maximumWeight;
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

    public Coordinates moveCreatureFromCoordinateToCoordinate(Coordinates from, Coordinates to){
        if (!entityIsCreature(getEntityByCoordinates(from))) throw new IllegalArgumentException("Static entity cannot move. Coordinates = " + from);
        if (!isEmptyCell(to)) throw new IllegalArgumentException("Destination coordinates must be empty. Coordinates = " + to);
        if (!getNeighboringCell(from).contains(to)) throw new IllegalArgumentException("Destination coordinates must be a neighboring cell. Coordinates = " + to);

        Entity entity = entities.remove(from);
        entities.put(to, entity);
        return to;
    }
}
