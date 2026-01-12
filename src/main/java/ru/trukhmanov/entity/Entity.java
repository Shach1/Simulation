package ru.trukhmanov.entity;

import ru.trukhmanov.Coordinates;

/**
 * Базовый класс для всех существ.
 */
public abstract class Entity {
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates){
        if (coordinates == null) return;
        this.coordinates = coordinates;
    }
}
