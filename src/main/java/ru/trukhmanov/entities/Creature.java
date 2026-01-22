package ru.trukhmanov.entities;

/**
 * Класс для существ способных двигаться
 */
public abstract class Creature extends Entity{
    public final int moveSpeed;
    private int healthPoints;
    private int stamina;
    private boolean isAlive = true;

    public Creature(int moveSpeed, int healthPoints) {
        this.moveSpeed = moveSpeed;
        this.healthPoints = healthPoints;
        this.stamina = moveSpeed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // вообще не уверен, что я правильно реализую эту функцию
    // если бы не ТЗ, реализовал бы движение на уровне класса Simulation
    public void makeMove(){
        if (stamina == 0) throw new RuntimeException("Creature cant move when stamina = 0");
        stamina--;
    }

    public boolean canMove(){
        return stamina > 0;
    }

    public void restoreStamina(){ stamina = moveSpeed;}

    public void healHealthPoints(int healthPoints){
        if (healthPoints > 0) this.healthPoints += healthPoints;
    }

    public void takeDamage(int damage){
        if (damage > 0) {
            healthPoints -= damage;
            if (healthPoints < 1) isAlive = false;
        }
    }

    public abstract boolean tryEatEntity(Entity entity);
}
