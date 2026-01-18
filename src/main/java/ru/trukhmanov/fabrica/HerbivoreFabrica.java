package ru.trukhmanov.fabrica;

import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Herbivore;

public class HerbivoreFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Herbivore(2, 3);
    }
}
