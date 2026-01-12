package ru.trukhmanov.entity;

import ru.trukhmanov.Coordinates;

/**
 * Хищное существо. Стремится найти травоядное.
 * Может потратить свой ход на чтобы:
 * - Переместиться (чтобы приблизиться к жертве - травоядному)
 * - Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
 * Если значение HP жертвы опускается до 0, травоядное исчезает
 */
public class Predator extends Creature{
    final int attackDamage;

    public Predator(int healthPoints, int moveSpeed, int attackDamage) {
        super(healthPoints, moveSpeed);
        this.attackDamage = attackDamage;
    }

    public void attackHerbivore(){

    }
}
