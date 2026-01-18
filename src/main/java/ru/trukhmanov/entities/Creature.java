package ru.trukhmanov.entities;

/**
 * Класс для существ способных двигаться
 */
public abstract class Creature extends Entity{
    public final int moveSpeed;
    private int healthPoints;
    private boolean isAlive = true;

    public Creature(int moveSpeed, int healthPoints) {
        this.moveSpeed = moveSpeed;
        this.healthPoints = healthPoints;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // TODO: Реализовать
    public void makeMove(){

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
