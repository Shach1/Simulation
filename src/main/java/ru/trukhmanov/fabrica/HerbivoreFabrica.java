package ru.trukhmanov.fabrica;

import ru.trukhmanov.entity.Entity;
import ru.trukhmanov.entity.Herbivore;

public class HerbivoreFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Herbivore(3, 2);
    }
}
