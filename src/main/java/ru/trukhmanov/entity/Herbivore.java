package ru.trukhmanov.entity;

import ru.trukhmanov.Coordinates;

/**
 * Травоядное существо. Стремятся найти ресурс (Grass), \
 * может потратить свой ход на движение в сторону травы, либо на её поглощение.
 */
public class Herbivore extends Creature{
    public Herbivore(int healthPoints, int moveSpeed) {
        super(healthPoints, moveSpeed);
    }

    public void eatGrass(){

    }
}
