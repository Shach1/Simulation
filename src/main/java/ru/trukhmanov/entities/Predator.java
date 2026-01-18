package ru.trukhmanov.entities;


/**
 * Хищное существо. Стремится найти травоядное.
 * Может потратить свой ход на чтобы:
 * - Переместиться (чтобы приблизиться к жертве - травоядному)
 * - Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
 * Если значение HP жертвы опускается до 0, травоядное исчезает
 */
public class Predator extends Creature{
    final int attackDamage;

    public Predator(int moveSpeed, int healthPoints, int attackDamage) {
        super(moveSpeed, healthPoints);
        this.attackDamage = attackDamage;
    }

    public void attackHerbivore(Entity entity){
        if (entity instanceof Herbivore herbivore){
            herbivore.takeDamage(attackDamage);
            if (!herbivore.isAlive()){
                healHealthPoints(15);
            }
        }

    }
}
