package ru.trukhmanov;

import ru.trukhmanov.entities.*;

/**
 * Класс для отрисовки карты в консоль
 */
public class Renderer {
    private final WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void renderMap(){
        for (int h = worldMap.height; h > 0; h--){
            StringBuilder line = new StringBuilder(String.format("%2d ", h));
            for (int w = 1; w < worldMap.weight + 1; w++){
                Coordinates coordinates = new Coordinates(w, h);
                if (worldMap.isEmptyCell(coordinates)) line.append("[  ]");
                else line.append(getEntitySprite(worldMap.getEntityByCoordinates(coordinates)));
            }
            System.out.println(line);
        }
    }

    private String getEntitySprite(Entity entity){
        if (entity instanceof Herbivore) return "[🐇]";
        if (entity instanceof Predator) return "[🦅]";
        if (entity instanceof Rock) return "[🗿]";
        if (entity instanceof Grass) return "[🌱]";
        if (entity instanceof Tree) return "[🌳]";
        return "";
    }

}
