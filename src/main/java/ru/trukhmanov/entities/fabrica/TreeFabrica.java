package ru.trukhmanov.entities.fabrica;

import ru.trukhmanov.entities.Entity;
import ru.trukhmanov.entities.Tree;

public class TreeFabrica implements EntityFabrica{
    @Override
    public Entity getEntity() {
        return new Tree();
    }
}
