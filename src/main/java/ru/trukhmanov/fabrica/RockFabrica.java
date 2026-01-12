package ru.trukhmanov.fabrica;

import ru.trukhmanov.entity.Entity;
import ru.trukhmanov.entity.Rock;

public class RockFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Rock();
    }
}
