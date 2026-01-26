package ru.trukhmanov.util;

import ru.trukhmanov.core.Coordinates;
import ru.trukhmanov.core.WorldMap;
import ru.trukhmanov.entities.*;

/**
 * Класс для отрисовки карты в консоль
 */
public class Renderer {
    private static final String HERBIVORE_SPRITE = "🐇";
    private static final String PREDATOR_SPRITE = "🦊";
    private static final String ROCK_SPRITE = "🗿";
    private static final String GRASS_SPRITE = "🌱";
    private static final String TREE_SPRITE = "🪾";
    private static final String EMPTY_CELL_SPRITE = "⬛️";
    private final WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void renderMap(){
        renderMap(0);
    }

    public void renderMap(long delay){
        if (delay < 0) throw new IllegalArgumentException("delay cannot be less than zero. delay = " + delay);
        try{
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        for (int h = worldMap.maximumHeight; h > 0; h--){
            StringBuilder line = new StringBuilder(String.format("%2d ", h));
            for (int w = 1; w < worldMap.maximumWeight + 1; w++){
                Coordinates coordinates = new Coordinates(w, h);
                if (worldMap.isEmptyCell(coordinates)) line.append(EMPTY_CELL_SPRITE);
                else line.append(getEntitySprite(worldMap.getEntityByCoordinates(coordinates)));
            }
            System.out.println(line);
        }
    }

    private String getEntitySprite(Entity entity){
        if (entity instanceof Herbivore) return HERBIVORE_SPRITE;
        if (entity instanceof Predator) return PREDATOR_SPRITE;
        if (entity instanceof Rock) return ROCK_SPRITE;
        if (entity instanceof Grass) return GRASS_SPRITE;
        if (entity instanceof Tree) return TREE_SPRITE;
        throw new IllegalArgumentException("Undefined entity type. entity = " + entity.getClass().getSimpleName());
    }

}
