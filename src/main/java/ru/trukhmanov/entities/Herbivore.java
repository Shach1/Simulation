package ru.trukhmanov.entities;


/**
 * Травоядное существо. Стремятся найти ресурс (Grass), \
 * может потратить свой ход на движение в сторону травы, либо на её поглощение.
 */
public class Herbivore extends Creature{

    public Herbivore(int moveSpeed, int healthPoints) {
        super(moveSpeed, healthPoints);
    }

    public boolean eatGrass(Entity entity){
        if (entity instanceof Grass grass){
            healHealthPoints(10);
            return true;
        }
        return false;
    }
}
