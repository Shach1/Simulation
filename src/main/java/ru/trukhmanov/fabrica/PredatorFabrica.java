package ru.trukhmanov.fabrica;

import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Predator;

public class PredatorFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Predator(5, 3, 1);
    }
}
