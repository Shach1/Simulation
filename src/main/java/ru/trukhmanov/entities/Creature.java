package ru.trukhmanov.entities;

import ru.trukhmanov.Coordinates;

/**
 * Класс для существ способных двигаться
 */
public abstract class Creature extends Entity{
    public final int moveSpeed;
    private int healthPoints;
    private boolean isAlive = true;
    private Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates){
        if (coordinates == null) return;
        this.coordinates = coordinates;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Creature(Coordinates coordinates, int moveSpeed, int healthPoints) {
        this.coordinates = coordinates;
        this.moveSpeed = moveSpeed;
        this.healthPoints = healthPoints;
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
