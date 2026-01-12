package ru.trukhmanov.entity;

import ru.trukhmanov.Coordinates;

/**
 * Класс для существ способных двигаться
 */
public abstract class Creature extends Entity{
    private final int moveSpeed;
    private int healthPoints;

    public Creature(int healthPoints, int moveSpeed) {
        this.healthPoints = healthPoints;
        this.moveSpeed = moveSpeed;
    }

    public void makeMove(){}
}
