package ru.trukhmanov.entities.fabrica;

import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Grass;

public class GrassFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Grass();
    }
}
