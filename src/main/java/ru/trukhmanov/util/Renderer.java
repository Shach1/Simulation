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
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_HIGHLIGHTED_BACKGROUND = "\u001B[41m";

    private final WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void renderMap() {
        rednerMapWithHighlightedCoordinates(null);
    }

    public void rednerMapWithHighlightedCoordinates(Coordinates highlightedCoordinates) {
        System.out.println();
        for (int h = worldMap.maxHeight; h >= WorldMap.MIN_HEIGHT; h--) {
            StringBuilder line = new StringBuilder(String.format("%2d ", h));
            for (int w = WorldMap.MIN_WIDTH; w < worldMap.maxWidth + 1; w++) {
                Coordinates coordinates = new Coordinates(w, h);
                if (worldMap.isEmptyCell(coordinates)) {
                    line.append(EMPTY_CELL_SPRITE);
                    continue;
                }
                if (coordinates.equals(highlightedCoordinates)){
                    line.append(getEntitySprite(worldMap.getEntityByCoordinates(coordinates), true));
                }
                else line.append(getEntitySprite(worldMap.getEntityByCoordinates(coordinates), false));
            }
            System.out.println(line);
        }
    }

    private String getEntitySprite(Entity entity, boolean isHighlighted) {
        String result = "";
        if (isHighlighted) result += ANSI_HIGHLIGHTED_BACKGROUND;
        switch (entity) {
            case Herbivore ignored -> result += HERBIVORE_SPRITE;
            case Predator ignored -> result += PREDATOR_SPRITE;
            case Rock ignored -> result += ROCK_SPRITE;
            case Grass ignored -> result += GRASS_SPRITE;
            case Tree ignored -> result += TREE_SPRITE;
            default ->
                    throw new IllegalArgumentException("Undefined entity type. entity = " + entity.getClass().getSimpleName());
        }
        result += ANSI_RESET;
        return result;
    }

}
