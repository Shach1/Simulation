package ru.trukhmanov.fabrica;

import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Rock;

public class RockFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Rock();
    }
}
