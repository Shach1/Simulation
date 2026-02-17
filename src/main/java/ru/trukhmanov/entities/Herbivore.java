package ru.trukhmanov.entities;


/**
 * Травоядное существо. Стремятся найти ресурс (Grass), \
 * может потратить свой ход на движение в сторону травы, либо на её поглощение.
 */
public class Herbivore extends Creature{

    public Herbivore(int moveSpeed, int healthPoints) {
        super(moveSpeed, healthPoints);
    }

    @Override
    public boolean tryEatEntity(Entity entity) {
        if (entity instanceof Grass) {
            healHealthPoints(5);
            return true;
        }
        return false;
    }
}
