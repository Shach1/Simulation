package ru.trukhmanov.fabrica;

import ru.trukhmanov.entity.Entity;
import ru.trukhmanov.entity.Predator;

public class PredatorFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Predator(5, 3, 1);
    }
}
