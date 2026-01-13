package ru.trukhmanov.entities;

import ru.trukhmanov.Coordinates;

/**
 * Класс для существ способных двигаться
 */
public abstract class Creature extends Entity{
    public final int moveSpeed;
    private int healthPoints;
    private boolean isAlive = true;

    public Creature(int healthPoints, int moveSpeed) {
        this.healthPoints = healthPoints;
        this.moveSpeed = moveSpeed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void makeMove(Coordinates target){
        setCoordinates(target);
    }

    public void healHealthPoints(int healthPoints){
        if (healthPoints > 0) this.healthPoints += healthPoints;
    }

    public void takeDamage(int damage){
        if (damage > 0) {
            healthPoints -= damage;
            if (healthPoints <= 0) isAlive = false;
        }
    }
}
