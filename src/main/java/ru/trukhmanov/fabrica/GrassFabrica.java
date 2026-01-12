package ru.trukhmanov.fabrica;

import ru.trukhmanov.entity.Entity;
import ru.trukhmanov.entity.Grass;

public class GrassFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Grass();
    }
}
